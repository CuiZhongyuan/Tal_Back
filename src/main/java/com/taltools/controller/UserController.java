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
     * groovy脚本调试
     * */
    @PostMapping("/groovy")
    public String testGroovy(@RequestBody String req){
        return userService.testGroovy(req);
    }
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
     * 编辑接口
     */
    @PostMapping("/editUser")
    public String editUser(@RequestBody UserEntity req){
        System.out.println(req);
        if (req.getSchool() !=null){
           return userService.editUser(req);
        }else {
            System.out.println("请求参数不能为空");
        }
        return null;
    }
    /**
     * 点击编辑前根据ID查询接口
     * @param id
     */
    @GetMapping("/queryId")
    public PageInfo<UserEntity> queryId(@RequestParam(value = "id" ) String id){
        List<UserEntity> userEntityList = userService.getId(id);
        //获取当前分页对象
        PageInfo<UserEntity> pageInfo = new PageInfo<>(userEntityList);
        return pageInfo;
    }
    /**
     * 条件查询
     */
    @GetMapping("/queryList")
    public PageInfo<UserEntity> getAll(@RequestParam(value = "name" ,required=false) String name,
                                       @RequestParam(value = "school") String school,
                                       @RequestParam(value = "id",required = false) String id,
                                       @RequestParam(value = "createTime",required = false)String createTime){
        List<UserEntity> userEntityList = userService.getQueryValue(name,school,createTime,id);
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
