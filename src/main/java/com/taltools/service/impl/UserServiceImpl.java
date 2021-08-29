package com.taltools.service.impl;

import com.taltools.dao.UserDao;
import com.taltools.entity.UserEntity;
import com.taltools.service.UserService;
import com.taltools.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<UserEntity> getQueryValue(String name) {
        Map paramsMap = new HashMap();
        paramsMap.put("name",name);
        log.info(JsonUtils.obj2json(userDao.getQueryValue(paramsMap)));
        return userDao.getQueryValue(paramsMap);
    }

    @Override
    public List<UserEntity> getAll() {
        return userDao.getAll();
    }
}
