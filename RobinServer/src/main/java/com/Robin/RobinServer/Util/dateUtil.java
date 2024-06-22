package com.Robin.RobinServer.Util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class dateUtil {

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
}
