package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface Visitor extends PropertyChangeListener {

    void visitProject(Project project);

    void visitTask(Task task);

    void propertyChange(final PropertyChangeEvent propertyChangeEvent);

}