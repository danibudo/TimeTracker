package core.ds.ds_project;

import java.util.Calendar;
import java.util.Locale;

public final class Time {

    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MILLISECONDS_IN_SECOND = 1000;

    private Time() { }

    /**
     * Converts milliseconds to days.
     * @param milliseconds time to convert into days
     * @return the days
     */
    public static long getDays(final long milliseconds) {
        return (int) Math.round((double) getHours(milliseconds) / HOURS_IN_DAY);
    }

    /**
     *Converts milliseconds to hours.
     * @param milliseconds time to convert into hours
     * @return the hours
     */
    public static long getHours(final long milliseconds) {
        return (int) Math.round((double) getMinutes(milliseconds) / MINUTES_IN_HOUR);
    }

    /**
     *Converts milliseconds to minutes.
     * @param milliseconds time to convert into minutes
     * @return the minutes
     */
    public static long getMinutes(final long milliseconds) {
        return (int) Math.round((double) getSeconds(milliseconds) / SECONDS_IN_MINUTE);
    }

    /**
     *Converts milliseconds to seconds.
     * @param milliseconds time to convert into seconds
     * @return the seconds
     */
    public static long getSeconds(final long milliseconds) {
        return (int) Math.round((double) milliseconds / MILLISECONDS_IN_SECOND);
    }

    /**
     * Gets the number of milliseconds from the number of days.
     * @param days the number of days to convert into milliseconds
     * @return the milliseconds
     */
    public static long setDays(final int days) {
        return days * HOURS_IN_DAY
                    * MINUTES_IN_HOUR
                    * SECONDS_IN_MINUTE
                    * MILLISECONDS_IN_SECOND;
    }

    /**
     * Gets the number of milliseconds from the number of hours.
     * @param hours the number of hours to convert into milliseconds
     * @return the milliseconds
     */
    public static long setHours(final int hours) {
        return hours * MINUTES_IN_HOUR
                     * SECONDS_IN_MINUTE
                     * MILLISECONDS_IN_SECOND;
    }

    /**
     * Gets the number of milliseconds from the number of minutes.
     * @param minutes the number of minutes to convert into milliseconds
     * @return the milliseconds
     */
    public static long setMinutes(final int minutes) {
        return minutes * SECONDS_IN_MINUTE
                       * MILLISECONDS_IN_SECOND;
    }

    /**
     * Gets the number of milliseconds from the number of seconds.
     * @param seconds the number of seconds to convert into milliseconds
     * @return the milliseconds
     */
    public static long setSeconds(final int seconds) {
        return seconds * MILLISECONDS_IN_SECOND;
    }

    /**
     * Turns a date written in milliseconds into a date and time representation
     * in a dd-mm-yyyy hh:mm:ss format.
     * @param milliseconds date and time in milliseconds
     * @return <code>String</code> with date and time
     */
    static String getDateAndTime(final long milliseconds) {
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
    /**
     * Turns a date written in milliseconds into a date representation
     * in a dd-mm-yyyy format.
     * @param milliseconds date in milliseconds
     * @return <code>String</code> with date
     */
    static String getDate(final long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        return String.format("%s-%s-%s",
                String.format(Locale.ENGLISH, "%02d", day),
                String.format(Locale.ENGLISH, "%02d", month),
                String.format(Locale.ENGLISH, "%02d", year));
    }
    /**
     * Converts milliseconds into a time representation
     * in a hh:mm:ss format.
     * @param milliseconds date and time in milliseconds
     * @return <code>String</code> with date and time
     */
    public static String getTime(final long milliseconds) {
        long timeInMilliseconds = milliseconds;
        int hours = (int) getHours(timeInMilliseconds);
        timeInMilliseconds -= setHours(hours);
        int minutes = (int) getMinutes(timeInMilliseconds);
        timeInMilliseconds -= setMinutes(minutes);
        int seconds = (int) getSeconds(timeInMilliseconds);
        return String.format("%s:%s:%s",
                String.format(Locale.ENGLISH, "%02d", hours),
                String.format(Locale.ENGLISH, "%02d", minutes),
                String.format(Locale.ENGLISH, "%02d", seconds));
    }
}
