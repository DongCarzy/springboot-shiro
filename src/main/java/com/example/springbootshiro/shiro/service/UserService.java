package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.exception.RepeatException;
import com.example.springbootshiro.exception.ResourceNotFoundException;
import com.example.springbootshiro.shiro.domain.UserInfo;

import java.util.Optional;

/**
 * @author carzy.
 * @date 16:18 2018/12/24
 */
public interface UserService {

    /**
     * 创建账户
     *
     * @param userInfo UserInfo
     * @return UserInfo
     */
    UserInfo createUser(UserInfo userInfo) throws RepeatException;

    /**
     * 修改密码
     *
     * @param userId      UserInfo.id
     * @param newPassword 新密码
     */
    void changePassword(Integer userId, String newPassword) throws ResourceNotFoundException;

    /**
     * 根据用户名查找用户
     *
     * @param account UserInfo.id phone email
     * @return UserInfo
     */
    UserInfo findByAccount(String account) throws ResourceNotFoundException;

}
