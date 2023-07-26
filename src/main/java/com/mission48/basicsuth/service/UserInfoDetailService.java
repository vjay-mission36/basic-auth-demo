package com.mission48.basicsuth.service;

import com.mission48.basicsuth.component.UserInfoUserDetails;
import com.mission48.basicsuth.model.UserInfo;
import com.mission48.basicsuth.repo.UserInfoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoDetailService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserInfoDetailService.class);

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        LOG.info("Trying to load the UserDetails : {}", username);
        Optional<UserInfo> userInfoOptional = userInfoRepo.findByUserName(username);
        LOG.info("User :{}", userInfoOptional.get().getRole());
        userInfo = userInfoOptional.get();
        LOG.info("-------------------------");
        return new UserInfoUserDetails(userInfo);
        // return userInfoOptional.map().orElseThrow(() -> new UsernameNotFoundException("User not found "+username));
    }
}
