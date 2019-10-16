package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Interval implements PropertyChangeListener {
    private Task ownerTask;
    private long startTime;
    private long endTime;

    public Interval(final Task ownerTask, final long startTime, final long finishTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime;
        this.endTime = finishTime;
    }

    public Interval(final Task ownerTask, final long startTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime;
    }

    public long getDuration() {
        //Difference in milliseconds
        return Clock.getCurrentTime() - startTime;
    }

    public void stop() {
        endTime = Clock.getCurrentTime();
    }

    public long getRemainingTime() {
        long currentTime = Clock.getCurrentTime();

        if (currentTime < startTime) return getDuration();
        else if (currentTime > endTime) return 0;
        else return endTime - currentTime;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (getRemainingTime() < getDuration()) {
            System.out.print("\n" + ownerTask.getOwner().getName());
            System.out.print("\t");
            System.out.print(startTime);
            System.out.print("\t");
            System.out.print(endTime);
            System.out.print("\t");
            System.out.print(Time.getSeconds(getDuration()));

            System.out.print("\n" + ownerTask.getName());
            System.out.print("\t");
            System.out.print(startTime);
            System.out.print("\t");
            System.out.print(endTime);
            System.out.print("\t");
            System.out.print(Time.getSeconds(getDuration()));
        }


        /*//Temporary solution for testing purposes
        if (getRemainingTime() > 0 && getRemainingTime() < getDuration()) {
            System.out.println("\"" + ownerTask.getOwner().name + "\" project:");
            //System.out.println("\"" + ownerTask.name + "\" task:");
            System.out.println(getRemainingTime() / 1000 + " seconds remaining ...\n");
        }*/
    }




}
