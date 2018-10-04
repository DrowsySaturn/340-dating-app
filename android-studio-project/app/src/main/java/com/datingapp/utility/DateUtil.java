package com.datingapp.utility;
/*
 * This utility class returns the current date for use as an SQL date object.
 *
 * @author: William Buck, Vincent Yang
 * @version: oct-04-2018
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * This method will generate the date, precise to
     * @return
     */
    public static Date getCurrentDateAndTime() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();
        return date;
    }

    public static void main(String[] args) {
        Date currentDate = DateUtil.getCurrentDateAndTime();
        System.out.println(currentDate.toString());
    }
}
