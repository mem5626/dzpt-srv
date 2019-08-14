package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.entity.Bill;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.AccountService;
import com.ourteam.dzpt.service.BillService;
import com.ourteam.dzpt.util.MD5Util;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1;
import com.icbc.api.response.MybankCreditcardOnlinecardCardapplylistReponseV1;

@RestController
public class PayController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private BillService billService;

  private static Logger logger = LoggerFactory.getLogger(PayController.class);

  //充值、提现、零钱支付
  @RequestMapping(value = "/pay/commit", method = RequestMethod.POST)
  public Response pay(HttpServletRequest request, @RequestBody HashMap<String, String> info)
      throws GlobalException {
    int id = (int) request.getSession().getAttribute("uid");
    if (Integer.parseInt(info.get("userId")) != id) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    //先验证密码，(判断是否需要银行转账，转账)成功则更新账单，检测用户余额与当前余额是否一致，不一致则更新余额。
      Account targetAccount = accountService.getAccountByUid(Integer.parseInt(info.get("userId")));
      //判断密码是否正确
      if (targetAccount.getPayPassword().equals(MD5Util.stringToMD5(info.get("payPassword")))) {
        billService.addBill(info);
        if (!info.get("balance").equals(targetAccount.getBalance())) {
          targetAccount.setBalance(Integer.parseInt(info.get("balance")));
          accountService.updateBalance(targetAccount);
        }
        //写卖方增加账单、余额变更逻辑
        if(info.get("tradeType").equals("3")){
          Account sellerAccount = accountService.getAccountByUid(Integer.parseInt(info.get("sellerId")));
          long beforeBalance = sellerAccount.getBalance();
          long afterBalance = beforeBalance + Integer.parseInt(info.get("money"));
          sellerAccount.setBalance(afterBalance);
          Map <String,String> sellerInfo=info;
          sellerInfo.replace("banlance",String.valueOf(afterBalance));
          sellerInfo.replace("drcrflg","2");
          sellerInfo.replace("tradeWay","");
          sellerInfo.replace("tradeWayName","");
          if (billService.addBill(sellerInfo) == 1 && accountService.updateBalance(sellerAccount) == 1) {
            return new Response(ExceptionMsg.Success);
          } else {
            throw new GlobalException(ExceptionMsg.Error);
          }
        }
        return new Response(ExceptionMsg.Success);
      } else {
        throw new GlobalException(ExceptionMsg.PasswordError);
      }
  }

//  @RequestMapping(value = "/pay/refund", method = RequestMethod.POST)
//  public Response refund(HttpServletRequest request, @RequestBody HashMap<String, String> info) {
//    Bill bill = new Bill();
//    Account targetAccount = accountService.getAccountByUid(Integer.parseInt(info.get("userId")));
//    long beforeBalance = targetAccount.getBalance();
//    long afterBalance = beforeBalance + Integer.parseInt(info.get("money"));
//    targetAccount.setBalance(afterBalance);
//    info.replace("banlance",String.valueOf(afterBalance));
//    if (billService.addBill(info) == 1 && accountService.updateBalance(targetAccount) == 1) {
//      return new Response(ExceptionMsg.Success);
//    } else {
//      throw new GlobalException(ExceptionMsg.Error);
//    }
//  }

  @RequestMapping(value = "/pay/test", method = RequestMethod.GET)
  public Response test(HttpServletRequest request) {
    logger.info(accountService.getBillByUserId("1"));
    logger.info(accountService.testPostAddCard("1"));
    return new Response(ExceptionMsg.Success);
  }


  @RequestMapping(value = "/pay/test1", method = RequestMethod.GET)
  public Response test1(HttpServletRequest request) {

    return new Response(ExceptionMsg.Success);
  }
}