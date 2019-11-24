package core.ds.ds_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

public abstract class Activity implements Serializable {
    protected static Logger logger = LoggerFactory.getLogger(Activity.class);
    private Project ownerProject;
    private String name;
    private long startTime;
    private long endTime;
    private long duration;
    abstract void acceptVisitor(Visitor visitor);

    /**
     * Gets the tasks that an activity possesses.
     * @return a list of tasks
     */
    abstract List<Task> getTasks();

    /**
     * Sets the name of the <code>Activity</code>.
     * @param activityName The activity's name.
     */
    void setName(final String activityName) {
        name = activityName;
    }

    /**
     * Gets the name of the <code>Activity</code>.
     * @return The activity's name.
     */
    String getName() {
        return name;
    }

    /**
     * Sets the <code>Project</code> that should own this <code>Activity</code>.
     * @param project The owner project.
     */
    void setOwnerProject(final Project project) {
        ownerProject = project;
    }

    /**
     * Gets the <code>Project</code> that owns this <code>Activity</code>.
     * @return The owner project.
     */
    public Project getOwnerProject() {
        return ownerProject;
    }

    /**
     * Gets the name of the <code>Project</code> of the <code>Activity</code>.
     * @return The activity's name.
     */
    public String getOwnerProjectName() {
        return getOwnerProject().getName();
    }

    /**
     * Sets the time when the <code>Activity</code> should finish.
     * @param endingTime The activity's ending time.
     */
    public void setEndTime(final long endingTime) {
        endTime = endingTime;
    }

    /**
     * Gets the time when the <code>Activity</code> finished.
     * @return The activity's ending time.
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Sets the time when the <code>Activity</code> should start.
     * @param startingTime The activity's starting time.
     */
    public void setStartTime(final long startingTime) {
        startTime = startingTime;
    }

    /**
     * Gets the time when the <code>Activity</code> began.
     * @return The activity's starting time.
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets the duration of the <code>Activity</code>.
     * @param milliseconds The duration of the <code>Activity</code>.
     */
    void setDuration(final long milliseconds) {
        duration = milliseconds;
    }

    /**
     * Gets the duration of the <code>Activity</code>.
     * @return The activity's duration.
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Gets the duration of the <code>Activity</code> taking into account
     * the period of a report.
     * @param periodStart the starting time of the report
     * @param periodFinish the finishing time of the report
     * @return The activity's duration
     */
    public abstract long getDuration(long periodStart, long periodFinish);
}
