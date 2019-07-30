package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.TradeBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradeBillMapper {
    int createTradeBill(TradeBill tradeBill);
    TradeBill getTradeInfo(Integer id);
    List<TradeBill> getTradeBill();
    List<Map> getMyTrading(Integer uid);
    int getLastId();
    int cancelTradeBill(Integer id);
}
