package core.ds.ds_project_UI;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import core.ds.ds_project.Activity;
import core.ds.ds_project.Project;
import core.ds.ds_project.TaskImpl;
import core.ds.ds_project.Time;

public class ActivityItem implements Parcelable {

    private String name;
    private String description;
    private String duration;
    private boolean isProject;
    private boolean isTask;
    private boolean isRunning;
    private List<Activity> childActivities;

    public ActivityItem(final Activity activity) {

        name = activity.getName();

        duration = Time.getTime(activity.getDuration());

        if (activity.getClass().getName().endsWith("Project")) {
            isProject = true;
            isTask = false;
            description = ((Project)activity).getDescription();
            childActivities = ((Project) activity).getActivities();

        } else {
            isProject = false;
            isTask = true;
            isRunning = ((TaskImpl)activity).isRunning();
        }
    }


    protected ActivityItem(Parcel in) {
        name = in.readString();
        description = in.readString();
        duration = in.readString();
        isProject = in.readByte() != 0;
        isTask = in.readByte() != 0;
        isRunning = in.readByte() != 0;
    }

    public static final Creator<ActivityItem> CREATOR = new Creator<ActivityItem>() {
        @Override
        public ActivityItem createFromParcel(Parcel in) {
            return new ActivityItem(in);
        }

        @Override
        public ActivityItem[] newArray(int size) {
            return new ActivityItem[size];
        }
    };

    public List<ActivityItem> getActivityItems() {

        ArrayList<ActivityItem> activityItems = new ArrayList<>();

        for (Activity act : childActivities) {
            activityItems.add(new ActivityItem(act));
        }
        return activityItems;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(duration);
        dest.writeByte((byte) (isProject ? 1 : 0));
        dest.writeByte((byte) (isTask ? 1 : 0));
        dest.writeByte((byte) (isRunning ? 1 : 0));
    }
}
