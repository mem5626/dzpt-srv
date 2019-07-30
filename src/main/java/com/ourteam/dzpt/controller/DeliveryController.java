package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.DeliveryBill;
import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.DeliveryService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {

  @Autowired
  private DeliveryService deliveryService;

  @RequestMapping(value = "/order/createDelivery", method = RequestMethod.POST)
  public Response createDelivery(HttpServletRequest request,DeliveryBill deliveryBill) throws GlobalException {
    if (deliveryService.createDelivery(deliveryBill) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/order/getDeliveryInfo", method = RequestMethod.GET)
  public Response selectDeliveryByTid(DeliveryBill deliveryBill) {
    return new Response(ExceptionMsg.Success,
        deliveryService.selectDeliveryByTid(deliveryBill.getTradeBillId()));
  }

  @RequestMapping(value = "/order/deliverGoods", method = RequestMethod.POST)
  public Response ifDeliver(HttpServletRequest request, @RequestBody DeliveryBill deliveryBill)
      throws GlobalException {
    if (deliveryService.ifDeliver(deliveryBill) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/order/receiveGoods", method = RequestMethod.POST)
  public Response ifReceive(HttpServletRequest request, @RequestBody DeliveryBill deliveryBill)
      throws GlobalException {
    if (deliveryService.ifReceive(deliveryBill) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/order/requireReturn", method = RequestMethod.POST)
  public Response requireReturn(HttpServletRequest request, @RequestBody DeliveryBill deliveryBill)
      throws GlobalException {
    if (deliveryService.requireReturn(deliveryBill) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/order/agreeReturn", method = RequestMethod.POST)
  public Response agreeReturn(HttpServletRequest request, @RequestBody DeliveryBill deliveryBill)
      throws GlobalException {
    if (deliveryService.agreeReturn(deliveryBill) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/order/confirmReturn", method = RequestMethod.POST)
  public Response confirmReturn(HttpServletRequest request, @RequestBody DeliveryBill deliveryBill)
      throws GlobalException {
    if (deliveryService.confirmReturn(deliveryBill) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }
}
