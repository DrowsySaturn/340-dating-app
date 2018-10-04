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
    public static java.util.Date getCurrentDateAndTime() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();
        return date;
    }
}
