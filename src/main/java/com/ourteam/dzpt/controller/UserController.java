package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.MyCar;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.entity.User;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.service.UserService;
import com.ourteam.dzpt.util.MD5Util;
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
    HashMap<String, List> map = new HashMap();
    map.put("userList", userService.selectAll());
    return new Response(ExceptionMsg.Success, map);
  }

  @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.GET)
  public Response selectById(User user) throws GlobalException {
    return new Response(ExceptionMsg.Success, userService.getUserInfo(user.getUserName()));
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public Response createUser(
      @Validated(value = {User.Default.class, User.Info.class}) @RequestBody User user,
      BindingResult br) throws GlobalException {
    if (br.hasErrors()) {
      return new Response("E0001", br.getFieldError().getDefaultMessage());
    }
    if (userService.createUser(user) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Response login(HttpServletRequest request,
      @Validated(value = User.Default.class) @RequestBody User user,
      BindingResult br) throws GlobalException {
    if (br.hasErrors()) {
      throw  new GlobalException("E0001", br.getFieldError().getDefaultMessage());
    }
    User targetUser = userService.selectByName(user.getUserName());
    //判断用户是否存在
    if (targetUser == null) {
      throw new GlobalException(ExceptionMsg.UserNotExist);
    } else {
      String Md5Password = MD5Util.stringToMD5(user.getPassword());
      //判断密码是否正确
      if (targetUser.getPassword().equals(Md5Password)) {
        request.getSession().setAttribute("uid", targetUser.getId());
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userService.selectByName(user.getUserName()).getId());
        return new Response(ExceptionMsg.Success, map);
      } else {
        throw new GlobalException(ExceptionMsg.Error);
      }
    }
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

    if (userService.updateInfo(user) == 0) {
      return new Response(ExceptionMsg.Error);
    } else {
      return new Response(ExceptionMsg.Success);
    }
  }

  @RequestMapping(value = "/user/updatePassword")
  public Response updatePassword(HttpServletRequest request, @RequestBody Map<String, String> info)
      throws GlobalException {
    int id = (int) request.getSession().getAttribute("uid");
    info.put("id", id + "");
    if (userService.updatePassword(info) == 0) {
      return new Response(ExceptionMsg.Error);
    } else {
      return new Response(ExceptionMsg.Success);
    }
  }

  @RequestMapping(value = "/user/deleteUser")
  public Response deleteUser(HttpServletRequest request, @RequestBody User user)
      throws GlobalException {
    if (userService.deleteUser(user.getId()) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/user/banUser")
  public Response banUser(@RequestBody User user) throws GlobalException {
    if (userService.banUser(user) == 0) {
      return new Response(ExceptionMsg.Error);
    } else {
      return new Response(ExceptionMsg.Success);
    }
  }

  @RequestMapping(value = "/user/getBanList")
  public Response getBanList() throws GlobalException {
    HashMap<String, List> map = new HashMap<String, List>();
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
    if (userService.deleteByGoodsId(myCar, id) == 1) {
      return new Response(ExceptionMsg.Success);
    } else {
      return new Response(ExceptionMsg.Error);
    }
  }

  @RequestMapping(value = "/mine/addMyCar", method = RequestMethod.POST)
  public Response addMyCar(HttpServletRequest request, @RequestBody @Validated MyCar myCar,
      BindingResult br) throws Exception {
    if (br.hasErrors()) {
      return new Response("E0001", br.getFieldError().getDefaultMessage());
    }
    int id = (int) request.getSession().getAttribute("uid");
    if (userService.insertIntoMyCar(myCar, id) == 1) {
      return new Response(ExceptionMsg.Success);
    } else
      return new Response(ExceptionMsg.Error);
  }
}
