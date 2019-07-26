package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Message;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.MessageMapper;
import com.ourteam.dzpt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper usermapper;

    public int sendMessage(Message message, Integer uid) {
        if (message.getSender() != uid || message.getSender() != 0 && uid == 1)
            throw new GlobalException(ExceptionMsg.NotAllow);
        if (usermapper.selectById(message.getReceiver()).getUserName() == null)
            throw new GlobalException(ExceptionMsg.ParameterError);
        message.setCreateDate(LocalDateTime.now().toString());
        return messageMapper.createMessage(message);
    }

    public List<Message> getMessageList(String userName, Integer uid, Integer type) {
        if (usermapper.selectByName(userName).getId() != uid)
            throw new GlobalException(ExceptionMsg.NotAllow);
        return messageMapper.getMessageList(uid, type);
    }

    public int deleteMessage(Integer messageId, String userName, Integer uid) {
        if (usermapper.selectByName(userName).getId() != uid)
            throw new GlobalException(ExceptionMsg.NotAllow);
        return messageMapper.deleteMessage(messageId);
    }
}
