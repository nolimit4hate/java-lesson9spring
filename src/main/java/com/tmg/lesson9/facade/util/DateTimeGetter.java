package com.tmg.lesson9.facade.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class have method for getting current date-time information in string type
 */

public class DateTimeGetter {

    /**
     *  Return string with current date-time information
     *
     * @return string with current date-time information in form (yyy-MM-dd HH:mm:ss)
     */

    public static String getCurrentDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
