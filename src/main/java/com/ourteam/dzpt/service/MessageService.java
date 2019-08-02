package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.ListedGoods;
import com.ourteam.dzpt.entity.Message;
import com.ourteam.dzpt.entity.Negotiate;
import com.ourteam.dzpt.entity.Order;
import com.ourteam.dzpt.entity.SystemMsg;
import com.ourteam.dzpt.entity.TradeBill;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.ListedGoodsMapper;
import com.ourteam.dzpt.mapper.MessageMapper;
import com.ourteam.dzpt.mapper.OrderMapper;
import com.ourteam.dzpt.mapper.TradeBillMapper;
import com.ourteam.dzpt.mapper.UserMapper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService {

  @Autowired
  MessageMapper messageMapper;
  @Autowired
  UserMapper usermapper;
  @Autowired
  TradeBillMapper tradeBillMapper;
  @Autowired
  ListedGoodsMapper listedGoodsMapper;
  @Autowired
  OrderMapper orderMapper;

  public int sendMessage(Message message, Integer uid) {
    if (message.getSender() != uid || message.getReceiver() == 0 && uid != 1) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    if (usermapper.selectById(message.getReceiver()).getUserName() == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    message.setCreateDate(new Date());
    return messageMapper.createMessage(message);
  }

  public List<Message> getMessageList(Integer userId, Integer uid) {
    if (!userId.equals(uid)) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    return messageMapper.getMessageList(uid);
  }

  public int deleteMessage(Integer messageId, Integer uid) {
    return messageMapper.deleteMessage(messageId);
  }

  public int setReadMessage(Integer messageId) {

    return messageMapper.setReadMessage(messageId);
  }

  public int createSystemMsg(SystemMsg systemMsg) {
    systemMsg.setCreateDate(new Date());
    return messageMapper.createSystemMsg(systemMsg);
  }

  public List<SystemMsg> getSystemMsgList() {
    return messageMapper.getSystemMsgList();
  }

  @Transactional
  public int negotiate(Negotiate negotiate, Message message, Integer listGoodsId) {
    Date createDate = new Date();
    message.setCreateDate(createDate);
//    if(tradeBillMapper.getNowStatus(listGoodsId)>0)
//      throw new GlobalException(ExceptionMsg.Error);
//    if(tradeBillMapper.countInNegotiateNum(message.getSender(),listGoodsId)!=0)
//      throw new GlobalException(ExceptionMsg.InNegotiate);
    TradeBill tradeBill = new TradeBill();
    tradeBill.setBuyer(message.getSender());
    tradeBill.setSeller(message.getReceiver());
    tradeBill.setStatus(0);
    tradeBill.setListedGoodsId(listGoodsId);
    tradeBill.setCreateDate(createDate);
    tradeBillMapper.createTradeBill(tradeBill);
    int tradeBillId = tradeBillMapper.getLastId();

    negotiate.setTradeBillId(tradeBillId);
    negotiate.setCreateDate(createDate);
    negotiate.setStatus(0);
    messageMapper.createNegotiate(negotiate);
    return messageMapper.createMessage(message);
  }

  public Map getNegotiate(Integer tradingId) {
    return messageMapper.getNegotiate(tradingId);
  }

  @Transactional
  public int affirmNegotiatedId(Integer tradingId, Integer negotiateId) {
    TradeBill tradeBill = tradeBillMapper.getTradeInfo(tradingId);
    Negotiate negotiate = messageMapper.selectNegotiateById(negotiateId);
    ListedGoods listedGoods = listedGoodsMapper.getListedGoodsInfo(tradeBill.getListedGoodsId());
    if (tradeBill.getStatus() != 0 || negotiate.getStatus() != 0 || listedGoods.getStatus() != 0) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }

    Order order = new Order();
    order.setTradeBillId(tradingId);
    order.setStatus(0);
    order.setCreateDate(new Date());
    order.setGoodsName(listedGoods.getGoodsName());
    order.setAmount(listedGoods.getAmount());
    order.setPrice(negotiate.getCurrentPrice());
    order.setDeposit(negotiate.getCurrentPrice() / 50);
    order.setServiceCharge(negotiate.getCurrentPrice() / 50);

    listedGoodsMapper.changeStatus(tradeBill.getListedGoodsId(), 1);
    tradeBillMapper.setTradeBillStatus(1, tradingId);
    messageMapper.setNegotiateStatus(1, negotiateId);
    return orderMapper.createOrder(order);
  }

  @Transactional
  public int refuseNegotiatedId(Integer tradingId, Integer negotiateId) {
    TradeBill tradeBill = tradeBillMapper.getTradeInfo(tradingId);
    Negotiate negotiate = messageMapper.selectNegotiateById(negotiateId);
    if (negotiate.getStatus() != 0 || tradeBill.getStatus() != 0) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    tradeBillMapper.setTradeBillStatus(-1, tradingId);
    return messageMapper.setNegotiateStatus(-1, negotiateId);
  }

}
