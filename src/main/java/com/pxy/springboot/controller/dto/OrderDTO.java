package com.pxy.springboot.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 接受前端登录请求的参数
 */
@Data
public class OrderDTO {
    private String username;
    private LocalDateTime time;
    private String name;
    private String fee;
    private String phone;
    private String address;

}
