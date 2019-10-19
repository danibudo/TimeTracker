package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Interval implements PropertyChangeListener {
    private Task ownerTask;
    private long startTime;
    private long endTime;
    private long duration;
    private boolean readyToStopListening = false;

    public Interval(final Task ownerTask, final long startTime, final long finishTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime;
        this.endTime = finishTime;
    }

    public Interval(final Task ownerTask, final long startTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime + Clock.getCurrentTime();
    }

    public long getDuration() {
        //Difference in milliseconds
        if (Clock.getCurrentTime() > endTime && endTime != 0) {
            return endTime - startTime;
        } else {
            return Clock.getCurrentTime() - startTime;
        }
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
        if (endTime < Clock.getCurrentTime()) {
            duration = getDuration();
        }
            printData(ownerTask.getOwner());
            printData(ownerTask);

        if (endTime < Clock.getCurrentTime()) {
            if (readyToStopListening) {
                Clock.getInstance().removePropertyChangeListener(this);
            }
            readyToStopListening = true;
        }
        /*//Temporary solution for testing purposes
        if (getRemainingTime() > 0 && getRemainingTime() < getDuration()) {
            System.out.println("\"" + ownerTask.getOwner().name + "\" project:");
            //System.out.println("\"" + ownerTask.name + "\" task:");
            System.out.println(getRemainingTime() / 1000 + " seconds remaining ...\n");
        }*/
    }

    private void printData(final Activity activity) {
        System.out.print("\n" + activity.getName());
        System.out.print("\t   ");
        System.out.print(Time.getDateAndTime(startTime));
        System.out.print("\t");
        if (endTime != 0) {
            System.out.print(Time.getDateAndTime(endTime));
            System.out.print("\t");
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        System.out.print(Time.getTime(activity.getDuration()));
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
