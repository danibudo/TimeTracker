package core.ds.ds_project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Clock {
    private static Date currentTime;
    private PropertyChangeSupport support;
    private ScheduledExecutorService executor;
    private static Clock instance;

    private Clock() {
        currentTime = new Date();
        support = new PropertyChangeSupport(this);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public static Clock getInstance() {

        if (instance == null) {
            instance = new Clock();
        }

        return instance;
    }

    public static long getCurrentTime() {
        return currentTime.getTime();
    }

    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
    public void removePropertyChangeListener(final PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    private void tick() {
        Date oldTime = currentTime;
        currentTime = new Date();
        support.firePropertyChange("currentTime", oldTime, currentTime);
    }

    void run() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                tick();
            }
        }, 0, 2, TimeUnit.SECONDS);

    }
}
