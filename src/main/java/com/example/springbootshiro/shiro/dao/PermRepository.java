package com.example.springbootshiro.shiro.dao;

import com.example.springbootshiro.shiro.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author carzy
 * @date 2018/08/06
 */
@Repository
public interface PermRepository extends JpaRepository<Permission, Integer> {
}
