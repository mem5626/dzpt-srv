package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Card;
import com.ourteam.dzpt.mapper.CardMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

  @Autowired
  private CardMapper cardMapper;

  public List<Card> getCardList(int id) {
    return cardMapper.selectCardByUid(id);
  }

  public int addCard(Card card) {

    card.setBindTime(new Date());
    return cardMapper.addCard(card);
  }

  public int deleteCard(int id) {
    return cardMapper.deleteById(id);
  }
}
