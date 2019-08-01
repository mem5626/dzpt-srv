package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.ListedGoods;
import com.ourteam.dzpt.entity.Message;
import com.ourteam.dzpt.entity.Order;
import com.ourteam.dzpt.entity.TradeBill;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.ListedGoodsMapper;
import com.ourteam.dzpt.mapper.MessageMapper;
import com.ourteam.dzpt.mapper.OrderMapper;
import com.ourteam.dzpt.mapper.TradeBillMapper;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Autowired
  OrderMapper orderMapper;
  @Autowired
  TradeBillMapper tradeBillMapper;
  @Autowired
  ListedGoodsMapper listedGoodsMapper;
  @Autowired
  MessageMapper messageMapper;

  @Transactional
  public int createOrder(Integer id, Integer buyer) {
    ListedGoods listedGoods = listedGoodsMapper.getListedGoodsInfo(id);
    if (listedGoods.getStatus() == 1) {
      throw new GlobalException(ExceptionMsg.OrderExist);
    }
    Date createDate = new Date();
    TradeBill tradeBill = new TradeBill();
    tradeBill.setBuyer(buyer);
    tradeBill.setSeller(listedGoods.getSupplier());
    tradeBill.setStatus(1);
    tradeBill.setListedGoodsId(id);
    tradeBill.setCreateDate(createDate);

    Order order = new Order();
    order.setAmount(listedGoods.getAmount());
    order.setGoodsName(listedGoods.getGoodsName());
    order.setPrice(listedGoods.getPrice());
    order.setCreateDate(createDate);
    order.setDeposit(listedGoods.getPrice()/50);
    order.setServiceCharge(listedGoods.getPrice()/50);
    order.setStatus(0);

    Message message = new Message();
    message.setCreateDate(createDate);
    message.setContent("有新订单生成，等待您去确认");
    message.setTitle("订单待确认");
    message.setType("订单");
    message.setSender(tradeBill.getBuyer());
    message.setReceiver(tradeBill.getSeller());
    messageMapper.createMessage(message);

    listedGoodsMapper.changeStatus(id, 1);
    tradeBillMapper.createTradeBill(tradeBill);
    order.setTradeBillId(tradeBillMapper.getLastId());
    return orderMapper.createOrder(order);
  }

  @Transactional
  public int cancelOrder(Integer id) {
    tradeBillMapper.cancelTradeBill(orderMapper.selectOrderById(id).getTradeBillId());
    return orderMapper.cancelOrder(id);
  }

  @Transactional
  public Map getOrderInfo(Integer id) {
    return orderMapper.getOrderInfo(id);
  }

  public int affirmOrder(Integer id, Integer uid) {
    Order order = orderMapper.selectOrderById(id);
    if (order == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    TradeBill tradeBill = tradeBillMapper.getTradeInfo(order.getTradeBillId());
    if (order.getStatus() == 1 && tradeBill.getSeller().equals(uid)) {
      return orderMapper.affirmOrder(id, 2);
    }
    if (order.getStatus() == 0 && tradeBill.getBuyer().equals(uid)) {
      return orderMapper.affirmOrder(id, 1);
    }
    throw new GlobalException(ExceptionMsg.NotAllow);
  }
}
