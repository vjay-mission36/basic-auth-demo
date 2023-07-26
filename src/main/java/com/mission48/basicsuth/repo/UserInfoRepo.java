package com.mission48.basicsuth.repo;

import com.mission48.basicsuth.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUserName(String username);
}
