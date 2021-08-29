package com.taltools.controller;

import com.taltools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        repMap.put("data",userService.getAll(name));
        return repMap;
    }
}
