package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.AccountMapper;
import com.ourteam.dzpt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public int getBalanceByUid(int id){return accountMapper.getBalanceByUid(id);}

    public int updateBalance(Account account) {return accountMapper.updateAccount(account);}

    public Account getAccountByUid(int id){return  accountMapper.selectAccountByUid(id);}

    public int setPassword(Map<String, String> info){
        String[] parameters = {info.get("userId"), info.get("password"), info.get("newPassword")};
        for (String parameter : parameters)
            if (parameter == null || parameter.equals("")) throw new GlobalException(ExceptionMsg.ParameterError);
        Account targetAccount = accountMapper.selectAccountByUid(Integer.parseInt(info.get("userId")));
        System.out.println("原密码"+targetAccount.getPayPassword());
        System.out.println("输入密码"+info.get("password"));
        //未md5
        if (!info.get("password").equals(targetAccount.getPayPassword())) {
            throw new GlobalException(ExceptionMsg.NotAllow);
            //未md5
        } else targetAccount.setPayPassword(info.get("newPassword"));
        return accountMapper.setPassword(targetAccount);}
}
