package core.ds.ds_project;

import java.io.Serializable;

public interface Activity extends Serializable {

    void acceptVisitor(Visitor visitor);

    String getName();

    long getDuration();

    long getEndTime();

    long getStartTime();
}
