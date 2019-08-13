package com.ourteam.dzpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ServletComponentScan
@EnableRedisHttpSession
@SpringBootApplication
@RestController
public class DzptApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DzptApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(this.getClass());
    }
}
