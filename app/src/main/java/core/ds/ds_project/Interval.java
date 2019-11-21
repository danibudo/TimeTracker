package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class Interval implements PropertyChangeListener, Serializable {
    private long startTime;
    private long endTime;
    private long duration;

    /**
     * Creates an <code>Interval</code> instance.
     * @param startingTime The time when the interval should start.
     * @param endingTime The time when the interval should end.
     */
    public Interval(final long startingTime, final long endingTime) {
        this.startTime = startingTime + Clock.getInstance().getCurrentTime();
        this.endTime = endingTime;
        this.duration = 0;
    }

    /**
     * Creates an <code>Interval</code> instance.
     * @param startingTime The time when the interval should start.
     */
    public Interval(final long startingTime) {
        this.startTime = startingTime + Clock.getInstance().getCurrentTime();
        this.duration = 0;
    }

    final long getDuration() {
        return duration;
    }

    final long getDuration(final long periodStart, final long periodFinish) {
        long intervalDuration = getDuration();
        if (periodStart > getStartTime()) {
            intervalDuration -= periodStart - getStartTime();
        }
        if (periodFinish < getEndTime()) {
            intervalDuration -= getEndTime() - periodFinish;
        }
        intervalDuration = Time.getSeconds(intervalDuration);
        intervalDuration = Time.setSeconds((int) intervalDuration);
        return intervalDuration;
    }

    final void stop() {
        endTime = Clock.getInstance().getCurrentTime();
    }

    final void setStartTime(final long milliseconds) {
        startTime = milliseconds;
    }

    final void setEndTime(final long milliseconds) {
        endTime = milliseconds;
    }

    final long getStartTime() {
        return startTime;
    }

    final long getEndTime() {
        return endTime;
    }

    /**
     * Updates the interval's duration with each tick of the <code>Clock</code>.
     * @param propertyChangeEvent The event used to calculate the amount
     *                            of time between ticks.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (endTime > Clock.getInstance().getCurrentTime() || endTime == 0) {
            long oldValue = (long) propertyChangeEvent.getOldValue();
            long newValue = (long) propertyChangeEvent.getNewValue();
            duration += newValue - oldValue;
        }
    }
}
