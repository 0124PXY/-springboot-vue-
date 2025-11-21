package com.pxy.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

//Alt+insert调出set和get方法
@Data//这一个注解就可以代替下面所有set,get方法
@NoArgsConstructor//创建无参构造方法
@AllArgsConstructor//创建有参构造方法
@Getter
@Setter
@TableName("sys_order")
@ApiModel(value = "Order对象",description = "")
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
      @TableId(type = IdType.AUTO,value = "id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("预约时间")
    private LocalDateTime time;

    @ApiModelProperty("套餐名称")
    private String name;

    @ApiModelProperty("套餐资费")
    private String fee;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("创建时间")
    private Date createTime;


}
