package core.ds.ds_project;

import java.util.LinkedList;

public class Time {

    public static long getDays(final long milliseconds) {
        return getHours(milliseconds) / 24;
    }

    public static long getHours(final long milliseconds) {
        return getMinutes(milliseconds) / 60;
    }

    public static long getMinutes(final long milliseconds) {
        return getSeconds(milliseconds) / 60;
    }

    public static long getSeconds(final long milliseconds) {
        return milliseconds / 1000;
    }

    public static long setDays(final int days) {
        return days * 24 * 60 * 60 * 1000;
    }

    public static long setHours(final int hours) {
        return hours * 60 * 60 * 1000;
    }

    public static long setMinutes(final int minutes) {
        return minutes * 60 * 1000;
    }

    public static long setSeconds(final int seconds) {
        return seconds * 1000;
    }

    public static String getTime(final long milliseconds) {
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
