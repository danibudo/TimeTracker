package core.ds.ds_project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Interval implements PropertyChangeListener {
    private Task ownerTask;
    private long startTime;
    private long endTime;
    private long duration;
    private boolean isRunning;
    private boolean readyToStopListening;

    public Interval(final Task ownerTask, final long startTime, final long finishTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime;
        this.endTime = finishTime;
        this.isRunning = false;
        this.readyToStopListening = false;
    }

    public Interval(final Task ownerTask, final long startTime) {
        this.ownerTask = ownerTask;
        this.startTime = startTime + Clock.getInstance().getCurrentTime();
        this.isRunning = true;
        this.readyToStopListening = false;
    }

    public long getDuration() {
        //Difference in milliseconds
        if (Clock.getInstance().getCurrentTime() > endTime && endTime != 0) {
            return endTime - startTime;
        } else {
            return Clock.getInstance().getCurrentTime() - startTime;
        }
    }

    public void stop() {
        endTime = Clock.getInstance().getCurrentTime();
        isRunning = false;
    }

    public long getRemainingTime() {
        long currentTime = Clock.getInstance().getCurrentTime();

        if (currentTime < startTime) return getDuration();
        else if (currentTime > endTime) return 0;
        else return endTime - currentTime;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (endTime < Clock.getInstance().getCurrentTime()) {
            duration = getDuration();
        }
            printData(ownerTask.getOwner());
            printData(ownerTask);

        if (endTime < Clock.getInstance().getCurrentTime()) {
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
