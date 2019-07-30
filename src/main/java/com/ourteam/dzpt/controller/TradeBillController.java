package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.entity.TradeBill;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.TradeBillService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeBillController {

  @Autowired
  TradeBillService tradeBillService;

  @RequestMapping("/order/createTrade")
  public Response createTradeBill(HttpServletRequest request,
      @Validated @RequestBody TradeBill tradeBill,
      BindingResult br) throws Exception {
    if (br.hasErrors()) {
      throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
    }
    int id = (int) request.getSession().getAttribute("uid");
    if (tradeBillService.createTradeBill(tradeBill) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      throw new GlobalException(ExceptionMsg.Error);
    }
  }

  @RequestMapping("/order/getTradeInfo")
  public Response getTradeInfo(HttpServletRequest request, Integer TradeId) throws Exception {
    return new Response(ExceptionMsg.Success, tradeBillService.getTradeBillInfo(TradeId));
  }

  @RequestMapping("/tradeBill/getTradeBill")
  public Response getTradeBills() throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    map.put("tradeBillList", tradeBillService.getTradeBills());
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping("/mine/getMyTrading")
  public Response getMyTrading(HttpServletRequest request, Integer userId) throws Exception {
    int id = (Integer) request.getSession().getAttribute("uid");
    if (id != userId) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    HashMap<String, Object> map = new HashMap<>();
    map.put("tradingList", tradeBillService.getMyTrading(id));
    return new Response(ExceptionMsg.Success, map);
  }
}
