package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account selectAccountByUid(int uid);
    int createAccount(Account account);
    int setPassword(Account account);
    int updateAccount(Account account);
}
