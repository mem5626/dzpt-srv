package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.ListedGoods;
import com.ourteam.dzpt.entity.User;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.ListedGoodsMapper;
import com.ourteam.dzpt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ListedGoodsService {
    @Autowired
    ListedGoodsMapper listedGoodsMapper;
    @Autowired
    UserMapper userMapper;

    public int changeHangInfo(ListedGoods listedGoods, Integer uid) {
        Integer supplier = listedGoodsMapper.searchSupplierById(listedGoods.getId());
        if (supplier == null) throw new GlobalException(ExceptionMsg.ParameterError);
        if (!supplier.equals(uid))
            throw new GlobalException(ExceptionMsg.NotAllow);
        return listedGoodsMapper.changeHangInfo(listedGoods);
    }

    public int hangNow(ListedGoods listedGoods, Integer uid) {
        if (!listedGoods.getSupplier().equals(uid))
            throw new GlobalException(ExceptionMsg.NotAllow);
        listedGoods.setCreateDate(LocalDateTime.now().toString());
        return listedGoodsMapper.hangNow(listedGoods);
    }

    public int deleteHangGood(int listedGoodsId, Integer uid) {
        if (listedGoodsMapper.searchSupplierById(listedGoodsId) != uid)
            throw new GlobalException(ExceptionMsg.NotAllow);
        return listedGoodsMapper.deleteHangGood(listedGoodsId);
    }

    public List<Map> getHangListByType(int hangtype) throws Exception {
        return listedGoodsMapper.getHangListByType(hangtype);
    }

    public List<Map> getMyHangList(String userName, int uid) throws Exception {
        User user = userMapper.selectByName(userName);
        if (user.getId() <= 0) throw new GlobalException(ExceptionMsg.ParameterError);
        if (user.getId() != uid) throw new GlobalException(ExceptionMsg.NotAllow);
        return listedGoodsMapper.getMyHangList(uid);
    }
}
