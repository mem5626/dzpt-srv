package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.MyCar;
import com.ourteam.dzpt.entity.User;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.AccountMapper;
import com.ourteam.dzpt.mapper.ListedGoodsMapper;
import com.ourteam.dzpt.mapper.MyCarMapper;
import com.ourteam.dzpt.mapper.UserMapper;
import com.ourteam.dzpt.util.MD5Util;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private MyCarMapper myCarMapper;
  @Autowired
  private AccountMapper accountMapper;
  @Autowired
  private ListedGoodsMapper listedGoodsMapper;

  public User selectById(int id) {
    return userMapper.selectById(id);
  }

  public Map getUserInfo(int id) {
    return userMapper.getUserInfo(id);
  }

  public User selectByName(String userName) {
    return userMapper.selectByName(userName);
  }

  public List<Map> selectAll() {
    try {
      return userMapper.selectAll();
    } catch (Exception e) {
      System.out.print(e.getMessage());
      throw new GlobalException(ExceptionMsg.Error);
    }
  }

  public void createUser(User user) {
    if (selectByName(user.getUserName()) != null) {
      throw new GlobalException(ExceptionMsg.UserNameOccupied);
    }
    user.setPassword(MD5Util.stringToMD5(user.getPassword()));
    user.setCreateDate(new Date());
    userMapper.create(user);
    Account account = new Account();
    account.setUserId(userMapper.getLastUserId());
    account.setPayPassword(MD5Util.stringToMD5("dzpt"));
    account.setBalance(0);
    int result = accountMapper.createAccount(account);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "注册影响用户数异常，为" + result);
    }
  }

  public int login(User user) {
    User targetUser = userMapper.selectByName(user.getUserName());
    if (targetUser == null) {
      throw new GlobalException(ExceptionMsg.UserNotExist);
    }
    if (!targetUser.getPassword().equals(MD5Util.stringToMD5(user.getPassword()))) {
      throw new GlobalException(ExceptionMsg.PasswordError);
    }
    if (targetUser.getIfBan() != 0){
      throw new GlobalException(ExceptionMsg.HasBeenBan);
    }
    return targetUser.getId();
  }


  public void updatePassword(Map<String, String> info) {
    String[] parameters = {info.get("userName"), info.get("password"), info.get("newPassword")};
    for (String parameter : parameters) {
      if (parameter == null || parameter.equals("")) {
        throw new GlobalException(ExceptionMsg.ParameterError);
      }
    }
    User targetUser = selectById(Integer.parseInt(info.get("id")));
    if (!info.get("userName").equals(targetUser.getUserName()) ||
        !MD5Util.stringToMD5(info.get("password")).equals(targetUser.getPassword())) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    } else {
      targetUser.setPassword(MD5Util.stringToMD5(info.get("newPassword")));
    }
    int result = userMapper.updatePassword(targetUser);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "修改用户密码返回参数错误，为" + result);
    }
  }

  public void updateInfo(User user) {
    int result = userMapper.updateInfo(user);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "更新用户返回值异常，为" + result);
    }
  }

  public List<Map> getBanList() {
    return userMapper.getBanList();
  }

  public void banUser(User user) {
    int result = userMapper.banUser(user);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "封禁用户返回参数异常，为" + result);
    }
  }

  public void deleteByGoodsId(MyCar myCar, Integer uid) {
    if (!myCar.getUserId().equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    int result = myCarMapper.deleteByGoodsId(myCar);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "删除购物车商品返回参数异常，为" + result);
    }
  }

  public void insertIntoMyCar(MyCar myCar, Integer uid) {
    if (!myCar.getUserId().equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    if (listedGoodsMapper.getListedGoodsInfo(myCar.getListedGoodsId()).getStatus() != 0) {
      throw new GlobalException(ExceptionMsg.AddMyCarError);
    }
    myCar.setCreateDate(new Date());
    int result = myCarMapper.insert(myCar);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "加入购物车返回参数异常，为" + result);
    }
  }

  public List<Map> getMyCar(Integer userId) {
    return myCarMapper.getMyCar(userId);
  }
}
