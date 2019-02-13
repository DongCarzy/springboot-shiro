package com.example.springbootshiro.exception;

/**
 * 未找到对应资源
 *
 * @author carzy.
 * @date 16:38 2018/12/24
 */
public class ResourceNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
