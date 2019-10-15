package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class Task implements Activity {
    private Project ownerProject;
    public String name;
    private List<Interval> intervals;

    public Task(final String taskName) {
        name = taskName;
    }

    @Override
    public void print() {
        System.out.println("Task = " + getName());

    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {

    }

    public void addActivity(final Activity activity) {
        //this is leaf node so this method is not applicable
    }

    public void removeActivity(final Activity activity) {
        //this is leaf node so this method is not applicable
    }

    public Activity getChild(final int i) {
        //this is leaf node so this method is not applicable
        return null;
    }

    public String getName() {
        return name;
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


}
