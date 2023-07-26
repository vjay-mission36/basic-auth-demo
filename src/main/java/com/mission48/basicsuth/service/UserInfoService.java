package com.mission48.basicsuth.service;

import com.mission48.basicsuth.model.UserInfo;
import com.mission48.basicsuth.repo.UserInfoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    private static final Logger LOG = LoggerFactory.getLogger(UserInfoService.class);
    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo addUserInfo(UserInfo userInfo){
        String password_encoded = passwordEncoder.encode(userInfo.getPassword());
        LOG.info("Encoded Password: {}",password_encoded);
        userInfo.setPassword(password_encoded);
       return  userInfoRepo.save(userInfo);
    }



}
