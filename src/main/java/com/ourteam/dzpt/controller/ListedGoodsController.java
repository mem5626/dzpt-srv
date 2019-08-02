package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.ListedGoods;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.ListedGoodsService;
import com.ourteam.dzpt.util.UploadUtil;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ListedGoodsController {

  @Autowired
  ListedGoodsService listedGoodsService;
  @Autowired
  UploadUtil uploadUtil;

  @RequestMapping(value = "/hang/changeHangInfo", method = RequestMethod.POST)
  public Response changeHangInfo(HttpServletRequest request,
      @Validated(value = ListedGoods.ListedGoodsInfo.class)
      @RequestBody ListedGoods listedGoods, BindingResult br) throws Exception {
    if (br.hasErrors()) {
      throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
    }
    int uid = (int) request.getSession().getAttribute("uid");
    listedGoodsService.changeHangInfo(listedGoods, uid);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/hang/hangNow", method = RequestMethod.POST)
  public Response hangNow(HttpServletRequest request, MultipartFile imageFile,
      @Validated(value = {ListedGoods.ListedGoodsInfo.class,
          ListedGoods.ListedGoodsCreate.class}) ListedGoods listedGoods, BindingResult br)
      throws Exception {
    if (br.hasErrors()) {
      throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
    }
    int uid = (int) request.getSession().getAttribute("uid");
    if (listedGoods.getHangType().equals("售出")) {
      listedGoods.setImage(uploadUtil.uploadImage(imageFile, "listedGoods"));
    }
    listedGoodsService.hangNow(listedGoods, uid);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/hang/deleteHangGood", method = RequestMethod.POST)
  public Response deleteHangGood(HttpServletRequest request, @RequestBody Map<String, Integer> info)
      throws Exception {
    Integer listedGoodsId = info.get("listedGoodsId");
    if (listedGoodsId == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    int uid = (int) request.getSession().getAttribute("uid");
    listedGoodsService.deleteHangGood(listedGoodsId, uid);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/hang/getSellerHangList", method = RequestMethod.GET)
  public Response getSellerHangList() throws Exception {
    HashMap<String, List> map = new HashMap<>();
    map.put("hangList", listedGoodsService.getHangListByType("售出"));
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "/hang/getBuyerHangList", method = RequestMethod.GET)
  public Response getBuyerHangList() throws Exception {
    HashMap<String, List> map = new HashMap<>();
    map.put("hangList", listedGoodsService.getHangListByType("需求"));
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "/hang/getMyHangList", method = RequestMethod.GET)
  public Response getMyHangList(HttpServletRequest request, int userId) throws Exception {
    if (userId <= 0) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    int uid = (int) request.getSession().getAttribute("uid");
    if (uid != userId) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    HashMap<String, List> map = new HashMap<>();
    map.put("hangList", listedGoodsService.getMyHangList(uid));
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "/search/searchHangGood")
  public Response searchHangGood(Integer listedGoodsId) throws Exception {
    if (listedGoodsId == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    return new Response(ExceptionMsg.Success, listedGoodsService.searchHangGood(listedGoodsId));
  }
}
