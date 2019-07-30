package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.DeliveryBill;
import com.ourteam.dzpt.mapper.DeliveryBillMapper;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

  @Autowired
  private DeliveryBillMapper deliveryBillMapper;

  public int createDelivery(DeliveryBill deliveryBill) {
    deliveryBill.setStatus(0);
    deliveryBill.setCreateDate(LocalDateTime.now().toString());
    return deliveryBillMapper.createDelivery(deliveryBill);
  }

  public Map selectDeliveryByTid(int tid) {
    return deliveryBillMapper.selectDeliveryByTid(tid);
  }

  public int ifDeliver(DeliveryBill deliveryBill) {
    deliveryBill.setStatus(1);
    return deliveryBillMapper.ifDeliver(deliveryBill);
  }

  public int ifReceive(DeliveryBill deliveryBill) {
    deliveryBill.setStatus(2);
    return deliveryBillMapper.ifReceive(deliveryBill);
  }

  public int requireReturn(DeliveryBill deliveryBill) {
    if (deliveryBill.getIsReturn() == true) {
      deliveryBill.setStatus(3);
    }
    return deliveryBillMapper.requireReturn(deliveryBill);
  }

  public int agreeReturn(DeliveryBill deliveryBill) {
    if (deliveryBill.getIsAgree() == true) {
      deliveryBill.setStatus(4);
    }
    return deliveryBillMapper.agreeReturn(deliveryBill);
  }

  public int confirmReturn(DeliveryBill deliveryBill) {
    if (deliveryBill.getIsConfirm() == true) {
      deliveryBill.setStatus(4);
    }
    return deliveryBillMapper.confirmReturn(deliveryBill);
  }
}
