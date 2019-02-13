package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.shiro.domain.Role;

import java.util.List;

/**
 * @author carzy
 */
public interface RoleService {

    /**
     * 添加角色
     *
     * @param role Role
     * @return Role
     */
    Role createRole(Role role);

    /**
     * 删除角色
     *
     * @param roleId Integer
     */
    void deleteRole(Integer roleId);

    /**
     * 通过账户号查找用户所有角色
     *
     * @param account 账户号 {@link com.example.springbootshiro.shiro.domain.UserInfo account}
     * @return List<Role>
     */
    List<Role> findAllByAccount(String account);

    /**
     * 查找所有的权限
     *
     * @return List<Role>
     */
    List<Role> findAll();
}
