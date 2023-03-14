package com.jwt.springjwtproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private  JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


//        Get Token
        String requestToken=request.getHeader("Authorization");
        System.out.println(requestToken);
        String username=null;
        String token=null;

        if(requestToken!=null && requestToken.startsWith("Bearer ")){

            token=requestToken.substring(7);
            try{
                username=jwtTokenHelper.extractUsername(token);
            }
            catch (Exception e){
                System.out.println("Something Went Wrong....");
            }
        }
        else {
            System.out.println("JWT token not begin with bearer...");
        }

//        Validate Token

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
            System.out.println("Validated User:="+userDetails.getUsername()+userDetails.getPassword());

            //return true if token is validated
            if(this.jwtTokenHelper.validateToken(token,userDetails)){

                //set authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            else {
                System.out.println("JWT Token is not Valid...");
            }

        }else {
            System.out.println("UserName or Context is Null...");
        }

        filterChain.doFilter(request,response);
    }
}
