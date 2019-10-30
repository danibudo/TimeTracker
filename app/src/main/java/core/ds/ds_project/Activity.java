package core.ds.ds_project;

import java.io.Serializable;

public abstract class Activity implements Serializable {
    private Project ownerProject;
    private String name;
    private long startTime;
    private long endTime;
    private long duration;
    abstract void acceptVisitor(Visitor visitor);

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
}
