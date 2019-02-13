package com.example.springbootshiro.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author carzy.
 * @date 16:09 2018/12/26
 */
@ConfigurationProperties(prefix = "sys.admin")
public class SysAdminProperties {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            password = UUID.randomUUID().toString();
            System.err.println(password);
        }
        this.password = password;
    }
}