package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.ListedGoods;
import java.util.Map;

public interface ListedGoodsMapper {
    int changeHangInfo(ListedGoods listedGoods);
    int hangNow(ListedGoods listedGoods);
    int deleteHangGood(String listedGoodsId);
    Map getHangListByType(int hangtype);
    Map getMyHangList(int supplier);
}
