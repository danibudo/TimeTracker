package core.ds.ds_project;

public class PreprogrammedTask extends TaskDecorator {
    private long startTime;

    PreprogrammedTask(final Task task, final long milliseconds) {
        super(task.getTask());
        assert null != task : "Getting null task as parameter";
        setIntervals(task.getTask().getIntervals());
        if (getIntervals().size() == 0) {
            Interval interval = new Interval(milliseconds);
            Clock.getInstance().addPropertyChangeListener(interval);
            task.addInterval(interval);
        } else {
            getIntervals().get(getIntervals().size() - 1)
                    .setStartTime(milliseconds);
        }
        task.setStartTime(milliseconds);
        startTime = milliseconds;
    }

    /**
     * Gets the starting time of the <code>Task</code>.
     * @return The task's starting time.
     */
    @Override
    public long getStartTime() {
        return getTask().getStartTime() + startTime;
    }
}
