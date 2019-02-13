package com.example.springbootshiro;

import com.example.springbootshiro.shiro.config.SysAdminProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author carzy
 * @date 2018/08/03
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {SysAdminProperties.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
