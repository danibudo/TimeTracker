package core.ds.ds_project;
import java.util.List;

public abstract class Report {

    protected List<Project> projects;
    protected Format format;
    protected List<Element> elements;
    protected long startTime;
    protected long endTime;

    abstract void createReport(Format reportFormat,
                               long start, long end);

    protected long getStartTime() {
        return startTime;
    }
    protected long getEndTime() {
        return endTime;
    }

    public void makeBriefReport(final Format reportFormat, final String title,
                                final long start, final long end) {
        format = reportFormat;
        startTime = start;
        endTime = end;

        elements.add(new Separator());
        elements.add(new Title(title));
        elements.add(new Separator());
        elements.add(Table.createPeriodTable(this));
        elements.add(new Separator());
        elements.add(Table.createProjectTable(start, end, projects));
        elements.add(new Separator());
    }
    public void writeToFile() {
        for (Element element : elements) {
            element.accept(format);
        }
    }
}
