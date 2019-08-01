package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Message;
import com.ourteam.dzpt.entity.Negotiate;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.entity.SystemMsg;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.MessageService;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/message")
public class MessageController {

  @Autowired
  MessageService messageService;

  @RequestMapping("/sendMessage")
  public Response sendMessage(HttpServletRequest request, @Validated @RequestBody Message message,
      BindingResult br) throws Exception {
    if (br.hasErrors()) {
      throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
    }
    int id = (int) request.getSession().getAttribute("uid");

    if (messageService.sendMessage(message, id) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      throw new GlobalException(ExceptionMsg.Error);
    }
  }

  @RequestMapping("/getMessageList")
  public Response getMessageList(HttpServletRequest request, Integer userId) throws Exception {
    if (userId == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    int id = (int) request.getSession().getAttribute("uid");
    HashMap<String, List> map = new HashMap<>();
    map.put("messageList", messageService.getMessageList(userId, id));
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping("/deleteMessage")
  public Response deleteMessage(HttpServletRequest request, @RequestBody Map<String, Object> info)
      throws Exception {
    if (info.get("userId") == null || info.get("messageId") == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    int id = (int) request.getSession().getAttribute("uid");
    if ((Integer) info.get("userId") != id) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    if (messageService
        .deleteMessage((Integer) info.get("messageId"), id) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      throw new GlobalException(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/setReadMessage")
  public Response setReadMessage(int messageId) throws Exception {
    if(messageService.setReadMessage(messageId) == 1) return new Response(ExceptionMsg.Success);
    else throw new GlobalException(ExceptionMsg.Error);
  }

  @RequestMapping("/sendSystemMsg")
  public Response sendSystemMsg(@RequestBody SystemMsg systemMsg) throws Exception {
    if (messageService.createSystemMsg(systemMsg) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping("/getSystemMessage")
  public Response getSystemMessage() throws Exception {
    HashMap<String, List> map = new HashMap<>();
    map.put("messageList", messageService.getSystemMsgList());
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping("/negotiate")
  public Response negotiate(@RequestBody Map<String, Object> info) throws Exception {
    Negotiate negotiate = new Negotiate();
    negotiate.setCurrentPrice((Integer) info.get("price"));

    Message message = new Message();
    message.setSender((Integer) info.get("sender"));
    message.setReceiver((Integer) info.get("receiver"));
    message.setType((String) info.get("type"));
    message.setTitle((String) info.get("title"));
    message.setContent((String) info.get("content"));

    if (messageService.negotiate(negotiate, message, (Integer) info.get("listedGoodsId")) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/getNegotiate", method = RequestMethod.GET)
  public Response getNegotiate(Integer id) throws Exception {
    if (id == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    return new Response(ExceptionMsg.Success,
        messageService.getNegotiate(id));
  }

  @RequestMapping(value = "/affirm", method = RequestMethod.POST)
  public Response affirmNegotiate(@RequestBody Map<String, Integer> info) throws Exception {
    if (info.get("tradingId") == null || info.get("negotiateId") == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    if (messageService.affirmNegotiatedId(info.get("tradingId"), info.get("negotiateId")) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      throw new GlobalException(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/refuse", method = RequestMethod.POST)
  public Response refuseNegotiate(@RequestBody Map<String, Integer> info) throws Exception {
    if (info.get("tradingId") == null || info.get("negotiateId") == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    if (messageService.refuseNegotiatedId(info.get("tradingId"), info.get("negotiateId")) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      throw new GlobalException(ExceptionMsg.Error);
    }
  }
}
