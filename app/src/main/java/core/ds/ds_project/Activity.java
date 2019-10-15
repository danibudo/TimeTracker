package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface Activity extends PropertyChangeListener {

    void addActivity(Activity activity);

    void removeActivity(Activity activity);

    Activity getChild(int i);

    String getName();

    void print();

    Project getOwner();

    @Override
    void propertyChange(PropertyChangeEvent propertyChangeEvent);
}
