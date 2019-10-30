package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Project extends Activity {
    private List<Activity> activities;
    private boolean isListening;

    public Project(final Project ownerProject, final String projectName) {
        setOwnerProject(ownerProject);
        setName(projectName);
        this.activities = new ArrayList<>();
        setDuration(0);
    }

    @Override
    public long getDuration() {
        long taskDurations = 0;
        for (Activity activity : getActivities()) {
            taskDurations += activity.getDuration();
        }
        setDuration(taskDurations);
        return super.getDuration();
    }

    @Override
    public void acceptVisitor(final Visitor visitor) {
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

    public void start(final long time) {
        setStartTime(time);
    }

    public void stop() {
        setEndTime(Clock.getInstance().getCurrentTime());
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
