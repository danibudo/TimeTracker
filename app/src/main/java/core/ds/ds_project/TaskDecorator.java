package core.ds.ds_project;

public abstract class TaskDecorator extends Task {
    /**
     * The <code>Task</code> instance.
     */
    private Task task;

    TaskDecorator(final Task decoratedTask) {
        task = decoratedTask;
    }

    /**
     * Gets the <code>Task</code> instance.
     * @return The <code>Task</code> instance.
     */
    @Override
    public Task getTask() {
        assert task != null : "Task Null";
        return task;
    }

    /**
     * Sets the <code>Task</code> instance.
     * @param taskObject The <code>Task</code> instance.
     */
    public void setTask(final Task taskObject) {
        assert taskObject != null : "Trying to assign a null task";
        task = taskObject;
    }

    /**
     * Sets the starting time of the <code>Task</code>.
     * @param milliseconds The task's starting time.
     */
    @Override
    public void setStartTime(final long milliseconds) {
        task.getTask().setStartTime(milliseconds);
        task.getOwnerProject().setStartTime(milliseconds);
    }

    /**
     * Sets the ending time of the <code>Task</code>.
     * @param milliseconds The task's ending time.
     */
    @Override
    public void setEndTime(final long milliseconds) {
        task.getTask().setEndTime(milliseconds);
        task.getOwnerProject().setEndTime(milliseconds);
    }

    /**
     * Gets the starting time of the <code>Task</code>.
     * @return The task's starting time.
     */
    @Override
    public long getStartTime() {
        return task.getStartTime();
    }

    /**
     * Gets the ending time of the <code>Task</code>.
     * @return The task's ending time.
     */
    @Override
    public long getEndTime() {
        return task.getEndTime();
    }

    /**
     * Gets the duration of the <code>Task</code>.
     * @return The task's duration.
     */
    @Override
    public long getDuration() {
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
     * the <code>TaskDecorator</code> object.
     * @param visitor The visitor that accesses the object.
     */
    @Override
    void acceptVisitor(final Visitor visitor) {
        assert visitor != null : "Trying to accept a null visitor";
        visitor.visitTask(task);
    }

    /**
     * Starts the <code>Task</code>.
     */
    @Override
    void start() {
        task.start();
    }

    /**
     * Checks if the <code>Task</code> is running.
     * @return <code>true</code> if the task is running,
     * <code>false</code> if it is not.
     */
    @Override
    boolean isRunning() {
        return getStartTime() != 0
                && getEndTime() != 0
                && Clock.getInstance().getCurrentTime() > getEndTime();
    }
}
