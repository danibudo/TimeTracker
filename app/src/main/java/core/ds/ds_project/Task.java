package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Task implements Activity {
    private Project ownerProject;
    private String name;
    private List<Interval> intervals;
    private long endTime;
    private long startTime;
    private boolean isRunning;

    public Task(final Project ownerProject, final String taskName) {
        this.ownerProject = ownerProject;
        this.name = taskName;
        this.intervals = new ArrayList<>();
        this.isRunning = false;
    }

    @Override
    public void print() {
        System.out.println("Task = " + getName());

    }

    @Override
    public long getDuration() {
        long duration = 0;
        for (Interval interval : intervals) {
            duration += interval.getDuration();
        }
        return duration;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {

    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    @Override
    public long getStartTime() {
//        if (!intervals.isEmpty() && startTime == 0 && isRunning) {
//            startTime = Clock.getCurrentTime();
//        }
        return startTime;
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

    public Project getOwner() {
        return ownerProject;
    }

    public void addInterval(final Interval interval) {
        if (intervals.isEmpty()) {
            startTime = interval.getStartTime();
        }
        if (interval.getEndTime() > endTime) {
            endTime = interval.getEndTime();
        }

        intervals.add(interval);
    }

    public void removeInterval(final Interval interval) {
        intervals.remove(interval);
    }

    public void start() {
        Interval interval = new Interval(this, 0);
        addInterval(interval);
        if (!ownerProject.isListening()) {
            Clock.getInstance().addPropertyChangeListener(ownerProject);
            ownerProject.setListening(true);
        }
        isRunning = true;
        if (ownerProject.getStartTime() == 0) {
            ownerProject.start(startTime);
        }
    }

    public void stop() {
        intervals.get(0).stop();
        this.endTime = intervals.get(0).getEndTime();
        this.ownerProject.stop();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void printData() {
        System.out.print("\n" + getName());
        System.out.print("\t   ");
        System.out.print(Time.getDateAndTime(getStartTime()));
        System.out.print("\t");
        if (getEndTime() != 0) {
            System.out.print(Time.getDateAndTime(getEndTime()));
            System.out.print("\t");
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        System.out.print(Time.getTime(getDuration()));
    }
}
