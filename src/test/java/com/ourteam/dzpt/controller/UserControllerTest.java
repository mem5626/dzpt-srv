package com.ourteam.dzpt.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.print.attribute.standard.Media;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTest {
    @Autowired
    private UserController userController;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception{
        System.out.print("执行初始化");
        session = new MockHttpSession();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void loginTest() throws Exception {

        //参数为空情况
        RequestBuilder parameterError = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON);
        ResultActions parameterErrorResult = mockMvc.perform(parameterError);
        parameterErrorResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("E0001"))
                .andReturn();

        //用户不存在情况
        RequestBuilder userNotExist = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"testNotExists\",\"password\":\"root\"}")
                .accept(MediaType.APPLICATION_JSON);
        ResultActions userNotExistResult = mockMvc.perform(userNotExist);
        userNotExistResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("E0003"))
                .andReturn();

        //密码错误情况
        RequestBuilder passwordError = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"root\",\"password\":\"root123\"}")
                .accept(MediaType.APPLICATION_JSON);
        ResultActions passwordErrorResult = mockMvc.perform(passwordError);
        passwordErrorResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("-1"))
                .andReturn();

        //登录成功测试
        RequestBuilder loginSuccess = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"root\",\"password\":\"root\"}")
                .accept(MediaType.APPLICATION_JSON);
        ResultActions loginSuccessResult = mockMvc.perform(loginSuccess);
        loginSuccessResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"))
                .andReturn();
    }

    @Test
    public void selectUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserInfo").param("userName","root"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void selectUsers() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"))
                .andReturn();
    }

    @Test
    public void signUp() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"unitTest\",\"password\":\"unitTest\",\"email\":\"test\",\"phone\":\"test\",\"companyName\":\"test\",\"address\":\"test\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"))
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"root\",\"password\":\"root\",\"email\":\"test\",\"phone\":\"test\",\"companyName\":\"test\",\"address\":\"test\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("E0002"))
                .andReturn();
    }

}