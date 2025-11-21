package com.pxy.springboot.controller;

//后台登录接口
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.springboot.common.Result;
import com.pxy.springboot.entity.Error;
import com.pxy.springboot.service.IErrorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/error")//统一给接口加前缀order
public class ErrorController {

    @Resource
    private IErrorService errorService;


    //新增或者更新
    @PostMapping
    public Result save(@RequestBody Error error){//RequestBody可以把前台传过来的json对象转化为Java对象
        return Result.success(errorService.saveOrUpdate(error));
    }
    //删除数据
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(errorService.removeById(id));
    }
    @GetMapping
    public Result findAll() {
        return Result.success(errorService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(errorService.getById(id));
    }
    //根据用户名，获取该用户的故障原因
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper<Error> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(errorService.getOne(queryWrapper));
    }
    //分页查询-mybatis-plus的方式
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String username//不传时候置空，不能只搜索一个信息时候给我报错
    ){
        QueryWrapper<Error> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        return Result.success(errorService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }

}
