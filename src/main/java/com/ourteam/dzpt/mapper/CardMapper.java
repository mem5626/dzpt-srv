package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardMapper{
    List<Card> selectCardByUid(int uid);
    int createCard(Card card);
    int deleteById(Integer id);
    int updateCard(Card card);
}

