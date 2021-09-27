package com.taltools.service;

import com.taltools.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserEntity> getQueryValue(String name,String school,String createTime);
    List<UserEntity> getAll();
    void addUser(UserEntity body);

    String deleteUser(String id);
}
