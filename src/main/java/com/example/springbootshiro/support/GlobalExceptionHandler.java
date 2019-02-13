package com.example.springbootshiro.support;

import com.example.springbootshiro.common.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理
 *
 * @author carzy.
 * @date 16:53 2018/12/26
 */
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result sqlException(DataAccessException e) {
        LOGGER.error("db err:", e);
        return Result.fail("db err");
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result authorizationException(AuthorizationException e) {
        LOGGER.debug("authorization err:", e);
        return Result.fail("", "Not authorized");
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result authenticationException(AuthenticationException e) {
        LOGGER.debug("authentication err:", e);
        return Result.fail("", "Not authentication");
    }


}
