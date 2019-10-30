package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class TaskImpl extends Task {
    private boolean isRunning;

    public TaskImpl(final Project ownerProject, final String taskName) {
        setOwnerProject(ownerProject);
        setName(taskName);
        this.isRunning = false;
        setDuration(0);
    }

    @Override
    public long getDuration() {
        long intervalDurations = 0;
        for (Interval interval : getIntervals()) {
            intervalDurations += interval.getDuration();
        }
        setDuration(intervalDurations);
        return super.getDuration();
    }

    @Override
    public void acceptVisitor(final Visitor visitor) {
        visitor.visitTask(this);
    }

//    public void addInterval(final Interval interval) {
//        if (intervals.isEmpty()) {
//            setStartTime(interval.getStartTime());
//        }
//        if (interval.getEndTime() > getEndTime()) {
//            setEndTime(interval.getEndTime());
//        }
//
//        intervals.add(interval);
//    }

    @Override
    public void start() {
        Interval interval = new Interval(this, 0);
        Clock.getInstance().addPropertyChangeListener(interval);
        addInterval(interval);
        isRunning = true;
        if (getOwnerProject().getStartTime() == 0) {
            getOwnerProject().start(getStartTime());
        }
    }

    public void stop() {
        int lastIndex = getIntervals().size() - 1;
        getIntervals().get(lastIndex).stop();
        setEndTime(getIntervals().get(lastIndex).getEndTime());
        getOwnerProject().stop();
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return getStartTime() != 0
                && getEndTime() != 0
                && Clock.getInstance().getCurrentTime() < getEndTime();
    }
}
