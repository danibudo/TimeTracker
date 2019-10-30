package core.ds.ds_project;

import java.io.Serializable;

public abstract class Activity implements Serializable {
    private Project ownerProject;
    private String name;
    private long startTime;
    private long endTime;
    private long duration;
    abstract void acceptVisitor(Visitor visitor);

    public void setName(final String activityName) {
        name = activityName;
    }

    public String getName() {
        return name;
    }

    public void setOwnerProject(final Project project) {
        ownerProject = project;
    }

    public Project getOwnerProject() {
        return ownerProject;
    }

    public void setEndTime(final long endingTime) {
        endTime = endingTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setStartTime(final long startingTime) {
        startTime = startingTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setDuration(final long milliseconds) {
        duration = milliseconds;
    }

    public long getDuration() {
        return duration;
    }
}
