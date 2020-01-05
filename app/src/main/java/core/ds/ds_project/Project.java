package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Project extends Activity {
    /**
     * The <code>List</code> of children activities which
     * belong to this <code>Project</code>.
     */
    private List<Activity> activities;
    private String description;

    /**
     * Creates a new instance of class <code>Project</code>.
     * @param ownerProject The <code>Project</code>
     *                     which should own this project.
     * @param projectName The name which this project should have.
     */
    public Project(final Project ownerProject, final String projectName) {
        setOwnerProject(ownerProject);
        setName(projectName);
        this.activities = new ArrayList<>();
        setDuration(0);
    }

    @Override
    public final long getDuration() {
        long taskDurations = 0;
        for (Activity activity : getActivities()) {
            taskDurations += activity.getDuration();
        }
        setDuration(taskDurations);
        return super.getDuration();
    }

    /**
     * Gets the duration that should be shown in a report.
     * @param periodStart starting time of the report's period
     * @param periodFinish ending time of the report's period
     * @return the duration of the project
     */
    @Override
    public long getDuration(final long periodStart, final long periodFinish) {
        long projectDuration = 0;
        for (Activity activity : getActivities()) {
            projectDuration += activity.getDuration(periodStart, periodFinish);
        }
        return projectDuration;
    }

    /**
     * When a <code>Visitor</code> tries to access the object,
     * this method accepts the visitor and helps it access
     * the object's child activities.
     * @param visitor The visitor that accesses the object.
     */
    @Override
    public final void acceptVisitor(final Visitor visitor) {
        visitor.visitProject(this);
        for (Activity activity : getActivities()) {
            activity.acceptVisitor(visitor);
        }
    }

    final void addActivity(final Activity activity) {
        activities.add(activity);
    }

    final void removeActivity(final Activity activity) {
        activities.remove(activity);
    }

    final void start(final long time) {
        setStartTime(time);
        System.out.println();
        logger.info("Project started at "
                + Time.getDateAndTime(getStartTime()));
    }

    final void stop() {
        setEndTime(Clock.getInstance().getCurrentTime());
        System.out.println();
        logger.info("Project stopped at "
                + Time.getDateAndTime(Clock.getInstance().getCurrentTime()));
    }

    final boolean hasRunningTasks() {
        boolean result = false;
        for (Activity activity : getActivities()) {
            if (activity instanceof Task) {
                if (((Task) activity).isRunning()) {
                   result = true;
                }
            } else {
                if (((Project) activity).hasRunningTasks()) {
                    result = true;
                }
            }
        }
        if (!result) {
            System.out.println();
            logger.warn("Project has no running tasks");
        }
        return result;
    }

    /**
     * Clones the <code>List</code> of activities and returns it.
     * @return The copy of the activity list.
     */
    public List<Activity> getActivities() {
        Object copy;
        copy = ((ArrayList<Activity>) activities).clone();
        //noinspection unchecked
        return (ArrayList<Activity>) copy;
    }

    /**
     * Gets the task of the project and its child projects.
     * @return a list of tasks
     */
    @Override
    List<Task> getTasks() {
        ArrayList<Task> list = new ArrayList<>();
        for (Activity activity : getActivities()) {
            list.addAll(activity.getTasks());
        }
        return list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
