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
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Response selectUser(User user){
        List<User> users = userService.selectAll();
        return new Response(ExceptionMsg.Success,users);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Response selectUserById(@PathVariable int id){
        User user = userService.selectById(id);
        if(user == null) return new Response(ExceptionMsg.Error);
        else return new Response(ExceptionMsg.Success,user);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Response createUser(@RequestBody User user){
        user.setCreateDate(LocalDateTime.now().toString());

        //判断密码是否为空并加密
        if(user.getPassword() != null){user.setPassword(MD5Util.stringToMD5(user.getPassword()));}
        else return new Response(ExceptionMsg.ParameterError);

        //判断用户名是否存在
        if(user.getUserName() != null){
            if(userService.selectByName(user.getUserName()) != null)return new Response(ExceptionMsg.UserNameOccupied);
        }else{
            return new Response(ExceptionMsg.ParameterError);
        }

        //添加用户
        if(userService.createUser(user) == 1) return new Response(ExceptionMsg.Success);
        else return new Response(ExceptionMsg.Error);
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
}
