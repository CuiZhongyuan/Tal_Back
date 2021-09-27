package com.taltools.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taltools.entity.UserEntity;
import com.taltools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 删除接口
     */
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id") String id){
        return userService.deleteUser(id);
    }
    /**
     * 新增接口
     * @param req
     * @return
     */
    @PostMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity req){
        System.out.println(req);
        if (req.getName() != null){
            userService.addUser(req);
        }else {
            System.out.println("参数不能为空");
        }
        return req;
    }
    /**
     * 查询
     */
    @GetMapping("/queryList")
    public PageInfo<UserEntity> getAll(@RequestParam(value = "name" ,required=false) String name,
                                       @RequestParam(value = "school") String school,
                                       @RequestParam(value = "createTime",required = false)String createTime){
        List<UserEntity> userEntityList = userService.getQueryValue(name,school,createTime);
        //获取当前分页对象
        PageInfo<UserEntity> pageInfo = new PageInfo<>(userEntityList);
        return pageInfo;
    }

    /**
     * 获取分页对象
     * @param pageNum ： 当前第几页
     * @param pageSize：当前页一共显示多少条数据
     * @return
     */
    @GetMapping("/getPage")
    public PageInfo<UserEntity> getPage(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        //通过PageHelper的静态方法获取分页数据
        PageHelper.startPage(pageNum,pageSize);
        //获取所有数据
        List<UserEntity> userEntityList = userService.getAll();
        //获取当前分页对象
        PageInfo<UserEntity> pageInfo = new PageInfo<>(userEntityList);
        return pageInfo;
    }


}
