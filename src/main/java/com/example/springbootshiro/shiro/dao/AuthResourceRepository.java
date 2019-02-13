package com.example.springbootshiro.shiro.dao;

import com.example.springbootshiro.shiro.domain.AuthResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author carzy.
 * @date 17:00 2018/12/25
 */
public interface AuthResourceRepository extends JpaRepository<AuthResource, Integer> {

    List<AuthResource> findAllByType(Short type);
}
