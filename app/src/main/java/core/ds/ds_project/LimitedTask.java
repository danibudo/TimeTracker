package core.ds.ds_project;

public class LimitedTask extends TaskDecorator {
    private long endTime;

    LimitedTask(final Task task, final long milliseconds) {
        super(task.getTask());
        assert null != task : "Getting null task as parameter";
        setTask(task.getTask());
        setIntervals(task.getTask().getIntervals());
        this.setOwnerProject(task.getTask().getOwnerProject());
        getOwnerProject().setEndTime(milliseconds);
        if (getIntervals().size() == 0) {
            Interval interval = new Interval(0, milliseconds);
            Clock.getInstance().addPropertyChangeListener(interval);
            task.addInterval(interval);
        } else {
            getIntervals().get(getIntervals().size() - 1)
                    .setEndTime(milliseconds);
        }
        task.setEndTime(milliseconds);
        endTime = milliseconds;
    }

    /**
     * Gets the ending time of the <code>Task</code>.
     * @return The task's ending time.
     */
    @Override
    public long getEndTime() {
        return getTask().getEndTime() + endTime;
    }

    /**
     * Starts the task by creating a new interval if no intervals exist.
     */
    @Override
    public void start() {
        Interval interval = getTask().getIntervals()
                .get(getTask().getIntervals().size() - 1);
        addInterval(interval);
        if (getOwnerProject().getStartTime() == 0) {
            getOwnerProject().start(getStartTime());
        }
    }

    /**
     * Gets the <code>Project</code> that owns this <code>Activity</code>.
     * @return The owner project.
     */
    @Override
    public Project getOwnerProject() {
        return getTask().getOwnerProject();
    }
}
