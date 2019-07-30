package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Message;
import com.ourteam.dzpt.entity.Negotiate;
import com.ourteam.dzpt.entity.SystemMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int createMessage(Message message);

    List<Message> getMessageList(Integer uid);

    int deleteMessage(Integer id);

    int createSystemMsg(SystemMsg systemMsg);

    List<SystemMsg> getSystemMsgList();

    int createNegotiate(Negotiate negotiate);
}
