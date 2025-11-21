package com.pxy.springboot.controller.dto;

import com.pxy.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 接受前端登录请求的参数
 */
@Data
public class BandDTO {
    private String name;
    private int fee;
    private String type;
    private String router;
    private String bandwidth;

}
