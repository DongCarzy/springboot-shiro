package com.example.springbootshiro.business.web;

import com.example.springbootshiro.business.web.dto.LoginForm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author carzy
 * @date 2018/08/06
 */
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("login")
    public Map<String, Object> login(@Valid @RequestBody LoginForm loginForm) {
        logger.info("login user: {}", loginForm);
        Map<String, Object> result = new HashMap<>(2);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword());

        subject.login(token);
//        try {
//            subject.login(token);
//            result.put("code", "200");
//            result.put("token", subject.getSession().getId());
//            result.put("msg", "登录成功");
//        } catch (IncorrectCredentialsException e) {
//            result.put("code", "601");
//            result.put("msg", "密码错误");
//        } catch (AuthenticationException e) {
//            result.put("code", "602");
//            result.put("msg", "该用户不存在");
//        }
        logger.info("login result: {}", result);
        return result;
    }

    @PostMapping("logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}
