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
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private CardService cardService;
  @Autowired
  private BillService billService;


  @RequestMapping(value = "/mine/getAccount", method = RequestMethod.GET)
  public Response getAccountInfoById(String userId) throws GlobalException {
    int Id = Integer.parseInt(userId);
    Map<String, Object> map = new HashMap<>();
    map.put("cardList", cardService.getCardList(Id));
    map.put("balance", accountService.getBalanceByUid(Id));
    return new Response(ExceptionMsg.Success, map);
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @RequestMapping(value = "/mine/addCard", method = RequestMethod.POST)
  public Response AddCard(HttpServletRequest request, @RequestBody Card card)
      throws GlobalException {
    if ((cardService.addCard(card)) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/mine/deleteCard", method = RequestMethod.POST)
  public Response DeleteCard(HttpServletRequest request, @RequestBody Map<String, String> info)
      throws GlobalException {
    int Id = Integer.parseInt(info.get("id"));
    if ((cardService.deleteCard(Id)) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/mine/getBill", method = RequestMethod.GET)
  public Response getBillById(String userId) throws GlobalException {
    int id = Integer.parseInt(userId);
    return new Response(ExceptionMsg.Success, billService.selectBillByUid(id));
  }

  @RequestMapping(value = "/account/verify", method = RequestMethod.POST)
  public Response verifyPassword(HttpServletRequest request,
      @Validated(value = Account.Default.class) @RequestBody Account account,
      BindingResult br) throws GlobalException {
    if (br.hasErrors()) {
      return new Response("E0001", br.getFieldError().getDefaultMessage());
    }
    Account targetAccount = accountService.getAccountByUid(account.getUserId());
    if (targetAccount == null) {
      return new Response(ExceptionMsg.UserNotExist);
    } else {
      String Md5Password = MD5Util.stringToMD5(account.getPayPassword());
      //判断密码是否正确
      if (targetAccount.getPayPassword().equals(Md5Password)) {
        return new Response(ExceptionMsg.Success);
      } else {
        return new Response(ExceptionMsg.Error);
      }
    }
  }

  @RequestMapping(value = "/account/updatePassword")
  public Response updatePassword(HttpServletRequest request, @RequestBody Map<String, String> info)
      throws GlobalException {
    if (accountService.setPassword(info) == 0) {
      return new Response(ExceptionMsg.Error);
    } else
      return new Response(ExceptionMsg.Success);
  }


}
