package com.pxy.springboot.exception;

import lombok.Getter;

import java.security.Provider;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;

    public ServiceException(String code,String msg){
        super(msg);
        this.code = code;
    }
}
