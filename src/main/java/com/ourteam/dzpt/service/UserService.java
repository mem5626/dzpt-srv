package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.User;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.UserMapper;
import com.ourteam.dzpt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    public Map getUserInfo(String name){
        if (name == null || name.equals("")) throw new GlobalException(ExceptionMsg.ParameterError);
        return userMapper.getUserInfo(name);
    }

    public User selectByName(String userName) {
        return userMapper.selectByName(userName);
    }

    public List<Map> selectAll(){
        try {
            return userMapper.selectAll();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            throw new GlobalException(ExceptionMsg.Error);
        }
    }

    public int createUser(User user){
        if (selectByName(user.getUserName()) != null) throw new GlobalException(ExceptionMsg.UserNameOccupied);
        user.setPassword(MD5Util.stringToMD5(user.getPassword()));
        user.setCreateDate(LocalDateTime.now().toString());
        return userMapper.create(user);
    }

    public int updatePassword(Map<String, String> info) {
        String[] parameters = {info.get("userName"), info.get("password"), info.get("newPassword")};
        for (String parameter : parameters)
            if (parameter == null || parameter.equals("")) throw new GlobalException(ExceptionMsg.ParameterError);
        User targetUser = selectById(Integer.parseInt(info.get("id")));
        if (!info.get("userName").equals(targetUser.getUserName()) ||
                !MD5Util.stringToMD5(info.get("password")).equals(targetUser.getPassword())) {
            throw new GlobalException(ExceptionMsg.NotAllow);
        } else targetUser.setPassword(MD5Util.stringToMD5(info.get("newPassword")));
        return userMapper.updatePassword(targetUser);
    }

    public int deleteUser(String userName) {
        return userMapper.delete(userName);
    }

    public int updateInfo(User user) {
        return userMapper.updateInfo(user);
    }

    public List<Map> getBanList() {
        return userMapper.getBanList();
    }

    public int banUser(User user) {
        if (user.getId() <= 0 || user.getIfBan() < 0 || user.getIfBan() > 1) throw new GlobalException(ExceptionMsg.ParameterError);
        return userMapper.banUser(user);
    }
}
