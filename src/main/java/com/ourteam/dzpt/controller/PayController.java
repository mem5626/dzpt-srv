package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.Account;
import com.ourteam.dzpt.entity.Bill;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.AccountService;
import com.ourteam.dzpt.service.BillService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private BillService billService;


  @RequestMapping(value = "/pay/commit", method = RequestMethod.POST)
  public Response pay(HttpServletRequest request, @RequestBody Map<String, String> info)
      throws GlobalException {
    //先验证密码，(判断是否需要银行转账，转账)成功则更新账单，检测用户余额与当前余额是否一致，不一致则更新余额。

    Account targetAccount = accountService.getAccountByUid(Integer.parseInt(info.get("userId")));
    if (targetAccount == null) {
      return new Response(ExceptionMsg.UserNotExist);
    } else {
      //判断密码是否正确
      if (targetAccount.getPayPassword().equals(info.get("payPassword"))) {
        Bill bill = new Bill();
        bill.setUserId(Integer.parseInt(info.get("userId")));
        bill.setBalance(Integer.parseInt(info.get("balance")));
        bill.setMoney(Integer.parseInt(info.get("money")));
        bill.setDrcrflg(Integer.parseInt(info.get("drcrflg")));
        bill.setTradeType(Integer.parseInt(info.get("tradeType")));
        bill.setTradeWay(Integer.parseInt(info.get("tradeWay")));
        //后台需要处理少传属性的情况
        if (info.get("tradeWayName") != null) {
          bill.setTradeWayName(info.get("tradeWayName"));
        }
        if (info.get("tradeId") != null) {
          bill.setTradeId(Integer.parseInt(info.get("tradeId")));
        }
        billService.addBill(bill);
        if (!info.get("balance").equals(targetAccount.getBalance())) {
          targetAccount.setBalance(bill.getBalance());
          accountService.updateBalance(targetAccount);
        }
        return new Response(ExceptionMsg.Success);
      } else {
        return new Response(ExceptionMsg.Error);
      }
    }
  }
}
