package com.ourteam.dzpt.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.entity.Bill;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.exception.GlobalExceptionHandler;
import com.ourteam.dzpt.mapper.AccountMapper;
import com.ourteam.dzpt.util.MD5Util;
import com.ourteam.dzpt.util.NetUtil;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
      throw new GlobalException(ExceptionMsg.PasswordError);
    } else {
      targetAccount.setPayPassword(MD5Util.stringToMD5(info.get("newPassword")));
    }
    return accountMapper.setPassword(targetAccount);
  }

  public String getBillByUserId(String userId) {
    HashMap<String,String> map=new HashMap<>();
    map.put("userId","1");
    Response response= NetUtil.getRequest("/mine/getBill",map);
    return response.getCode();
  }


  public String testPostAddCard(String userId) {
    HashMap<String,String> map=new HashMap<>();
    map.put("userId","1");
    map.put("cardNumber","7777777");
    map.put("bank","bankbankbank");
    Response response=NetUtil.postRequest("/mine/addCard",map);
    return response.getCode();
  }

  public Boolean verifyPayPassword(HashMap<String, String> info){
    Account targetAccount = accountMapper.selectAccountByUid(Integer.parseInt(info.get("userId")));
    return MD5Util.stringToMD5(info.get("password")).equals(targetAccount.getPayPassword());
  }
}
