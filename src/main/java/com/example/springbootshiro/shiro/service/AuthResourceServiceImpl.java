package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.shiro.dao.AuthResourceRepository;
import com.example.springbootshiro.shiro.domain.AuthResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author carzy.
 * @date 17:08 2018/12/25
 */
@Service
public class AuthResourceServiceImpl implements AuthResourceService {

    private final AuthResourceRepository authResourceRepository;

    @Autowired
    public AuthResourceServiceImpl(AuthResourceRepository authResourceRepository) {
        this.authResourceRepository = authResourceRepository;
    }

    @Override
    public List<AuthResource> finaAllApiResource() {
        return this.authResourceRepository.findAllByType(Short.valueOf("2"));
    }

    @Override
    public List<AuthResource> finaAllMenuResource() {
        return this.authResourceRepository.findAllByType(Short.valueOf("1"));
    }
}
