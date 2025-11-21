package com.pxy.springboot.controller;
//后台登录接口

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.springboot.common.Result;
import com.pxy.springboot.entity.Band;
import com.pxy.springboot.service.IBandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/band")//统一给接口加前缀band
public class BandController {

    @Resource
    private IBandService bandService;


    //新增或者更新
    @PostMapping
    public Result save(@RequestBody Band band){//RequestBody可以把前台传过来的json对象转化为Java对象
        return Result.success(bandService.saveOrUpdate(band));
    }
    //删除数据
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(bandService.removeById(id));
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(bandService.removeByIds(ids));
    }
    @GetMapping
    public Result findAll() {
        return Result.success(bandService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(bandService.getById(id));
    }
    //根据套餐名称，获取宽带信息的所有数据
    @GetMapping("/name/{name}")
    public Result findOne(@PathVariable String name) {
        QueryWrapper<Band> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return Result.success(bandService.getOne(queryWrapper));
    }
    //分页查询-mybatis-plus的方式
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,//不传时候置空，不能只搜索一个信息时候给我报错
                           @RequestParam(defaultValue = "") String bandwidth
    ){
        QueryWrapper<Band> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if(!"".equals(bandwidth)){
            queryWrapper.like("bandwidth",bandwidth);
        }
        return Result.success(bandService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }

}
