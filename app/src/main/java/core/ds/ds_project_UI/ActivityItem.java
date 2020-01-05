package core.ds.ds_project_UI;

import core.ds.ds_project.Activity;
import core.ds.ds_project.Project;
import core.ds.ds_project.TaskImpl;
import core.ds.ds_project.Time;

public class ActivityItem {

    private String name;
    private String description;
    private String duration;
    private boolean isProject;
    private boolean isTask;
    private boolean isRunning;

    public ActivityItem(final Activity activity) {

        name = activity.getName();

        duration = Time.getTime(activity.getDuration());

        if (activity.getClass().getName().endsWith("Project")) {
            isProject = true;
            isTask = false;
            description = ((Project)activity).getDescription();

        } else {
            isProject = false;
            isTask = true;
            isRunning = ((TaskImpl)activity).isRunning();
        }
    }


    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final String getDuration() {
        return duration;
    }

    public final boolean isProject() {
        return isProject;
    }

    public final boolean isTask() {
        return isTask;
    }

    public final boolean isRunning() {
        return isRunning;
    }
}
