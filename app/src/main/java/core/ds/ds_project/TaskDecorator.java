package core.ds.ds_project;

public abstract class TaskDecorator extends Task {
    protected Task task;

    public TaskDecorator(final Task decoratedTask) {
        task = decoratedTask;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public void setStartTime(final long milliseconds) {
        task.getTask().setStartTime(milliseconds);
        task.getOwnerProject().setStartTime(milliseconds);
    }

    @Override
    public void setEndTime(final long milliseconds) {
        task.getTask().setEndTime(milliseconds);
        task.getOwnerProject().setEndTime(milliseconds);
    }

    @Override
    public long getStartTime() {
        return task.getStartTime();
    }

    @Override
    public long getEndTime() {
        return task.getEndTime();
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
    void acceptVisitor(final Visitor visitor) {
        visitor.visitTask(task);
    }

    @Override
    void start() {
        task.start();
    }

    @Override
    boolean isRunning() {
        return getStartTime() != 0
                && getEndTime() != 0
                && Clock.getInstance().getCurrentTime() > getEndTime();
    }
}
