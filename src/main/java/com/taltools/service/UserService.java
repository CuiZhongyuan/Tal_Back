package com.taltools.service;

import com.taltools.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    List<UserEntity> getQueryValue(String name,String school,String createTime,String id);
    List<UserEntity> getAll();
    void addUser(UserEntity body);

    String deleteUser(String id);

    String editUser(UserEntity req);

    List<UserEntity> getId(String id);

    Map testGroovy(String req);
}
