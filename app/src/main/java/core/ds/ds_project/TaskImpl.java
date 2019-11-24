package core.ds.ds_project;

public class TaskImpl extends Task {
    TaskImpl(final Project ownerProject, final String taskName) {
        setOwnerProject(ownerProject);
        setName(taskName);
        setDuration(0);
    }

    @Override
    public final long getDuration() {
        assert invariant() : "Invalid Task";
        long intervalDurations = 0;
        for (Interval interval : getIntervals()) {
            intervalDurations += interval.getDuration();
        }
        setDuration(intervalDurations);
        return super.getDuration();
    }

    /**
     * When a <code>Visitor</code> tries to access the object,
     * this method accepts the visitor and lets it use or modify
     * the <code>TaskImpl</code> object.
     * @param visitor The visitor that accesses the object.
     */
    @Override
    public void acceptVisitor(final Visitor visitor) {
        visitor.visitTask(this);
    }

    /**
     * Starts the task by creating a new interval that starts immediately.
     */
    @Override
    public void start() {
        assert invariant() : "Invalid Task";
        Interval interval = new Interval(0);
        Clock.getInstance().addPropertyChangeListener(interval);
        addInterval(interval);
        if (getOwnerProject().getStartTime() == 0) {
            getOwnerProject().start(getStartTime());
        }
    }

    /**
     * Stops the task by stopping the last created interval.
     * <p>
     * Updates ending times of the task and its owner project.
     */
    final void stop() {
        assert invariant() : "Invalid Task";
        int lastIndex = getIntervals().size() - 1;
        getIntervals().get(lastIndex).stop();
        setEndTime(getIntervals().get(lastIndex).getEndTime());
        getOwnerProject().stop();
    }

    /**
     * Checks if the <code>TaskImpl</code> is running.
     * @return <code>true</code> if the task is running,
     * <code>false</code> if it is not.
     */
    @Override
    public boolean isRunning() {
        assert invariant() : "Invalid Task";
        return getStartTime() != 0
                && getEndTime() != 0
                && Clock.getInstance().getCurrentTime() < getEndTime();
    }
}
