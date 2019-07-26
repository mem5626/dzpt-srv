package com.ourteam.dzpt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getNowDataTimeString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date= df.format(new Date());// new Date()为获取当前系统时间
        return date;
    }

    public static String getNowDataString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date= df.format(new Date());// new Date()为获取当前系统时间
        return date;
    }

    public static Date stringToDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null; //初始化date
        try {
           date = sdf.parse(str); //Mon Jan 14 00:00:00 CST 2013

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Date stringToDateTime(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null; //初始化date
        try {
            date = sdf.parse(str); //Mon Jan 14 00:00:00 CST 2013

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String dateToString(Date date){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String dateTimeToString(Date date){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


}
