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

    @Override
    public void printData() {
        System.out.print("\n" + getName());
        System.out.print("\t   ");
        if (startTime != 0) {
            System.out.print(Time.getDateAndTime(getStartTime()));
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        if (!hasRunningTasks() && endTime != 0) {
            System.out.print(Time.getDateAndTime(getEndTime()));
            System.out.print("\t");
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        System.out.print(Time.getTime(getDuration()));

        for (Activity activity : getActivities()) {
            activity.printData();
        }
    }

    public Project getOwner() {
        return ownerProject;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        this.acceptVisitor(visitor);
        for (Activity activity : getActivities()) {
            activity.acceptVisitor(visitor);
        }
    }

    public void addActivity(final Activity activity) {
        if (activities.isEmpty()) {
            startTime = activity.getStartTime();
        }
        if (activity.getEndTime() > endTime) {
            endTime = activity.getEndTime();
        }

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
        if (!activities.isEmpty() && startTime == 0) {
            startTime = Clock.getInstance().getCurrentTime();
        }
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
