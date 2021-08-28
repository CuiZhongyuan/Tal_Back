package com.taltools.service;

import com.taltools.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUser(String name);
}
