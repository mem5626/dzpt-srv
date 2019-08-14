package com.ourteam.dzpt.mapper;

import com.ourteam.dzpt.entity.Card;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper {

  List<Card> selectCardByUid(int uid);

  int addCard(Card card);

  int deleteById(Integer id);

  int updateCard(Card card);

  int setReceive(Card card);

  void cancelReceive(Integer userId);

  Card getReceive(Integer userId);
}

