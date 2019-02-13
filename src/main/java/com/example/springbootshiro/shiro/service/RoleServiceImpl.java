package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.shiro.dao.RoleRepository;
import com.example.springbootshiro.shiro.dao.SysRoleUserRepository;
import com.example.springbootshiro.shiro.domain.Role;
import com.example.springbootshiro.shiro.domain.SysRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author carzy.
 * @date 15:43 2018/12/26
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final SysRoleUserRepository sysRoleUserRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(SysRoleUserRepository sysRoleUserRepository, RoleRepository roleRepository) {
        this.sysRoleUserRepository = sysRoleUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public void deleteRole(Integer roleId) {

    }

    @Override
    public List<Role> findAllByAccount(String account) {
        return this.sysRoleUserRepository.findAllByUserInfoAccount(account).stream().map(SysRoleUser::getRole).collect(Collectors.toList());
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
