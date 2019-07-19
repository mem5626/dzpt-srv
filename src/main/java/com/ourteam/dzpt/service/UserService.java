package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.User;
import com.ourteam.dzpt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User selectById(int id){
        return userMapper.selectById(id);
    }
    public User selectByName(String userName){
        return userMapper.selectByName(userName);
    }
    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    public int createUser(User user){
        return userMapper.create(user);
    }
    public int deleteUser(int id){
        return userMapper.delete(id);
    }
    public int updateInfo(User user){
        return userMapper.updateInfo(user);
    }
}
