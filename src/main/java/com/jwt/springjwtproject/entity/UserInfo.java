package com.jwt.springjwtproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("userInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    private String userId;
    private String userName;
    private String email;
    private  String password;
    private String roles;
}