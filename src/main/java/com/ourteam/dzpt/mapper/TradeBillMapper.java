package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.TradeBill;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeBillMapper {

  int createTradeBill(TradeBill tradeBill);

  TradeBill getTradeInfo(Integer id);

  List<TradeBill> getTradeBill();

  List<Map> getMyTrading(Integer uid);

  int getLastId();

  int cancelTradeBill(Integer id);
}
