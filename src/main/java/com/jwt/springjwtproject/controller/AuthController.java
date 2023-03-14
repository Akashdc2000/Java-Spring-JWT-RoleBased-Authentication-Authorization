package com.jwt.springjwtproject.controller;

import com.jwt.springjwtproject.entity.UserInfo;
import com.jwt.springjwtproject.repository.UserInfoRepository;
import com.jwt.springjwtproject.security.JwtAuthRequest;
import com.jwt.springjwtproject.security.JwtAuthResponse;
import com.jwt.springjwtproject.security.JwtTokenHelper;
//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;

    public AuthController(JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserInfoRepository userInfoRepository) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping("/login")
    public JwtAuthResponse createToken(@RequestBody JwtAuthRequest jwtAuthRequest){
        this.authenticate(jwtAuthRequest.getUserName(),jwtAuthRequest.getPassword());
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
        String token=this.jwtTokenHelper.generateToken(userDetails);
        System.out.println("Token ="+token);

        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        return jwtAuthResponse;
    }

    private void authenticate(String userName, String password) {

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
        System.out.println("UserName:"+userName+"Password:"+password);
        this.authenticationManager.authenticate(authenticationToken);
    }


    @PostMapping("/user")
    public String addUser(@RequestBody UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);

        return "User Added Successfully...";
    }


    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserInfo> getAllUsers(){
        return userInfoRepository.findAll();
    }
}
