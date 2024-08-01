package com.example.springpractice.mapper;

import com.example.springpractice.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    User selectUser(User user);
    int updateUser(User user);
    int deleteUser(User user);
}
