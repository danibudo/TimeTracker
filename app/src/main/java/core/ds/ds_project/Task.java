package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class Task implements Activity {
    private Project ownerProject;
    public String name;
    private List<Interval> intervals;

    public Task(final String taskName, final Project project) {
        ownerProject = project;
        name = taskName;
    }

    @Override
    public void printTime() {

    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {

    }

    public Project getOwner () {
        return ownerProject;
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
