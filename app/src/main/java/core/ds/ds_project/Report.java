package core.ds.ds_project;
import java.util.List;

public abstract class Report {

    protected Project project;
    protected Format format;
    protected List<Elements> elements;

    abstract void createReport();
}
