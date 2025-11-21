package com.pxy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxy.springboot.common.Constants;
import com.pxy.springboot.controller.dto.OrderDTO;
import com.pxy.springboot.entity.Order;
import com.pxy.springboot.exception.ServiceException;
import com.pxy.springboot.mapper.OrderMapper;
import com.pxy.springboot.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private Order getOrderInfo(OrderDTO orderDTO){
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", orderDTO.getUsername());
        Order one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
            return one;
    }

}
