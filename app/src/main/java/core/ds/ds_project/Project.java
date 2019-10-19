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
        for (Activity activity : activities) {
            duration += activity.getDuration();
        }
        return duration;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        printData(this);
        for (Activity activity : activities) {
            printData(activity);
        }
    }

    private static void printData(final Activity activity) {
        System.out.print("\n" + activity.getName());
        System.out.print("\t   ");
        System.out.print(Time.getDateAndTime(activity.getStartTime()));
        System.out.print("\t");
        if (activity.getEndTime() != 0) {
            System.out.print(Time.getDateAndTime(activity.getEndTime()));
            System.out.print("\t");
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        System.out.print(Time.getTime(activity.getDuration()));
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

    public void stop() {
        endTime = Clock.getCurrentTime();
    }

    public void setListening(final boolean listening) {
        isListening = listening;
    }

    public boolean isListening() {
        return isListening;
    }
}
