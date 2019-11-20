package core.ds.ds_project;
import java.util.List;

public abstract class Report {

    private Project project;
    private Format format;
    List<Elements> elements;

    abstract void createReport();
}
