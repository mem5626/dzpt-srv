package com.ourteam.dzpt.controller;

import cn.hutool.core.collection.CollUtil.Hash;
import com.icbc.api.IcbcApiException;
import com.icbc.api.response.MybankPayCpayCppayapplyResponseV1;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.exception.GlobalExceptionHandler;
import com.ourteam.dzpt.service.BankService;
import com.ourteam.dzpt.service.OrderService;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.internal.util.internal.util.fastjson.JSONObject;
import com.icbc.api.request.MybankPayCpayCpordercloseRequestV1.MybankPayCpayCpordercloseV1RequestV1Biz;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.BeanGoodsInfo;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.BeanRecvMallInfo;
import com.icbc.api.request.MybankPayCpayCppayapplyRequestV1.MybankPayCpayCppayapplyRequestV1Biz;
import com.icbc.api.response.MybankCreditcardOnlinecardCardapplylistReponseV1;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BankController {
  @Autowired
  OrderService orderService;
  @Autowired
  BankService bankService;

  private static Logger logger = LoggerFactory.getLogger(BankController.class);

  @RequestMapping(value = "/bank/pay", method = RequestMethod.POST)
  public Response pay(HttpServletRequest request, @RequestBody HashMap<String, Object> info)
      throws GlobalException {
    Map map = orderService.getOrderInfo((Integer) info.get("listedGoodsId"));
    map.put("payChannel",info.get("payChannel"));
    MybankPayCpayCppayapplyResponseV1 result = bankService.payByBank(map);

    if (result!=null && result.isSuccess()){
      HashMap map1 = new HashMap();
      map1.put("url",result.getRedirectParam().replaceAll("http://122.64.141.6:80","http://114.255.225.35:83"));
      return new Response(ExceptionMsg.Success, map1);
    }
    return new Response(ExceptionMsg.Error);
  }

  @RequestMapping("/bank/test2")
  public Response test2(HttpServletRequest request, @RequestBody HashMap map){
    logger.info("支付成功回调内容" + JSONObject.toJSONString(map));
    return new Response(ExceptionMsg.Success);
  }







}
