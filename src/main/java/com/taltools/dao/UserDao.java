package com.taltools.dao;

import com.taltools.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {
    User getUser(Map map);
}
