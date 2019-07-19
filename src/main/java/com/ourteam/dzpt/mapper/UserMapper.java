package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(int id);
    User selectByName(String name);
    List<User> selectAll();
    int delete(int id);
    int create(User user);
    int updateInfo(User user);
}
