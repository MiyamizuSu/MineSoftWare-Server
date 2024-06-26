package com.Robin.RobinServer.Util;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RobinUtil {

    public static Date fromStringGetDate(String timeDay)  throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d");
        java.util.Date utilDate = dateFormat.parse(timeDay);
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.setTime(utilDate);
        calendar.set(Calendar.YEAR, currentYear);
        utilDate = calendar.getTime();
        Date timeDayToSql = new Date(utilDate.getTime());
        return timeDayToSql;
    }
    public static String fromTimeStampToString(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }
    public static void setDownLoadHead(String fileName, HttpServletResponse response){
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fn = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fn + ".xlsx");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
