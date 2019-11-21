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
        list.add(this);
        return list;
    }
}
