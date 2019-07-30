package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.DeliveryBill;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliveryBillMapper {

  Map selectDeliveryByTid(int tid);

  int createDelivery(DeliveryBill deliveryBill);

  int ifReceive(DeliveryBill deliveryBill);

  int ifDeliver(DeliveryBill deliveryBill);

  int requireReturn(DeliveryBill deliveryBill);

  int agreeReturn(DeliveryBill deliveryBill);

  int confirmReturn(DeliveryBill deliveryBill);
}
