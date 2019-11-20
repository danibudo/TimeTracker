package core.ds.ds_project;
import java.util.List;

public abstract class Report {

    protected Project project;
    protected Format format;
    protected List<Elements> elements;
    private long startTime;
    private long endTime;

    abstract void createReport();
    protected long getStartTime() {
        return startTime;
    }
    protected long getEndTime() {
        return endTime;
    }
}
