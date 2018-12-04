package com.datingapp.utility;
/*
 * This utility class returns the current date for use as an SQL date object.
 *
 * @author: William Buck, Vincent Yang
 * @version: oct-04-2018
 */

import android.annotation.TargetApi;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;


/*
 * Returns a date object.
 * Note: Object must be cast as the appropriate SQL object when inserted into database
 */
public class DateUtil {
    /**
     * This method will generate the date, precise to
     * @return
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static LocalDate getCurrentDateAndTime() {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of(""));
        return todayLocalDate;
    }
}
