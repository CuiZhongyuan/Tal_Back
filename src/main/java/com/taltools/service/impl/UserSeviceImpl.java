package com.taltools.service.impl;


import com.taltools.dao.UserDao;
import com.taltools.entity.User;
import com.taltools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("ALL")
@Service
public class UserSeviceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User getUser(String name) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("name",name);
        return userDao.getUser(paramsMap);
    }
}
