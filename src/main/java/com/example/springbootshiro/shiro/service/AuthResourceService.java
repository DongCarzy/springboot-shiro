package com.example.springbootshiro.shiro.service;

import com.example.springbootshiro.shiro.domain.AuthResource;

import java.util.List;

/**
 * @author carzy
 */
public interface AuthResourceService {

    List<AuthResource> finaAllApiResource();

    List<AuthResource> finaAllMenuResource();
}
