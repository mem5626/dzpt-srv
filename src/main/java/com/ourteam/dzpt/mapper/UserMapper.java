package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

  User selectById(int id);

  User selectByName(String name);

  Map getUserInfo(String name);

  List<Map> selectAll();

  int delete(int id);

  int create(User user);

  int updateInfo(User user);

  int updatePassword(User user);

  List<Map> getBanList();

  int banUser(User user);
}
