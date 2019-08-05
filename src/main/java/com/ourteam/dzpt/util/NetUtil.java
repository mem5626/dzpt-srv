package com.ourteam.dzpt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ourteam.dzpt.entity.Response;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class NetUtil {

  static String baseUrl="http://localhost:8080";

  public static Response getRequest(String url) {
    StringBuilder sb=new StringBuilder();
    sb.append(baseUrl);
    sb.append(url);
    RestTemplate restTemplate=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    String strbody=restTemplate.exchange(sb.toString(), HttpMethod.GET, entity,String.class).getBody();
    Response res = JSONArray.parseObject(strbody, Response.class);
    return res;
  }

  public static Response getRequest(String url, HashMap<String,String> map) {
    StringBuilder sb=new StringBuilder();
    sb.append(baseUrl);
    sb.append(url);
    sb.append("?");

    for(String key : map.keySet()){
      sb.append(key);
      sb.append("=");
      sb.append(map.get(key));
      sb.append("&");
    }
    if(sb.charAt(sb.length()-1)=='&'){
      sb.deleteCharAt(sb.length()-1);
    }
    System.out.println(sb);
    RestTemplate restTemplate=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    HttpEntity<String> entity = new HttpEntity<String>(headers);
    String strbody=restTemplate.exchange(sb.toString(), HttpMethod.GET, entity,String.class).getBody();
    Response res = JSONArray.parseObject(strbody, Response.class);
    return res;
  }

  public static Response postRequest(String url, HashMap<String,String> map) {
    JSONObject jo = JSONObject.parseObject(JSON.toJSONString(map));
    RestTemplate client=new RestTemplate();
    StringBuilder sb=new StringBuilder();
    sb.append(baseUrl);
    sb.append(url);
    JSONObject json = client.postForEntity(sb.toString(), jo, JSONObject.class).getBody();
    Response res = JSONArray.parseObject(json.toJSONString(), Response.class);
    return res;

  }


}
