package com.taltools.dao;

import com.taltools.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    List<UserEntity> getQueryValue(String name,String school,String createTime);
    List<UserEntity> getAll();
    void addUser(UserEntity userEntity);
    int deleteUser(String id);
    int editUser(UserEntity userEntity);
}
