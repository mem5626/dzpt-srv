package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.MyCar;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MyCarMapper {

  int deleteByGoodsId(MyCar myCar);

  int insert(MyCar myCar);

  List<Map> getMyCar(Integer userId);
}
