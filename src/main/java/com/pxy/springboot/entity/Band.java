package com.pxy.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author pxy
 * @since 2023-04-05
 */
@Getter
@Setter
@TableName("sys_fee")
@ApiModel(value = "Band对象", description = "")
public class Band implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("套餐名称")
      private String name;
      @ApiModelProperty("套餐资费")
      private String fee;

      @ApiModelProperty("宽带类型")
      private String type;

      @ApiModelProperty("路由器设备")
      private String router;

      @ApiModelProperty("宽带带宽")
      private String bandwidth;


}
