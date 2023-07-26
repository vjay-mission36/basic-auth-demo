package com.mission48.basicsuth.component;

import com.mission48.basicsuth.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
    private static final Logger LOG = LoggerFactory.getLogger(UserInfoUserDetails.class);
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfoUserDetails(UserInfo userInfo){
        LOG.info("UserinfoUserDetails : {}",userInfo.getPassword());
        this.userName = userInfo.getUserName();
        this.password = userInfo.getPassword();
        this.authorities = Arrays.stream(userInfo.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        LOG.info("Username: {}  Password: {} authorities: {}", this.userName, this.password, this.authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserInfoUserDetails{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
