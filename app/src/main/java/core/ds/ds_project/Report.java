package core.ds.ds_project;
import java.util.List;

public abstract class Report {

    protected List<Project> projects;
    protected Format format;
    protected List<Element> elements;
    protected long startTime;
    protected long endTime;

    abstract void createReport(List<Project> rootProject, Format reportFormat,
                               long start, long end);
    abstract void writeToFile();

    protected long getStartTime() {
        return startTime;
    }
    protected long getEndTime() {
        return endTime;
    }
}
