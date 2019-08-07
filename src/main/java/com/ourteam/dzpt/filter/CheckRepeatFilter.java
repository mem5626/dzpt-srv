package com.ourteam.dzpt.filter;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import com.ourteam.dzpt.exception.GlobalException;
import com.ourteam.dzpt.util.IpUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

@Order(1)
//@WebFilter(urlPatterns = "/*")
public class CheckRepeatFilter implements Filter {

  @Autowired
  RedisTemplate redisTemplate;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;

    String key = request.getSession().getId() + "_" + request.getRequestURI().replace("/", "_");
    boolean checkRepeat = redisTemplate.opsForValue().setIfAbsent(key, "1", 2, TimeUnit.SECONDS);

    if (checkRepeat){
      filterChain.doFilter(servletRequest, servletResponse);
    }else {
      try {
        logger.info(
            "IP:" + IpUtil.getIpAddr(request) + ", URI:" + request.getRequestURI() + ", INFO:"
                + "重复请求");
        returnJson((HttpServletResponse) servletResponse,
            new Response(ExceptionMsg.RepeatRequest).toString());
      } catch (Exception e) {
        logger.error("response error", e);
      }
    }
  }


  @Override
  public void destroy() {

  }

  private void returnJson(HttpServletResponse response, String json) throws Exception {
    PrintWriter writer = null;
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    try {
      writer = response.getWriter();
      writer.print(json);

    } catch (IOException e) {
      logger.error("重复请求相应错误", e);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }
}
