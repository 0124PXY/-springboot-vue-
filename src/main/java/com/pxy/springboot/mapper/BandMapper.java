package com.pxy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxy.springboot.entity.Band;


//@Mapper//加上这个注解就可以使UserMapper bean加入到springboot里面了
////数据库查询的接口
public interface BandMapper extends BaseMapper<Band> {

}
