package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project implements Activity {
    private Project ownerProject;
    public String name;
    private List<Activity> activities;

    public Project(final String projectName) {

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
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {

    }

    public Project getOwner () {
        return ownerProject;
    }

    public void addActivity(final Activity activity) {
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


}
