package com.ourteam.dzpt.exception;

import com.ourteam.dzpt.entity.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(GlobalException.class)
  public Response globalExceptionHandler(GlobalException e) {
    System.out.print(e.getMessage());
    return new Response(e.getCode(), e.getMessage());
  }
}
