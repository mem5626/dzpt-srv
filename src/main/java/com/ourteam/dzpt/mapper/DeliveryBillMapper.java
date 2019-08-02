package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.DeliveryBill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryBillMapper {

  DeliveryBill getDeliveryInfo(int tradeBillId);

  int createDelivery(DeliveryBill deliveryBill);

  int ifReceive(Integer listedGoodsId);

  int ifDeliver(Integer listedGoodsId);

  int requireReturn(DeliveryBill deliveryBill);

  int agreeReturn(DeliveryBill deliveryBill);

  int confirmReturn(DeliveryBill deliveryBill);
}
