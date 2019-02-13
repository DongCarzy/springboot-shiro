package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.shiro.domain.Permission;

import java.util.List;
import java.util.Set;

/**
 * @author carzy
 */
public interface PermissionService {

    /**
     * 新建权限
     *
     * @param permission Permission
     * @return Permission
     */
    Permission addPermission(Permission permission);

    /**
     * 删除权限
     *
     * @param permissionId Permission.id
     */
    void deletePermission(Integer permissionId);

    /**
     * 通过账户号查询用户拥有的所有权限
     *
     * @param account {@link com.example.springbootshiro.shiro.domain.UserInfo account}
     * @return List<Permission>
     */
    List<Permission> findAllByUserAccount(String account);

    /**
     * 通过账户号查询用户拥有的所有权限
     *
     * @param roleIds {@link com.example.springbootshiro.shiro.domain.Role ids}
     * @return List<Permission>
     */
    List<Permission> findAllByRoleIds(Set<Integer> roleIds);

    List<Permission> findAll();

}
