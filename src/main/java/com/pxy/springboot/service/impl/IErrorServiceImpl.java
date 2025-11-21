package com.pxy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxy.springboot.common.Constants;
import com.pxy.springboot.controller.dto.ErrorDTO;
import com.pxy.springboot.controller.dto.ErrorDTO;
import com.pxy.springboot.entity.Error;
import com.pxy.springboot.exception.ServiceException;
import com.pxy.springboot.mapper.ErrorMapper;
import com.pxy.springboot.mapper.ErrorMapper;
import com.pxy.springboot.service.IErrorService;
import org.springframework.stereotype.Service;

@Service
public class IErrorServiceImpl extends ServiceImpl<ErrorMapper, Error> implements IErrorService {

    private Error getErrorInfo(ErrorDTO errorDTO){
        QueryWrapper<Error> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", errorDTO.getUsername());
        Error one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
            return one;
    }

}
