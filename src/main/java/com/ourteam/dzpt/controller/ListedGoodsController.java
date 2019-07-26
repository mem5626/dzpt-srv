package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.ListedGoods;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.ListedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/hang")
public class ListedGoodsController {

    @Autowired
    ListedGoodsService listedGoodsService;

    @RequestMapping(value = "/changeHangInfo", method = RequestMethod.POST)
    public Response changeHangInfo(HttpServletRequest request, @Validated(value = ListedGoods.ListedGoodsInfo.class)
    @RequestBody ListedGoods listedGoods, BindingResult br) throws Exception {
        if (br.hasErrors()) throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
        int uid = (int) request.getSession().getAttribute("uid");
        if (listedGoodsService.changeHangInfo(listedGoods, uid) == 1) return new Response(ExceptionMsg.Success);
        else throw new GlobalException(ExceptionMsg.Error);
    }

    @RequestMapping(value = "/hangNow", method = RequestMethod.POST)
    public Response hangNow(HttpServletRequest request,
                            @Validated(value = {ListedGoods.ListedGoodsInfo.class, ListedGoods.ListedGoodsCreate.class})
                            @RequestBody ListedGoods listedGoods, BindingResult br) throws Exception {
        if (br.hasErrors()) throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
        int uid = (int) request.getSession().getAttribute("uid");
        if (listedGoodsService.hangNow(listedGoods, uid) == 1) return new Response(ExceptionMsg.Success);
        else throw new GlobalException(ExceptionMsg.Error);
    }

    @RequestMapping(value = "/deleteHangGood", method = RequestMethod.POST)
    public Response deleteHangGood(HttpServletRequest request, Integer listedGoodsId) throws Exception {
        if (listedGoodsId == null) throw new GlobalException(ExceptionMsg.ParameterError);
        int uid = (int) request.getSession().getAttribute("uid");
        if (listedGoodsService.deleteHangGood(listedGoodsId, uid) == 1) return new Response(ExceptionMsg.Success);
        else throw new GlobalException(ExceptionMsg.Error);
    }

    @RequestMapping(value = "/getSellerHangList", method = RequestMethod.GET)
    public Response getSellerHangList() throws Exception {
        HashMap<String, List> map = new HashMap<>();
        map.put("hangList", listedGoodsService.getHangListByType(0));
        return new Response(ExceptionMsg.Success, map);
    }

    @RequestMapping(value = "/getBuyerHangList", method = RequestMethod.GET)
    public Response getBuyerHangList() throws Exception {
        HashMap<String, List> map = new HashMap<>();
        map.put("hangList", listedGoodsService.getHangListByType(1));
        return new Response(ExceptionMsg.Success, map);
    }

    @RequestMapping(value = "/getMyHangList", method = RequestMethod.GET)
    public Response getMyHangList(HttpServletRequest request, String userName) throws Exception {
        if (userName == null || userName.equals("")) throw new GlobalException(ExceptionMsg.ParameterError);
        int uid = (int) request.getSession().getAttribute("uid");
        HashMap<String, List> map = new HashMap<>();
        map.put("hangList", listedGoodsService.getMyHangList(userName, uid));
        return new Response(ExceptionMsg.Success, map);
    }
}
