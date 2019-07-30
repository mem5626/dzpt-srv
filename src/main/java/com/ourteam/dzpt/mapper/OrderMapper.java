package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OrderMapper {
    int createOrder(Order order);
    int cancelOrder(Integer id);
    Map getOrderInfo(Integer id);
    int affirmOrder(Integer id,Integer status);
    Order selectOrderById(Integer id);
}
