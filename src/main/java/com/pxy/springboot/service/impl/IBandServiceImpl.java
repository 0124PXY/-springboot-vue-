package com.pxy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxy.springboot.common.Constants;
import com.pxy.springboot.controller.dto.BandDTO;
import com.pxy.springboot.entity.Band;
import com.pxy.springboot.exception.ServiceException;
import com.pxy.springboot.mapper.BandMapper;
import com.pxy.springboot.service.IBandService;
import org.springframework.stereotype.Service;

@Service
public class IBandServiceImpl extends ServiceImpl<BandMapper, Band> implements IBandService {

    private Band getBandInfo(BandDTO bandDTO){
        QueryWrapper<Band> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", bandDTO.getName());
        queryWrapper.eq("bandwidth",bandDTO.getBandwidth());
        Band one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
            return one;
    }

}
