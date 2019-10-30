package core.ds.ds_project;

public class LimitedTask extends TaskDecorator {
    private long endTime;

    public LimitedTask(final Task task, final long milliseconds) {
        super(task.getTask());
        this.task = task.getTask();
        setIntervals(task.getTask().getIntervals());
        this.setOwnerProject(task.getTask().getOwnerProject());
        getOwnerProject().setEndTime(milliseconds);
        if (getIntervals().size() == 0) {
            Interval interval = new Interval(this, 0, milliseconds);
            Clock.getInstance().addPropertyChangeListener(interval);
            task.addInterval(interval);
        } else {
            getIntervals().get(getIntervals().size() - 1).setEndTime(milliseconds);
        }
        task.setEndTime(milliseconds);
        endTime = milliseconds;
    }

    @Override
    public long getEndTime() {
        return task.getEndTime() + endTime;
    }

    @Override
    public void start() {
        Interval interval = task.getIntervals().get(task.getIntervals().size() - 1);
        addInterval(interval);
        if (getOwnerProject().getStartTime() == 0) {
            getOwnerProject().start(getStartTime());
        }
    }

    @Override
    public Project getOwnerProject() {
        return task.getOwnerProject();
    }
}
