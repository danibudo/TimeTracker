package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface Visitor extends PropertyChangeListener {

    /**
     * Visits the project to use or modify it.
     * @param project The project to visit.
     */
    void visitProject(Project project);

    /**
     * Visits the task to use or modify it.
     * @param task The task to visit.
     */
    void visitTask(Task task);

    /**
     * This method is fired each time the clock ticks.
     * @param propertyChangeEvent The <code>PropertyChangeEvent</code> (ticking)
     *                            that <code>Visitor</code> listens to.
     */
    void propertyChange(PropertyChangeEvent propertyChangeEvent);

}
