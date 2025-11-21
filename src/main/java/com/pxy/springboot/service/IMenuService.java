package com.pxy.springboot.service;

import com.pxy.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pxy
 * @since 2023-04-05
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> findMenus(String name);
}
