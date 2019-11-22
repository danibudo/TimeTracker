package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public abstract class Task extends Activity {
    /**
     * The <code>List</code> of children intervals which
     * belong to this <code>Task</code>.
     */
    private List<Interval> intervals = new ArrayList<>();

    /**
     * Adds an interval to the interval <code>List</code>.
     * @param interval The <code>Interval</code> to be added.
     */
    final void addInterval(final Interval interval) {
        assert invariant() : "Invalid Task";
        if (intervals.isEmpty()) {
            setStartTime(interval.getStartTime());
        }
        if (interval.getEndTime() > getEndTime()) {
            setEndTime(interval.getEndTime());
        }

        intervals.add(interval);
    }

    /**
     * Removes an interval from the interval <code>List</code>.
     * @param interval The <code>Interval</code> to be removed.
     */
    public final void removeInterval(final Interval interval) {
        intervals.remove(interval);
    }
    final List<Interval> getIntervals() {
        assert invariant() : "Invalid Task";
        return intervals;
    }
    final void setIntervals(final List<Interval> intervalList) {
        intervals = intervalList;
    }

    /**
     * Gets the <code>Task</code> instance.
     * @return The <code>Task</code> instance.
     */
    public Task getTask() {
        assert invariant() : "Invalid Task";
        return this;
    }

    abstract boolean isRunning();
    abstract void start();

    /**
     * Gets the task instance.
     * @return the task instance
     */
    @Override
    List<Task> getTasks() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(getTask());
        return list;
    }

    /**
     * Gets the duration that should be shown in a report.
     * @param periodStart starting time of the report's period
     * @param periodFinish ending time of the report's period
     * @return the duration of the task
     */
    @Override
    public long getDuration(final long periodStart, final long periodFinish) {
        assert invariant() : "Invalid Task";
        long taskDuration = 0;
        for (Interval interval : getIntervals()) {
            taskDuration += interval.getDuration(periodStart, periodFinish);
        }
        return taskDuration;
    }

    /**
     * Fires a set of assertions that must always hold true.
     * @return a <code>boolean</code> value describing if the invariant
     * has been satisfied
     */
    boolean invariant() {
        return intervals != null
        && getDuration() >= 0
        && getTask() != null;
    }
}
