package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Message;
import com.ourteam.dzpt.entity.Negotiate;
import com.ourteam.dzpt.entity.SystemMsg;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int createMessage(Message message);

    List<Message> getMessageList(Integer uid);

    int deleteMessage(Integer id);

    int setReadMessage(Integer id);

    Message getMessageInfo(Integer id);

    int createSystemMsg(SystemMsg systemMsg);

    List<SystemMsg> getSystemMsgList();

    int updateSystemMsg(SystemMsg systemMsg);

    int deleteSystemMsg(Integer systemMsgId);

    int createNegotiate(Negotiate negotiate);

    Map getNegotiate(Integer tradingId);

    Negotiate selectNegotiateById(Integer id);

    int setNegotiateStatus(Integer status, Integer negotiateId);
}
