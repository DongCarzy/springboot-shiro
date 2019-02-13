package com.example.springbootshiro.shiro.dao;

import com.example.springbootshiro.shiro.domain.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author carzy
 * @date 2018/08/06
 */
@Repository
public interface SysRoleUserRepository extends JpaRepository<SysRoleUser, Integer> {

    /**
     * 通过账户号查找该用户所有角色
     *
     * @param account 账户号
     * @return SysRoleUser集合
     */
    List<SysRoleUser> findAllByUserInfoAccount(String account);
}
