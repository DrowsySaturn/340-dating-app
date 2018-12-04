package com.datingapp.utility;
/*
 * This utility class returns the current date for use as an SQL date object.
 *
 * @author: William Buck, Vincent Yang
 * @version: 12/4/2018
 */

import android.annotation.TargetApi;
import android.os.Build;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
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
//    @TargetApi(Build.VERSION_CODES.O)
//    public static LocalDate getCurrentDateAndTime(ResultSet _resultSet) {
//        Timestamp timestamp = _resultSet.getTimestamp(i);
//        return todayLocalDate;
//    }
}
