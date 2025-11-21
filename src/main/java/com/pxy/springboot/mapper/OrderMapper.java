package com.pxy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxy.springboot.entity.Order;

//@Mapper//加上这个注解就可以使OrderMapper bean加入到springboot里面了
////数据库查询的接口
public interface OrderMapper extends BaseMapper<Order> {

}
