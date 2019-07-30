package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.MyCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface MyCarMapper {
    int deleteByGoodsId(MyCar myCar);
    int insert(MyCar myCar);
    List<Map> getMyCar(Integer userId);
}
