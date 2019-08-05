package com.ourteam.dzpt.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.entity.Card;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.AccountService;
import com.ourteam.dzpt.service.BillService;
import com.ourteam.dzpt.service.CardService;
import com.ourteam.dzpt.util.MD5Util;
import java.lang.reflect.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    @RequestMapping(value = "/mine/addCard", method = RequestMethod.POST)
    public Response AddCard(HttpServletRequest request, @RequestBody Card card) throws GlobalException{
        if ((cardService.addCard(card))== 1)
        {   logger.info(accountService.getBillByUserId("1"));
            return new Response(ExceptionMsg.Success);}
        else return new Response(ExceptionMsg.Error);
    }

    @RequestMapping(value = "/mine/deleteCard",method = RequestMethod.POST)
        public Response DeleteCard(HttpServletRequest request, @RequestBody Map<String,String> info) throws GlobalException{
        int Id=Integer.parseInt(info.get("id"));
        if((cardService.deleteCard(Id))== 1)
            return new Response(ExceptionMsg.Success);
        else return new Response(ExceptionMsg.Error);
    }

    @RequestMapping(value = "/mine/getBill", method = RequestMethod.GET)
    public Response getBillById(String userId) throws GlobalException{
        int id=Integer.parseInt(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("billList", billService.selectBillByUid(id));
        return new Response(ExceptionMsg.Success,map);
    }

    @RequestMapping(value = "/account/verify", method = RequestMethod.POST)
    public Response verifyPassword(HttpServletRequest request,@RequestBody Map<String,String> info ) throws GlobalException{
        if(accountService.verifyPayPassword(info))return new Response(ExceptionMsg.Success);
        else throw new GlobalException(ExceptionMsg.PasswordError);
    }

    @RequestMapping(value = "/account/updatePassword", method = RequestMethod.POST)
    public Response updatePassword(HttpServletRequest request, @RequestBody Map<String, String> info) throws GlobalException {
        if (accountService.setPassword(info) == 0) return new Response(ExceptionMsg.Error);
        else return new Response(ExceptionMsg.Success);
    }




}
