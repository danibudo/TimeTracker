package core.ds.ds_project;

public interface Activity {

    void acceptVisitor(Visitor visitor);

    String getName();

    long getDuration();

    long getEndTime();

    long getStartTime();
}
