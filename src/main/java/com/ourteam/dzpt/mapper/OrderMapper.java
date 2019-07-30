package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Order;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

  int createOrder(Order order);

  int cancelOrder(Integer id);

  Map getOrderInfo(Integer id);

  int affirmOrder(Integer id, Integer status);

  Order selectOrderById(Integer id);
}
