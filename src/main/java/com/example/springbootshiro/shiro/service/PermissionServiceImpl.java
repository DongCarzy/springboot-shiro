package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.shiro.dao.PermRepository;
import com.example.springbootshiro.shiro.dao.SysRolePermRepository;
import com.example.springbootshiro.shiro.dao.SysRoleUserRepository;
import com.example.springbootshiro.shiro.domain.Permission;
import com.example.springbootshiro.shiro.domain.SysRolePermission;
import com.example.springbootshiro.shiro.domain.SysRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author carzy.
 * @date 15:34 2018/12/26
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final SysRolePermRepository sysRolePermRepository;
    private final SysRoleUserRepository sysRoleUserRepository;
    private final PermRepository permRepository;

    @Autowired
    public PermissionServiceImpl(SysRolePermRepository sysRolePermRepository, SysRoleUserRepository sysRoleUserRepository, PermRepository permRepository) {
        this.sysRolePermRepository = sysRolePermRepository;
        this.sysRoleUserRepository = sysRoleUserRepository;
        this.permRepository = permRepository;
    }

    @Override
    public Permission addPermission(Permission permission) {
        return null;
    }

    @Override
    public void deletePermission(Integer permissionId) {
    }

    @Override
    public List<Permission> findAllByUserAccount(String account) {
        List<SysRoleUser> roleUsers = this.sysRoleUserRepository.findAllByUserInfoAccount(account);
        Set<Integer> roleIds = roleUsers.stream().map(v -> v.getRole().getId()).collect(Collectors.toSet());

        return this.findAllByRoleIds(roleIds);
    }

    @Override
    public List<Permission> findAllByRoleIds(Set<Integer> roleIds) {
        return this.sysRolePermRepository.findByRoleIdIn(roleIds).stream().map(SysRolePermission::getPermission).collect(Collectors.toList());
    }

    @Override
    public List<Permission> findAll() {
        return this.permRepository.findAll();
    }
}
