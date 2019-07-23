package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.MyCar;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MyCarMapper {
    int deleteByGoodsId(Integer listedGoodsId);
    int insert(MyCar myCar);
}
