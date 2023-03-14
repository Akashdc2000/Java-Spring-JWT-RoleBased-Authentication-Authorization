package com.jwt.springjwtproject.security;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userName;
    private String password;
}
