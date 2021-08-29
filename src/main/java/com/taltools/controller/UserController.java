package com.taltools.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taltools.entity.UserEntity;
import com.taltools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/queryName")
    public Map<String,Object> getAll(@RequestParam("name") String name){
        Map<String, Object> repMap = new HashMap<>();
        repMap.put("code", 200);
        repMap.put("message","操作成功");
        repMap.put("data",userService.getQueryValue(name));
        return repMap;
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
