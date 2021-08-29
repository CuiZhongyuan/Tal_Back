package com.taltools.service;

import com.taltools.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserEntity> getQueryValue(String name);
    List<UserEntity> getAll();
}
