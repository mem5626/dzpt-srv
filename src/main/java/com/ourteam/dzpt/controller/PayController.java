package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.*;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.AccountService;
import com.ourteam.dzpt.service.BillService;
import com.ourteam.dzpt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PayController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BillService billService;

    @RequestMapping(value = "/pay/verify", method = RequestMethod.POST)
    public Response verifyPassword(HttpServletRequest request, @Validated(value = Account.Default.class) @RequestBody Account account,
                                   BindingResult br) throws GlobalException{
        if (br.hasErrors()) return new Response("E0001", br.getFieldError().getDefaultMessage());
        Account targetAccount= accountService.getAccountByUid(account.getUserId());
        if (targetAccount == null) return new Response(ExceptionMsg.UserNotExist);
        else {
            String Md5Password = MD5Util.stringToMD5(account.getPayPassword());
            //判断密码是否正确
            if (targetAccount.getPayPassword().equals(Md5Password)) {
                return new Response(ExceptionMsg.Success);
            } else {
                return new Response(ExceptionMsg.Error);
            }
        }
    }

    @RequestMapping(value = "/pay/commit", method = RequestMethod.POST)
    public Response AddBill(HttpServletRequest request, @RequestBody Bill payerBill,@RequestBody Bill receiverBill) {
        if (payerBill != null && receiverBill != null) {
            if ((billService.addBill(payerBill) == 1) && (billService.addBill(receiverBill) == 1)) {
                return new Response(ExceptionMsg.Success);
            } else {
                return new Response(ExceptionMsg.Error);
            }
        } else if (payerBill != null) {
            if (billService.addBill(payerBill) == 1)
                return new Response(ExceptionMsg.Success);
            else {
                return new Response(ExceptionMsg.Error);
            }
        } else if (receiverBill !=null){
            if (billService.addBill(receiverBill) == 1)
                return new Response(ExceptionMsg.Success);
            else {
                return new Response(ExceptionMsg.Error);
            }
        } else return new Response(ExceptionMsg.Error);

    }
}
