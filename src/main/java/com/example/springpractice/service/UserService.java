package com.example.springpractice.service;

import com.example.springpractice.dto.User;
import com.example.springpractice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int join(User user){
        return userMapper.insertUser(user);
    }

    public User login(User user){
        return userMapper.loginUser(user);
    }

    public int update(User user){
        return userMapper.updateUser(user);
    }

    public int delete(User user){
        return userMapper.deleteUser(user);
    }
}
