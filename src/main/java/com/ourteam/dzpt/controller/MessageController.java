package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Message;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping("/sendMessage")
    public Response sendMessage(HttpServletRequest request, @Validated @RequestBody Message message,
                                BindingResult br) throws Exception {
        if (br.hasErrors()) throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
        int id = (int) request.getSession().getAttribute("uid");
        if (messageService.sendMessage(message, id) == 1) return new Response(ExceptionMsg.Success);
        else throw new GlobalException(ExceptionMsg.Error);
    }

    @RequestMapping("/getMessageList")
    public Response getMessageList(HttpServletRequest request, @RequestBody Map<String, Object> info) throws Exception {
        if (info.get("userName") == null || info.get("type") == null)
            throw new GlobalException(ExceptionMsg.ParameterError);
        int id = (int) request.getSession().getAttribute("uid");
        HashMap<String, List> map = new HashMap<>();
        map.put("messageList", messageService.getMessageList((String) info.get("userName"), id, (Integer) info.get("type")));
        return new Response(ExceptionMsg.Success, map);
    }

    @RequestMapping("/deleteMessage")
    public Response deleteMessage(HttpServletRequest request, @RequestBody Map<String, Object> info) throws Exception {
        if (info.get("userName") == null || info.get("messageId") == null)
            throw new GlobalException(ExceptionMsg.ParameterError);
        int id = (int) request.getSession().getAttribute("uid");
        if (messageService.deleteMessage((Integer) info.get("messageId"), (String) info.get("userName"), id) == 1)
            return new Response(ExceptionMsg.Success);
        else throw new GlobalException(ExceptionMsg.Error);
    }
}
