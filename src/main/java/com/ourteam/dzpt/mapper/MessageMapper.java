package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int createMessage(Message message);

    List<Message> getMessageList(Integer uid, Integer type);

    int deleteMessage(Integer id);
}
