package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface Activity extends PropertyChangeListener {

    void printTime();

    @Override
    void propertyChange(PropertyChangeEvent propertyChangeEvent);
}
