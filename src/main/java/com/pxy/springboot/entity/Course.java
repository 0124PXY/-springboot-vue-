package com.pxy.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author pxy
 * @since 2023-04-13
 */
@Getter
@Setter
  @ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("业务名称")
      private String name;

      @ApiModelProperty("服务评价")
      private Integer score;

      @ApiModelProperty("上传时间")
      private String times;

      @ApiModelProperty("是否已经维修")
      private Integer state;

      @ApiModelProperty("维修工人工号")
      private Integer teacherId;


}
