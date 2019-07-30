package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.ListedGoods;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.ListedGoodsMapper;
import com.ourteam.dzpt.mapper.UserMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListedGoodsService {

  @Autowired
  ListedGoodsMapper listedGoodsMapper;
  @Autowired
  UserMapper userMapper;

  public int changeHangInfo(ListedGoods listedGoods, Integer uid) {
    Integer supplier = listedGoodsMapper.searchSupplierById(listedGoods.getId());
    if (supplier == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    if (!supplier.equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    return listedGoodsMapper.changeHangInfo(listedGoods);
  }

  public int hangNow(ListedGoods listedGoods, Integer uid) {
    if (!listedGoods.getSupplier().equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    listedGoods.setCreateDate(LocalDateTime.now().toString());
    return listedGoodsMapper.hangNow(listedGoods);
  }

  public int deleteHangGood(int listedGoodsId, Integer uid) {
    if (!listedGoodsMapper.searchSupplierById(listedGoodsId).equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    return listedGoodsMapper.deleteHangGood(listedGoodsId);
  }

  public List<Map> getHangListByType(String hangtype) {
    return listedGoodsMapper.getHangListByType(hangtype);
  }

  public List<Map> getMyHangList(Integer userId, int uid) {
    if (userId != uid) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    return listedGoodsMapper.getMyHangList(uid);
  }

  public Map searchHangGood(int id) {
    Map map = listedGoodsMapper.getListedGoodsInfoByMap(id);
    map.put("supplierName", userMapper.selectById((Integer) map.get("supplier")).getUserName());
    return map;
  }
}
