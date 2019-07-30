package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.TradeBill;
import com.ourteam.dzpt.mapper.TradeBillMapper;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeBillService {

  @Autowired
  TradeBillMapper tradeBillMapper;

  public int createTradeBill(TradeBill tradeBill) {
    return tradeBillMapper.createTradeBill(tradeBill);
  }

  public TradeBill getTradeBillInfo(Integer id) {
    return tradeBillMapper.getTradeInfo(id);
  }

  public List<TradeBill> getTradeBills() {
    return tradeBillMapper.getTradeBill();
  }

  public List<Map> getMyTrading(Integer uid) {
    return tradeBillMapper.getMyTrading(uid);
  }
}
