package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class Project implements Activity {
    private Project ownerProject;
    public String name;
    private List<Activity> activities;

    public Project(final String projectName, final Project project) {
        ownerProject = project;
        name = projectName;
    }

    @Override
    public void printTime() {

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

  //public void getActivities(){ }


}
