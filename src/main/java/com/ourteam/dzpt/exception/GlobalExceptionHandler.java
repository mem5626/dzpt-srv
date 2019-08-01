package com.ourteam.dzpt.exception;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.util.IpUtil;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//  @ResponseBody
//  @ExceptionHandler(value = Exception.class)
//  public Response errorHandler(HttpServletRequest req,Exception ex) {
//    logger.error("IP:" + IpUtil.getIpAddr(req) + ", URI:"+ req.getRequestURI() + ", ERROR:" + ex);
//    return new Response(ExceptionMsg.Error);
//  }

  @ResponseBody
  @ExceptionHandler(value = SQLException.class)
  public Response sqlErrorHandler(HttpServletRequest req,Exception ex) {
    logger.error("IP:" + IpUtil.getIpAddr(req) + ", URI:"+ req.getRequestURI() + ", ERROR:" + ex);
    return new Response(ExceptionMsg.Error);
  }

  @ResponseBody
  @ExceptionHandler(GlobalException.class)
  public Response globalExceptionHandler(HttpServletRequest req,GlobalException e) {
    logger.warn("IP:"+ IpUtil.getIpAddr(req) + ", URI:"+req.getRequestURI() + ", WARN:" + e.getMessage());
    return new Response(e.getCode(), e.getMessage());
  }

}
