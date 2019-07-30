package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.*;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.MessageMapper;
import com.ourteam.dzpt.mapper.TradeBillMapper;
import com.ourteam.dzpt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper usermapper;
    @Autowired
    TradeBillMapper tradeBillMapper;

    public int sendMessage(Message message, Integer uid) {
        if (message.getSender() != uid || message.getReceiver() == 0 && uid != 1)
            throw new GlobalException(ExceptionMsg.NotAllow);
        if (usermapper.selectById(message.getReceiver()).getUserName() == null)
            throw new GlobalException(ExceptionMsg.ParameterError);
        message.setCreateDate(LocalDateTime.now().toString());
        return messageMapper.createMessage(message);
    }

    public List<Message> getMessageList(Integer userId, Integer uid) {
        if (!userId.equals(uid))
            throw new GlobalException(ExceptionMsg.NotAllow);
        return messageMapper.getMessageList(uid);
    }

    public int deleteMessage(Integer messageId, String userName, Integer uid) {
        if (usermapper.selectByName(userName).getId() != uid)
            throw new GlobalException(ExceptionMsg.NotAllow);
        return messageMapper.deleteMessage(messageId);
    }

    public int createSystemMsg(SystemMsg systemMsg){
        systemMsg.setCreateDate(LocalDateTime.now().toString());
        return messageMapper.createSystemMsg(systemMsg);
    }

    public List<SystemMsg> getSystemMsgList(){
        return messageMapper.getSystemMsgList();
    }

    @Transactional
    public int negotiate(Negotiate negotiate, Message message, Integer listGoodsId){
        String createDate = LocalDateTime.now().toString();
        message.setCreateDate(createDate);
        messageMapper.createMessage(message);

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
}
