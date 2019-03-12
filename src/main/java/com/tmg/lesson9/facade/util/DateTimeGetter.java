package com.tmg.lesson9.facade.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeGetter {

    public static String getCurrentDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
