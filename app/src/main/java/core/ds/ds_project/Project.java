package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
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
    public void print() {
        System.out.println("-----------------------");
        System.out.println("Project = " + getName());
        System.out.println("-----------------------");

        Iterator<Activity> activityIterator = activities.iterator();

        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            activity.print();
        }

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
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (ownerProject == null) {
            this.printData();
        }
        for (Activity activity : getActivities()) {
            activity.printData();
        }
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
    }

    public Project getOwner() {
        return ownerProject;
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

    public Activity getChild(final int i) {
        return activities.get(i);
    }

    public String getName() {
        return name;
    }

    @Override
    public long getStartTime() {
        if (!activities.isEmpty() && startTime == 0) {
            startTime = Clock.getCurrentTime();
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
        endTime = Clock.getCurrentTime();
    }

    public void setListening(final boolean listening) {
        isListening = listening;
    }

    public boolean isListening() {
        return isListening;
    }

    private boolean hasRunningTasks() {
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

    private ArrayList<Activity> getActivities() {
        Object copy;
        copy = ((ArrayList<Activity>) activities).clone();
        //noinspection unchecked
        return (ArrayList<Activity>) copy;
    }
}
