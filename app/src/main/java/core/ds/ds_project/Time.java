package core.ds.ds_project;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;

public class Time {

    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MILLISECONDS_IN_SECOND = 1000;

    /**
     * Convert miliseconds to days
     * @return the days
     */
    public static long getDays(final long milliseconds) {
        return getHours(milliseconds) / HOURS_IN_DAY;
    }

    /**
     *Convert miliseconds to hours
     * @return the hours
     */
    public static long getHours(final long milliseconds) {
        return getMinutes(milliseconds) / MINUTES_IN_HOUR;
    }

    /**
     *Convert miliseconds to minutes
     * @return the minutes
     */
    public static long getMinutes(final long milliseconds) {
        return getSeconds(milliseconds) / SECONDS_IN_MINUTE;
    }

    /**
     *Convert miliseconds to seconds
     * @return the seconds
     */
    public static long getSeconds(final long milliseconds) {
        return milliseconds / MILLISECONDS_IN_SECOND;
    }

    /*
    Get the number of miliseconds from the number of days
     */
    public static long setDays(final int days) {
        return days * HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
    }
    /*
    Get the number of miliseconds from the number of hours
     */
    public static long setHours(final int hours) {
        return hours * MINUTES_IN_HOUR * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
    }
    /*
    Get the number of miliseconds from the number of minutes
     */
    public static long setMinutes(final int minutes) {
        return minutes * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
    }
    /*
    Get the number of miliseconds from the number of seconds
     */
    public static long setSeconds(final int seconds) {
        return seconds * MILLISECONDS_IN_SECOND;
    }
    /**
     *Return date and time in a year-month-day hh:mm:ss format
     */
    public static String getDateAndTime(final long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return String.format("%s-%s-%s %s:%s:%s",
                String.format(Locale.ENGLISH, "%02d", day),
                String.format(Locale.ENGLISH, "%02d", month),
                String.format(Locale.ENGLISH, "%02d", year),
                String.format(Locale.ENGLISH, "%02d", hour),
                String.format(Locale.ENGLISH, "%02d", minute),
                String.format(Locale.ENGLISH, "%02d", second));
    }
    /*
    Return the time in hh:mm:ss format
     */
    public static String getTime(long milliseconds) {
        int hours = (int) getHours(milliseconds);
        milliseconds -= setHours(hours);
        int minutes = (int) getMinutes(milliseconds);
        milliseconds -= setMinutes(minutes);
        int seconds = (int) getSeconds(milliseconds);
        return String.format("%s:%s:%s",
                String.format(Locale.ENGLISH, "%02d", hours),
                String.format(Locale.ENGLISH, "%02d", minutes),
                String.format(Locale.ENGLISH, "%02d", seconds));
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
