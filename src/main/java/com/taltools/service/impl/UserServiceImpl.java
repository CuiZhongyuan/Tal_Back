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

    /**
     * 条件查询实现
     * @param name
     * @param school
     * @param createTime
     * @return
     */
    @Override
    public List<UserEntity> getQueryValue(String name,String school,String createTime) {
        List<UserEntity>  userEntityList = null;
        try {
            log.info(JsonUtils.obj2json(userDao.getQueryValue(name,school,createTime)));
            userEntityList = userDao.getQueryValue(name,school,createTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userEntityList;
    }

    /**
     * 真分页实现
     * @return
     */
    @Override
    public List<UserEntity> getAll() {
        return userDao.getAll();
    }

    /**
     * 添加实现
     * @param userEntity
     */
    @Override
    public void addUser(UserEntity userEntity) {
        userDao.addUser(userEntity);
    }

    /**
     * 删除实现
     * @param id
     * @return
     */
    @Override
    public String deleteUser(String id) {
        if ( id != null){
           int rep =  userDao.deleteUser(id);
            System.out.println(rep);
            if (rep != 0){
                return "success";
            }
        }
        return "error";
    }

    @Override
    public String editUser(UserEntity userEntity) {
        if (userEntity.getId() != null){
            int num = userDao.editUser(userEntity);
            if (num != 0){
                return "success";
            }
        }
        return "error";
    }
}
