package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.AccountMapper;
import com.ourteam.dzpt.util.MD5Util;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  private AccountMapper accountMapper;

  public int getBalanceByUid(int id) {
    return accountMapper.getBalanceByUid(id);
  }

  public int updateBalance(Account account) {
    return accountMapper.updateAccount(account);
  }

  public Account getAccountByUid(int id) {
    return accountMapper.selectAccountByUid(id);
  }

  public int setPassword(Map<String, String> info) {
    String[] parameters = {info.get("userId"), info.get("password"), info.get("newPassword")};
    for (String parameter : parameters) {
      if (parameter == null || parameter.equals("")) {
        throw new GlobalException(ExceptionMsg.ParameterError);
      }
    }
    Account targetAccount = accountMapper.selectAccountByUid(Integer.parseInt(info.get("userId")));
    System.out.println("当前原密码" + targetAccount.getPayPassword());
    System.out.println("输入的原密码" + info.get("password"));
    System.out.println("设置的新原密码" + info.get("newPassword"));
    if (!MD5Util.stringToMD5(info.get("password")).equals(targetAccount.getPayPassword())) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    } else {
      targetAccount.setPayPassword(MD5Util.stringToMD5(info.get("newPassword")));
    }
    return accountMapper.setPassword(targetAccount);
  }
}
