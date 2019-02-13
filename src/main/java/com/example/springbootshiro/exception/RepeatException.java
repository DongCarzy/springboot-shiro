package com.example.springbootshiro.exception;

/**
 * 资源重复出现
 *
 * @author carzy.
 * @date 16:44 2018/12/24
 */
public class RepeatException extends Exception {

    private static final long serialVersionUID = 1L;

    public RepeatException(String message) {
        super(message);
    }

}
