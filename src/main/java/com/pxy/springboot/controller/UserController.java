package com.pxy.springboot.controller;
//后台登录接口

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.springboot.common.Constants;
import com.pxy.springboot.common.Result;
import com.pxy.springboot.controller.dto.UserDTO;
import com.pxy.springboot.controller.dto.UserPasswordDTO;
import com.pxy.springboot.entity.User;
import com.pxy.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user")//统一给接口加前缀user
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){//RequestBody可以把前台传过来的json对象转化为Java对象
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            //hutool工具类StrUtil的isBlank校验字符串username是不是空
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){//RequestBody可以把前台传过来的json对象转化为Java对象
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            //hutool工具类StrUtil的isBlank校验字符串username是不是空
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(userService.register(userDTO));
    }
    /**
     * 修改密码
     */
    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
//        userPasswordDTO.setPassword(SecureUtil.md5(userPasswordDTO.getPassword()));
//        userPasswordDTO.setNewPassword(SecureUtil.md5(userPasswordDTO.getNewPassword()));
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }
    //新增或者更新
    @PostMapping
    public Result save(@RequestBody User user){//RequestBody可以把前台传过来的json对象转化为Java对象
        return Result.success(userService.saveOrUpdate(user));
    }
    //删除数据
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(userService.removeByIds(ids));
    }
    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(userService.getOne(queryWrapper));
    }
    //分页查询-mybatis-plus的方式
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,//不传时候置空，不能只搜索一个信息时候给我报错
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }
        return Result.success(userService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //从数据库查出数据
        List<User> list = userService.list();
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");
        writer.addHeaderAlias("avatarUrl","头像");
        writer.addHeaderAlias("role","角色");

        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);
        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName +".xlsx");
        //获取输出流，把输出流放到writer里面刷新一下
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();

    }
    //导入
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        //忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list){
            User user = new User();
            user.setUsername(row.get(1).toString());
            user.setPassword(row.get(2).toString());
            user.setNickname(row.get(3).toString());
            user.setEmail(row.get(4).toString());
            user.setPhone(row.get(5).toString());
            user.setAddress(row.get(6).toString());
            user.setAvatarUrl(row.get(7).toString());
            user.setRole(row.get(9).toString());
            users.add(user);
        }
        userService.saveBatch(users);//批量插入
        System.out.println(list);
        return Result.success(true);
    }
}
