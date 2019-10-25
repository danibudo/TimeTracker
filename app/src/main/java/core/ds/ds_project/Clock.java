package core.ds.ds_project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Clock {
    private Date currentTime;
    private PropertyChangeSupport support;
    private ScheduledExecutorService executor;
    private static Clock instance;

    private Clock() {
        currentTime = new Date();
        support = new PropertyChangeSupport(this);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Gets the single instance of the class <code>Clock</code>.
     * @return the <code>Clock</code> instance
     */
    public static Clock getInstance() {

        if (instance == null) {
            instance = new Clock();
        }

        return instance;
    }

    long getCurrentTime() {
        return currentTime.getTime();
    }

    /**
     * Adds a listener which will be notified about every
     * time the tick method is executed.
     * <p>
     * In order to make a class be able to listen to the
     * ticks, the class must implement
     * the <code>PropertyChangeListener</code> interface.
     * @param pcl the property change listener
     */
    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
    /**
     * Make a listener stop listening to the clock ticks.
     * @param pcl the property change listener
     */
    public void removePropertyChangeListener(final PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * Updates current time and fires the property change event.
     * <p>
     * This method gets executed at a fixed rate after the
     * <code>run</code> method is used.
     */
    private void tick() {
        Date oldTime = currentTime;
        currentTime = new Date();

        if (support.hasListeners("currentTime")) {
            String tableHeader = "\nNom   Temps inici\t\t\t Temps final\t\t\tDurada (hh:mm:ss)";
            tableHeader += "\n-----+---------------------+---------------------+-----------------";

           System.out.println(tableHeader);
        }
        support.firePropertyChange("currentTime", oldTime, currentTime);
    }

    /**
     * Starts the clock.
     * <p>
     * Establishes a thread in which the <code>tick</code>
     * method will be executed at fixed rate.
     */
    void run() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                tick();
            }
        }, 0, 2, TimeUnit.SECONDS);

    }
}
