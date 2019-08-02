package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.DeliveryBill;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.DeliveryBillMapper;
import com.ourteam.dzpt.mapper.TradeBillMapper;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

  @Autowired
  private DeliveryBillMapper deliveryBillMapper;
  @Autowired
  private TradeBillMapper tradeBillMapper;

  public int createDelivery(DeliveryBill deliveryBill) {
    if (getDeliveryInfo(deliveryBill.getTradeBillId()) != null) {
      throw new GlobalException(ExceptionMsg.DeliveryExit);
    }
    tradeBillMapper.setTradeBillStatus(3,deliveryBill.getTradeBillId());
    deliveryBill.setStatus(0);
    deliveryBill.setCreateDate(LocalDateTime.now().toString());
    return deliveryBillMapper.createDelivery(deliveryBill);
  }

  public DeliveryBill getDeliveryInfo(int tradeBillId) {
    return deliveryBillMapper.getDeliveryInfo(tradeBillId);
  }

  public int ifDeliver(Integer listedGoodsId) {
    return deliveryBillMapper.ifDeliver(listedGoodsId);
  }

  public int ifReceive(Integer listedGoodsId) {
    tradeBillMapper.setTradeBillStatus(4,tradeBillMapper.selectTradeByGoodsId(listedGoodsId).getId());
    return deliveryBillMapper.ifReceive(listedGoodsId);
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
