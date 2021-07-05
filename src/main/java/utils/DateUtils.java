package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转换成字符串
    public static String DateToString(Date date, String pattern){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        String result = simpleDateFormat.format(date);
        return result;
    }

    //字符串转日期
    public static Date StringToDate(String str,String pattern ){
        Date result=null;
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
            result = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
