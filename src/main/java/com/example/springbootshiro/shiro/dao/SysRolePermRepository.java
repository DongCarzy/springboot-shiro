package com.example.springbootshiro.shiro.dao;

import com.example.springbootshiro.shiro.domain.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author carzy
 * @date 2018/08/06
 */
@Repository
public interface SysRolePermRepository extends JpaRepository<SysRolePermission, Integer> {

    /**
     * 通过角色集合查权限
     *
     * @param roles 角色ID集
     * @return SysRolePermission集
     */
    List<SysRolePermission> findByRoleIdIn(Set<Integer> roles);
}
