package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class Project implements Activity {
    private String name;
    private List<Activity> activities;

    @Override
    public void printTime() {

    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        
    }

    public void addActivity(final Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(final Activity activity) {
        activities.remove(activity);
    }

  //public void getActivities(){ }


}
