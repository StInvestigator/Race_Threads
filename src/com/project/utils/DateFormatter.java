package com.project.utils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateFormatter {
    public static String convertToTime(long time){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(time);
    }
}
