package core.ds.ds_project;

public class PreprogrammedTask extends TaskDecorator {
    private long startTime;

    public PreprogrammedTask(final Task task, final long milliseconds) {
        super(task.getTask());
        setIntervals(task.getTask().getIntervals());
        if (getIntervals().size() == 0) {
            Interval interval = new Interval(this, milliseconds);
            Clock.getInstance().addPropertyChangeListener(interval);
            task.addInterval(interval);
        } else {
            getIntervals().get(getIntervals().size() - 1).setStartTime(milliseconds);
        }
        task.setStartTime(milliseconds);
        startTime = milliseconds;
    }

    @Override
    public long getStartTime() {
        return task.getStartTime() + startTime;
    }
}
