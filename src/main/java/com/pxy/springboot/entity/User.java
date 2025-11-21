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
import java.util.Date;

//Alt+insert调出set和get方法
@Data//这一个注解就可以代替下面所有set,get方法
@NoArgsConstructor//创建无参构造方法
@AllArgsConstructor//创建有参构造方法
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "User对象",description = "")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
      @TableId(type = IdType.AUTO,value = "id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @JsonIgnore//忽略某个字段不展示给前端
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField(value = "avatar_url")//指定数据库的字段名称
    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色")
    private String role;

}
