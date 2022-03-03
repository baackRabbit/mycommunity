package com.glodon.mycommunity.service;

import com.glodon.mycommunity.dao.UserMapper;
import com.glodon.mycommunity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User selectUserById(int userId){
        return userMapper.selectUserById(userId);
    }
}
