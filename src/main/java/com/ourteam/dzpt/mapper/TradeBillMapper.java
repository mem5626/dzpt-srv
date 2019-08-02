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

  int setTradeBillStatus(Integer status, Integer id);

  int countInNegotiateNum(Integer buyer, Integer listedGoodsId);

  int getNowStatus(Integer listedGoodsId);

  TradeBill selectTradeByGoodsId(Integer listedGoodsId);
}
