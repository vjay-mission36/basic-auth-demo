package com.mission48.basicsuth.controller;

import com.mission48.basicsuth.model.UserInfo;
import com.mission48.basicsuth.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/add")
    public UserInfo addUserInfo(@RequestBody UserInfo userInfo){
         LOG.info("Adding the UserInfo: "+userInfo.getUserName());
          return  userInfoService.addUserInfo(userInfo);
    }
}
