package com.pxy.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxy.springboot.controller.dto.UserDTO;
import com.pxy.springboot.controller.dto.UserPasswordDTO;
import com.pxy.springboot.entity.User;

public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);
}
