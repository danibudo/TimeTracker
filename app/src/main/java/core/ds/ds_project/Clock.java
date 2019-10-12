package core.ds.ds_project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Clock {
    private Date date;
    private PropertyChangeSupport support;
    private ScheduledExecutorService executor;

    Clock() {
        date = new Date();
        support = new PropertyChangeSupport(this);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
    public void removePropertyChangeListener(final PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    private void tick() {
        date = new Date();
        System.out.println(date);
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
