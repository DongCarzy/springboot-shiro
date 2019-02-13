package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.exception.RepeatException;
import com.example.springbootshiro.exception.ResourceNotFoundException;
import com.example.springbootshiro.shiro.dao.RoleRepository;
import com.example.springbootshiro.shiro.dao.SysRoleUserRepository;
import com.example.springbootshiro.shiro.dao.UserInfoRepository;
import com.example.springbootshiro.shiro.domain.Role;
import com.example.springbootshiro.shiro.domain.SysRoleUser;
import com.example.springbootshiro.shiro.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author carzy.
 * @date 16:28 2018/12/24
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;
    private final SysRoleUserRepository sysRoleUserRepository;

    @Autowired
    public UserServiceImpl(UserInfoRepository userInfoRepository, RoleRepository roleRepository, SysRoleUserRepository sysRoleUserRepository) {
        this.userInfoRepository = userInfoRepository;
        this.roleRepository = roleRepository;
        this.sysRoleUserRepository = sysRoleUserRepository;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserInfo createUser(UserInfo userInfo) throws RepeatException {
        Optional<UserInfo> optional = this.userInfoRepository.findById(userInfo.getId());
        if (optional.isPresent()) {
            throw new RepeatException("user is existed");
        } else {
            UserInfo userInfo1 = this.userInfoRepository.save(userInfo);
            Optional<Role> rOptional = this.roleRepository.findByRoleName("访客");
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserInfo(userInfo1);
            if (rOptional.isPresent()) {
                sysRoleUser.setRole(rOptional.get());
            } else {
                Role role = new Role();
                role.setRoleName("访客");
                role.setDescription("访客");
                role.setAvailable(true);
                role = roleRepository.save(role);
                sysRoleUser.setRole(role);
            }
            this.sysRoleUserRepository.save(sysRoleUser);
            return userInfo1;
        }
    }

    @Override
    public void changePassword(Integer userId, String newPassword) throws ResourceNotFoundException {
        Optional<UserInfo> userInfo = this.userInfoRepository.findById(userId);
        if (userInfo.isPresent()) {
            userInfo.get().setPassword(newPassword);
            this.userInfoRepository.save(userInfo.get());
        } else {
            throw new ResourceNotFoundException("user is not exist");
        }
    }

    @Override
    public UserInfo findByAccount(String account) throws ResourceNotFoundException {
         Optional<UserInfo> optional = this.userInfoRepository.findByAccount(account);
         if (optional.isPresent()) {
             return optional.get();
         } else {
             throw new ResourceNotFoundException(String.format("user_info not found by appId: %s", account));
         }
    }
}
