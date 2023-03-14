package com.jwt.springjwtproject.repository;

import com.jwt.springjwtproject.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoRepository extends MongoRepository<UserInfo,String> {
    public Optional<UserInfo> findByUserName(String username);
}
