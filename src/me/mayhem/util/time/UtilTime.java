package me.mayhem.util.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTime {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm:ss");

    public static String formatTime(long difference) {
        return DATE_FORMAT.format(new Date(difference));
    }

}
