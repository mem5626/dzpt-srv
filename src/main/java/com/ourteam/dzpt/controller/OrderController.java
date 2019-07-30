package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.OrderService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @RequestMapping("/createOrder")
  public Response createOrder(@RequestBody Map<String, Object> info) throws Exception {
    if (orderService.createOrder((Integer) info.get("listedGoodsId"), (Integer) info.get("buyer"))
        == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping("/getOrderInfo")
  public Response getOrderInfo(Integer listedGoodsId) throws Exception {
    return new Response(ExceptionMsg.Success, orderService.getOrderInfo(listedGoodsId));
  }

  @RequestMapping("/cancelOrder")
  public Response cancelOrder(@RequestBody Integer orderId) throws Exception {
    if (orderId == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    if (orderService.cancelOrder(orderId) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      throw new GlobalException(ExceptionMsg.Error);
    }
  }

  @RequestMapping("/affirmOrder")
  public Response affirmOrder(@RequestBody Map<String, Integer> info) throws Exception {
    if (info.get("orderId") == null || info.get("userId") == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    if (orderService.affirmOrder(info.get("orderId"), info.get("userId")) == 1) {
      return new Response(ExceptionMsg.Success);
    } else
      throw new GlobalException(ExceptionMsg.Error);
  }

}
