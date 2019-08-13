package com.ourteam.dzpt.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ourteam.dzpt.entity.Bill;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.BillMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

  @Autowired
  private BillMapper billMapper;

  public List<Bill> selectBillByUid(int id) {
    try {
      return billMapper.selectBillByUid(id);
    } catch (Exception e) {
      System.out.print(e.getMessage());
      throw new GlobalException(ExceptionMsg.Error);
    }
  }

  public int addBill(Map<String,String> info) {
    Bill bill = new Bill();
    bill.setUserId(Integer.parseInt(info.get("userId")));
    bill.setBalance(Integer.parseInt(info.get("balance")));
    bill.setMoney(Integer.parseInt(info.get("money")));
    bill.setDrcrflg(Integer.parseInt(info.get("drcrflg")));
    bill.setTradeType(Integer.parseInt(info.get("tradeType")));
    //后台需要处理少传属性的情况
    if (info.get("tradeWay") != null) {
      bill.setTradeWay(Integer.parseInt(info.get("tradeWay")));
    }
    if (info.get("tradeWayName") != null) {
      bill.setTradeWayName(info.get("tradeWayName"));
    }
    if (info.get("tradeId") != null) {
      bill.setTradeId(Integer.parseInt(info.get("tradeId")));
    }
    bill.setCreateDate(new Date());
    return billMapper.createBill(bill);
  }



}
