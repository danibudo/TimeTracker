package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Project implements Activity {
    private Project ownerProject;
    private String name;
    private List<Activity> activities;
    private long startTime;
    private long endTime;
    private boolean isListening;

    public Project(final Project ownerProject, final String projectName) {
        this.ownerProject = ownerProject;
        name = projectName;
        this.activities = new ArrayList<>();
    }

    @Override
    public long getDuration() {
        long duration = 0;
        for (Activity activity : getActivities()) {
            duration += activity.getDuration();
        }
        return duration;
    }

    public Project getOwner() {
        return ownerProject;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitProject(this);
        for (Activity activity : getActivities()) {
            activity.acceptVisitor(visitor);
        }
    }

    public void addActivity(final Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(final Activity activity) {
        activities.remove(activity);
    }

    public String getName() {
        return name;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    public void start(final long time) {
        startTime = time;
    }

    public void stop() {
        endTime = Clock.getInstance().getCurrentTime();
    }

    public void setListening(final boolean listening) {
        isListening = listening;
    }

    public boolean isListening() {
        return isListening;
    }

    public boolean hasRunningTasks() {
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
        return result;
    }

    public ArrayList<Activity> getActivities() {
        Object copy;
        copy = ((ArrayList<Activity>) activities).clone();
        //noinspection unchecked
        return (ArrayList<Activity>) copy;
    }
}
