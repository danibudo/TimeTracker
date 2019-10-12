package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class Task implements Activity {
    private String name;
    private List<Interval> intervals;

    @Override
    public void printTime() {

    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {

    }

    public void addInterval(final Interval interval) {
        intervals.add(interval);
    }

    public void removeInterval(final Interval interval) {
        intervals.remove(interval);
    }

    //public void startTask() {}

    //public void getIntervals() {}
}
