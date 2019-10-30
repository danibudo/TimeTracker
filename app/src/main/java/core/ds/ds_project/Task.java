package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public abstract class Task extends Activity {
    private List<Interval> intervals = new ArrayList<>();
    void addInterval(final Interval interval) {
        if (intervals.isEmpty()) {
            setStartTime(interval.getStartTime());
        }
        if (interval.getEndTime() > getEndTime()) {
            setEndTime(interval.getEndTime());
        }

        intervals.add(interval);
    }
    public void removeInterval(final Interval interval) {
        intervals.remove(interval);
    }
    public List<Interval> getIntervals() {
        return intervals;
    }
    public void setIntervals(final List<Interval> intervalList) {
        intervals = intervalList;
    }

    public Task getTask() {
        return this;
    }

    abstract boolean isRunning();
    abstract void start();
}
