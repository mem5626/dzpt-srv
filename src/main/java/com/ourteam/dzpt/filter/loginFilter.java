package com.ourteam.dzpt.filter;

import com.ourteam.dzpt.entity.ExceptionMsg;
import com.ourteam.dzpt.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Order(1)
@WebFilter(urlPatterns = {"/user","/user/*"},filterName = "loginFilter")
public class loginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession(false);
        System.out.print(((HttpServletRequest) servletRequest).getRequestURI());
        System.out.print(((HttpServletRequest) servletRequest).getMethod());
        if(session !=null && session.getAttribute("uid") != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            try {
                returnJson((HttpServletResponse)servletResponse,new Response(ExceptionMsg.NotLogin).toString());
            }catch (Exception e){
                logger.error("response error",e);
            }
        }
    }

    @Override
    public void destroy() {

    }


    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
