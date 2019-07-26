package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.Card;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.AccountService;
import com.ourteam.dzpt.service.BillService;
import com.ourteam.dzpt.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CardService cardService;
    @Autowired
    private BillService billService;


    @RequestMapping(value = "/mine/getAccount", method = RequestMethod.GET)
    public Response getAccountInfoById(String userId) throws GlobalException{
        int Id=Integer.parseInt(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("cardList", cardService.getCardList(Id));
        map.put("balance", accountService.getBalanceByUid(Id));
        return new Response(ExceptionMsg.Success,map);
    }

    @RequestMapping(value = "/mine/addCard", method = RequestMethod.POST)
    public Response AddCard(HttpServletRequest request, @RequestBody Card card) throws GlobalException{
        if ((cardService.addCard(card))== 1)
            return new Response(ExceptionMsg.Success);
        else return new Response(ExceptionMsg.Error);
    }

    @RequestMapping(value = "/mine/deleteCard",method = RequestMethod.POST)
    public Response DeleteCard(HttpServletRequest request, @RequestBody String id) throws GlobalException{
        int Id=Integer.parseInt(id);
        if((cardService.deleteCard(Id))== 1)
            return new Response(ExceptionMsg.Success);
        else return new Response(ExceptionMsg.Error);
    }

    @RequestMapping(value = "/mine/getBill", method = RequestMethod.GET)
    public Response getBillById(String userId) throws GlobalException{
        int id=Integer.parseInt(userId);
        return new Response(ExceptionMsg.Success,billService.selectBillByUid(id));
    }




}
