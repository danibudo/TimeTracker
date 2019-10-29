package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Task implements Activity {
    private Project ownerProject;
    private String name;
    private List<Interval> intervals;
    private long endTime;
    private long startTime;
    private long duration;
    private boolean isRunning;

    public Task(final Project ownerProject, final String taskName) {
        this.ownerProject = ownerProject;
        this.name = taskName;
        this.intervals = new ArrayList<>();
        this.isRunning = false;
        this.duration = 0;
    }

    @Override
    public long getDuration() {
        long intervalDurations = 0;
        for (Interval interval : intervals) {
            intervalDurations += interval.getDuration();
        }
        duration = intervalDurations;
        return duration;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitTask(this);
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
        Clock.getInstance().addPropertyChangeListener(interval);
        addInterval(interval);
        isRunning = true;
        if (ownerProject.getStartTime() == 0) {
            ownerProject.start(startTime);
        }
    }

    public void stop() {
        int lastIndex = intervals.size() - 1;
        intervals.get(lastIndex).stop();
        this.endTime = intervals.get(lastIndex).getEndTime();
        this.ownerProject.stop();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
