package core.ds.ds_project;

import java.util.Calendar;
import java.util.LinkedList;

public class Time {

    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MILLISECONDS_IN_SECOND = 1000;

    public static long getDays(final long milliseconds) {
        return getHours(milliseconds) / HOURS_IN_DAY;
    }

    public static long getHours(final long milliseconds) {
        return getMinutes(milliseconds) / MINUTES_IN_HOUR;
    }

    public static long getMinutes(final long milliseconds) {
        return getSeconds(milliseconds) / SECONDS_IN_MINUTE;
    }

    public static long getSeconds(final long milliseconds) {
        return milliseconds / MILLISECONDS_IN_SECOND;
    }

    public static long setDays(final int days) {
        return days * HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
    }

    public static long setHours(final int hours) {
        return hours * MINUTES_IN_HOUR * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
    }

    public static long setMinutes(final int minutes) {
        return minutes * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
    }

    public static long setSeconds(final int seconds) {
        return seconds * MILLISECONDS_IN_SECOND;
    }

    public static String getDateAndTime(final long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String time = getTime(milliseconds);

        return String.valueOf(day) + '-' + month + '-' + year + ' ' + time;
    }

    public static String getTime(final long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return String.valueOf(hour) + ':' + minute + ':' + second;
    }

    public static String getRemainingTime(final long milliseconds) {
        LinkedList<String> units = new LinkedList<>();
        StringBuilder builder = new StringBuilder();

        long days = getDays(milliseconds);
        long hours = getHours(milliseconds);
        long minutes = getMinutes(milliseconds);
        long seconds = getSeconds(milliseconds);

        if (days > 0) {
            units.add(days + " days");
        }
        if (hours > 0) {
            units.add(hours + " hours");
        }
        if (minutes > 0) {
            units.add(minutes + " minutes");
        }
        if (seconds > 0 || units.isEmpty()) {
            units.add(seconds + " seconds");
        }

        while (!units.isEmpty()) {
            builder.append(units.pop());

            if (units.size() > 0) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
