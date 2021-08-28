package com.taltools.dao;

import com.taltools.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    List<UserEntity> getAll(Map map);
}
