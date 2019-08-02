package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.ListedGoods;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.ListedGoodsMapper;
import com.ourteam.dzpt.mapper.UserMapper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ListedGoodsService {

  @Autowired
  ListedGoodsMapper listedGoodsMapper;
  @Autowired
  UserMapper userMapper;

  public void changeHangInfo(ListedGoods listedGoods, Integer uid) {
    Integer supplier = listedGoodsMapper.searchSupplierById(listedGoods.getId());
    if (supplier == null) {
      throw new GlobalException(ExceptionMsg.ListedGoodsNotExist, "修改挂牌找不到对应挂牌消息");
    }
    if (!supplier.equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    int result = listedGoodsMapper.changeHangInfo(listedGoods);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "修改挂牌信息数异常，返回值为" + result);
    }
  }

  public void hangNow(ListedGoods listedGoods, Integer uid) {
    if (!listedGoods.getSupplier().equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    listedGoods.setCreateDate(new Date());
    int result = listedGoodsMapper.hangNow(listedGoods);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "插入挂牌信息异常，返回值为" + result);
    }
  }

  public void deleteHangGood(int listedGoodsId, Integer uid) {
    Integer supplier = listedGoodsMapper.searchSupplierById(listedGoodsId);
    if (supplier == null) {
      throw new GlobalException(ExceptionMsg.ListedGoodsNotExist, "删除挂牌找不到对应挂牌消息");
    }
    if (!supplier.equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    int result = listedGoodsMapper.deleteHangGood(listedGoodsId);
    if (result != 1) {
      throw new GlobalException(ExceptionMsg.Error, "删除挂牌信息异常，返回值为" + result);
    }
  }

  public List<Map> getHangListByType(String hangtype) {
    return listedGoodsMapper.getHangListByType(hangtype);
  }

  public List<Map> getMyHangList(int uid) {
    return listedGoodsMapper.getMyHangList(uid);
  }

  public Map searchHangGood(int id) {
    Map map = listedGoodsMapper.getListedGoodsInfoByMap(id);
    if (map == null) {
      throw new GlobalException(ExceptionMsg.ListedGoodsNotExist);
    }
    map.put("supplierName", userMapper.selectById((Integer) map.get("supplier")).getUserName());
    return map;
  }
}
