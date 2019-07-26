package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.ListedGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ListedGoodsMapper {
    Integer changeHangInfo(ListedGoods listedGoods);

    Integer hangNow(ListedGoods listedGoods);

    Integer deleteHangGood(int listedGoodsId);

    Integer searchSupplierById(int id);

    List<Map> getHangListByType(int hangtype);

    List<Map> getMyHangList(int supplier);
}
