package com.ourteam.dzpt.controller;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.entity.User;
import com.ourteam.dzpt.service.UserService;
import com.ourteam.dzpt.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/getUserList",method = RequestMethod.GET)
    public Response selectUsers(){
        try {
            HashMap<String,List> map = new HashMap<String, List>();
            map.put("userList",userService.selectAll());
            return new Response(ExceptionMsg.Success,map);
        }catch (Exception e){
            return new Response(ExceptionMsg.getByCode(e.getMessage()));
        }
    }

    @RequestMapping(value = "/user/getUserInfo",method = RequestMethod.GET)
    public Response selectById(User user){
        try {
            return new Response(ExceptionMsg.Success,userService.getUserInfo(user.getUserName()));
        }catch (Exception e){
            return new Response(ExceptionMsg.getByCode(e.getMessage()));
        }
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public Response createUser(@RequestBody User user){
        try {
            if(userService.createUser(user)==1) return new Response(ExceptionMsg.Success);
            else return new Response(ExceptionMsg.Error);
        }catch (Exception e){
            return new Response(ExceptionMsg.getByCode(e.getMessage()));
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(HttpServletRequest request,@RequestBody User user){
        User targetUser;
        //判断用户名是否为空
        if(user.getUserName() == null){
            return new Response(ExceptionMsg.ParameterError);
        } else{
            targetUser = userService.selectByName(user.getUserName());
            //判断用户是否存在
            if(targetUser == null) return new Response(ExceptionMsg.UserNotExist);
            else{
                String Md5Password = MD5Util.stringToMD5(user.getPassword());
                //判断密码是否正确
                if(targetUser.getPassword().equals(Md5Password)){
                    request.getSession().setAttribute("uid",targetUser.getId());
                    return new Response(ExceptionMsg.Success);
                }else{
                    return new Response(ExceptionMsg.Error);
                }
            }
        }
    }

    @RequestMapping(value = "logout")
    public Response logout(HttpServletRequest request){
        HttpSession session= request.getSession();
        if(session!=null) session.setAttribute("uid",null);
        return new Response(ExceptionMsg.Success);
    }

    @RequestMapping(value = "/user/updateUserInfo")
    public Response updateUserInfo(HttpServletRequest request,@RequestBody User user){
        int id = (int)request.getSession().getAttribute("uid");
        if(!user.getUserName().equals(userService.selectById(id).getUserName()))
            return new Response(ExceptionMsg.NotAllow);
        user.setId(id);

        try {
            if(userService.updateInfo(user)==0) return new Response(ExceptionMsg.Error);
            else return new Response(ExceptionMsg.Success);
        }catch (Exception e){
            return new Response(ExceptionMsg.getByCode(e.getMessage()));
        }
    }

    @RequestMapping(value = "/user/updatePassword")
    public Response updatePassword(HttpServletRequest request,@RequestBody Map<String, String> info){
//        int id = (int)request.getSession().getAttribute("uid");
        info.put("id",4+"");
        try {
            if(userService.updatePassword(info)==0) return new Response(ExceptionMsg.Error);
            else return new Response(ExceptionMsg.Success);
        }catch (Exception e){
            return new Response(ExceptionMsg.getByCode(e.getMessage()));
        }
    }

    @RequestMapping(value = "/user/deleteUser")
    public Response deleteUser(HttpServletRequest request,@RequestBody User user){
        if(userService.deleteUser(user.getUserName())==1) return new Response(ExceptionMsg.Success);
        else return new Response(ExceptionMsg.Error);
    }

    @RequestMapping(value ="/user/banUser")
    public Response banUser(@RequestBody User user){
        try {
            if(userService.banUser(user)==0) return new Response(ExceptionMsg.Error);
            else return new Response(ExceptionMsg.Success);
        }catch (Exception e){
            return new Response(ExceptionMsg.getByCode(e.getMessage()));
        }
    }
    @RequestMapping(value = "/user/getBanList")
    public Response getBanList(){
        try {
            HashMap<String,List> map = new HashMap<String, List>();
            map.put("banList",userService.getBanList());
            return new Response(ExceptionMsg.Success,map);
        }catch (Exception e){
            return new Response(ExceptionMsg.getByCode(e.getMessage()));
        }
    }
}
