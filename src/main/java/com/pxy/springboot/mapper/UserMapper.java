package com.pxy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxy.springboot.controller.dto.UserPasswordDTO;
import com.pxy.springboot.entity.User;
import org.apache.ibatis.annotations.*;


//@Mapper//加上这个注解就可以使UserMapper bean加入到springboot里面了
//数据库查询的接口
public interface UserMapper extends BaseMapper<User> {
    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

}
