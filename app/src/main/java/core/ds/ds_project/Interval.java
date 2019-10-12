package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Interval implements PropertyChangeListener {
    private long startTime;
    private long endTime;

    public Interval(final long start, final long finish) {
        startTime = start;
        endTime = finish;
    }

    public long getDuration() {
        //Difference in milliseconds
        return endTime - startTime;
    }

    public long getRemainingTime() {
        long currentTime = Clock.getCurrentTime();

        if (currentTime < startTime) return getDuration();
        else if (currentTime > endTime) return 0;
        else return endTime - currentTime;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        //Temporary solution for testing purposes
        if (getRemainingTime() > 0 && getRemainingTime() <= getDuration()) {
            System.out.println(getRemainingTime() / 1000 + "seconds remaining");
        }
    }

    //getFinalDate() {}
    //getBeginDate() {}



}
