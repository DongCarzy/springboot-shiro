package com.example.springbootshiro.shiro.dao;

import com.example.springbootshiro.shiro.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author carzy
 * @date 2018/08/06
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    /**
     * 通过唯一标识符账号找到用户信息
     *
     * @param account 账号
     * @return UserInfo
     */
    Optional<UserInfo> findByAccount(String account);

}
