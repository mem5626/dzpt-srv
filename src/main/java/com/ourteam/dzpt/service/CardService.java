package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Card;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.mapper.CardMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardService {

  @Autowired
  private CardMapper cardMapper;

  public List<Card> getCardList(int id) {
    return cardMapper.selectCardByUid(id);
  }

  public int addCard(Card card) {
    card.setBindTime(new Date());
    if (cardMapper.getReceive(card.getUserId()).getBank() == null){
      card.setReceive(1);
    }
    return cardMapper.addCard(card);
  }

  public int deleteCard(int id) {
    return cardMapper.deleteById(id);
  }

  public void setReceiveCard(Card card){
    cardMapper.cancelReceive(card.getUserId());
    if (cardMapper.setReceive(card) != 1){
      throw new GlobalException(ExceptionMsg.Error,"设置默认卡号错误");
    }
  }

  public Card getReceiveCard(Integer userId){
    return cardMapper.getReceive(userId);
  }

}
