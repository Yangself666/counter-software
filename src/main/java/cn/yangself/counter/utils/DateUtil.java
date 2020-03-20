package cn.yangself.counter.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getCurrentDate(){
        String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return day +" 00:00:00";
    }
}
