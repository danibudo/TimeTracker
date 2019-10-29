package core.ds.ds_project;

import android.util.Printer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class Interval implements PropertyChangeListener, Serializable {
    private Task ownerTask;
    private long startTime;
    private long endTime;
    private long duration;
    private boolean isRunning;
    private boolean readyToStopListening;

    public Interval(final Task ownerTask, final long startTime, final long finishTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime;
        this.endTime = finishTime;
        this.isRunning = false;
        this.readyToStopListening = false;
        this.duration = 0;
    }

    public Interval(final Task ownerTask, final long startTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime + Clock.getInstance().getCurrentTime();
        this.isRunning = true;
        this.readyToStopListening = false;
        this.duration = 0;
    }

    public long getDuration() {
        //Difference in milliseconds
//        if (Clock.getInstance().getCurrentTime() > endTime && endTime != 0) {
//            return endTime - startTime;
//        } else {
//            return Clock.getInstance().getCurrentTime() - startTime;
//        }
        return duration;
    }

    public void stop() {
        endTime = Clock.getInstance().getCurrentTime();
        isRunning = false;
    }

    public long getRemainingTime() {
        long currentTime = Clock.getInstance().getCurrentTime();

        if (currentTime < startTime) {
            return getDuration();
        } else if (currentTime > endTime) {
            return 0;
        } else {
            return endTime - currentTime;
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (endTime == 0) {
            long oldValue = (long) propertyChangeEvent.getOldValue();
            long newValue = (long) propertyChangeEvent.getNewValue();
            duration += newValue - oldValue;
        }
    }
}
