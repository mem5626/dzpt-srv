package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public int getBalanceByUid(int id){return accountMapper.getBalanceByUid(id);}

    public Account getAccountByUid(int id){return  accountMapper.selectAccountByUid(id);}


}
