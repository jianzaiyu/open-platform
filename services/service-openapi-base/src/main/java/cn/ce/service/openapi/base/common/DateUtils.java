package cn.ce.service.openapi.base.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @ClassName: Utils
 * @Description: 工具类型
 * @author dingjia@300.cn
 *
 */
public final class DateUtils {
    
    /** 格式化日期对象 */
    private static DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", java.util.Locale.ENGLISH); 
    
    public static Date parseDate(String strDate) {
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public static String formatDate(Date date) {
        return df.format(date);
    }
}
