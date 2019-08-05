package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.MyCar;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.entity.User;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired

  @RequestMapping(value = "/user/getUserList", method = RequestMethod.GET)
  public Response selectUsers() throws Exception {
    HashMap<String, List> map = new HashMap<>();
    map.put("userList", userService.selectAll());
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.GET)
  public Response selectById(HttpServletRequest request, Integer userId) throws Exception {
    if (userId == null) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    int id = (Integer) request.getSession().getAttribute("uid");
    if (id != userId) {
      throw new GlobalException(ExceptionMsg.NotAllow);
    }
    return new Response(ExceptionMsg.Success, userService.getUserInfo(userId));
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public Response createUser(
      @Validated(value = {User.Default.class, User.Info.class}) @RequestBody User user,
      BindingResult br) throws Exception {
    if (br.hasErrors()) {
      return new Response("E0001", br.getFieldError().getDefaultMessage());
    }
    userService.createUser(user);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Response login(HttpServletRequest request,
      @Validated(value = User.Default.class) @RequestBody User user,
      BindingResult br) throws GlobalException {
    if (br.hasErrors()) {
      throw new GlobalException("E0001", br.getFieldError().getDefaultMessage());
    }
    int uid = userService.login(user);
    request.getSession().setAttribute("uid", uid);
    Map<String, Integer> map = new HashMap<>();
    map.put("userId", uid);
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "logout")
  public Response logout(HttpServletRequest request) throws GlobalException {
    HttpSession session = request.getSession();
    if (session != null) {
      session.setAttribute("uid", null);
    }
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/user/updateUserInfo")
  public Response updateUserInfo(HttpServletRequest request, @RequestBody User user) {
    int id = (int) request.getSession().getAttribute("uid");
    if (!user.getUserName().equals(userService.selectById(id).getUserName())) {
      return new Response(ExceptionMsg.NotAllow);
    }
    user.setId(id);
    userService.updateInfo(user);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/user/updatePassword")
  public Response updatePassword(HttpServletRequest request, @RequestBody Map<String, String> info)
      throws GlobalException {
    int id = (int) request.getSession().getAttribute("uid");
    info.put("id", id + "");
    userService.updatePassword(info);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/user/banUser")
  public Response banUser(@RequestBody User user) throws GlobalException {
    if (user.getId() <= 0 || user.getIfBan() < 0 || user.getIfBan() > 1) {
      throw new GlobalException(ExceptionMsg.ParameterError);
    }
    userService.banUser(user);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/user/getBanList")
  public Response getBanList() throws GlobalException {
    HashMap<String, List> map = new HashMap<>();
    map.put("banList", userService.getBanList());
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "/mine/getMyCar", method = RequestMethod.GET)
  public Response getMyCar(HttpServletRequest request) throws GlobalException {
    int id = (int) request.getSession().getAttribute("uid");
    HashMap<String, List> map = new HashMap<>();
    map.put("goodsList", userService.getMyCar(id));
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "/mine/deleteMyCar", method = RequestMethod.POST)
  public Response deleteMyCar(HttpServletRequest request, @RequestBody @Validated MyCar myCar,
      BindingResult br) throws Exception {
    if (br.hasErrors()) {
      return new Response("E0001", br.getFieldError().getDefaultMessage());
    }
    int id = (int) request.getSession().getAttribute("uid");
    userService.deleteByGoodsId(myCar, id);
    return new Response(ExceptionMsg.Success);
  }

  @RequestMapping(value = "/mine/addMyCar", method = RequestMethod.POST)
  public Response addMyCar(HttpServletRequest request, @RequestBody @Validated MyCar myCar,
      BindingResult br) throws Exception {
    if (br.hasErrors()) {
      return new Response("E0001", br.getFieldError().getDefaultMessage());
    }
    int id = (int) request.getSession().getAttribute("uid");
    userService.insertIntoMyCar(myCar, id);
    return new Response(ExceptionMsg.Success);
  }
}
