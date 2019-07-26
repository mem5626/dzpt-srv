package com.ourteam.dzpt.service;

import com.ourteam.dzpt.entity.Card;
import com.ourteam.dzpt.mapper.CardMapper;
import com.ourteam.dzpt.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;

    public List<Card> getCardList(int id){return cardMapper.selectCardByUid(id);}

    public int addCard(Card card){
        card.setBindTime(new Date());
        return cardMapper.addCard(card);
    }

    public int deleteCard(int id){
        return cardMapper.deleteById(id);
    }
}
