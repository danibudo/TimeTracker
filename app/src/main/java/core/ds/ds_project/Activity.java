package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface Activity extends PropertyChangeListener {

    void addActivity(Activity activity);

    void removeActivity(Activity activity);

    Activity getChild(int i);

    String getName();

    void print();

    long getDuration();

    //void addInterval(Interval interval);

    //void removeInterval(Interval interval);

    Project getOwner();

    @Override
    void propertyChange(PropertyChangeEvent propertyChangeEvent);

    long getEndTime();

    long getStartTime();
}
