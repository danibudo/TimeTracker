package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

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
    public long getDuration() {
        long duration = 0;
        for (Interval interval : intervals) {
            duration += interval.getDuration();
        }
        return duration;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    @Override
    public long getStartTime() {
//        if (!intervals.isEmpty() && startTime == 0 && isRunning) {
//            startTime = Clock.getInstance().getCurrentTime();
//        }
        return startTime;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        this.acceptVisitor(visitor);
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
        Clock.getInstance().addPropertyChangeListener(interval);
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
