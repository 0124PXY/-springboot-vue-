package com.pxy.springboot.controller;

//后台登录接口
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.springboot.common.Result;
import com.pxy.springboot.entity.Order;
import com.pxy.springboot.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")//统一给接口加前缀order
public class OrderController {

    @Resource
    private IOrderService orderService;


    //新增或者更新
    @PostMapping
    public Result save(@RequestBody Order order){//RequestBody可以把前台传过来的json对象转化为Java对象
        return Result.success(orderService.saveOrUpdate(order));
    }
    //删除数据
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(orderService.removeById(id));
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(orderService.removeByIds(ids));
    }
    @GetMapping
    public Result findAll() {
        return Result.success(orderService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(orderService.getById(id));
    }
    //根据用户名，获取该用户的订单
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(orderService.getOne(queryWrapper));
    }
    //分页查询-mybatis-plus的方式
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String username//不传时候置空，不能只搜索一个信息时候给我报错
    ){
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        return Result.success(orderService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }

}
