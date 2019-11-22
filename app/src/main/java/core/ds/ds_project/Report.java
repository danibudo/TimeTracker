package core.ds.ds_project;
import java.util.List;

public abstract class Report {

    /**
     * A <code>List</code> of root projects that should be included
     * in the <code>Report</code>.
     */
    private List<Project> projects;
    private Format format;
    private List<Element> elements;
    private long startTime;
    private long endTime;

    abstract void createReport(Format reportFormat,
                               long start, long end);

    /**
     * Gets the start time of the report.
     * @return the report's starting time
     */
    public long getStartTime() {
        return startTime;
    }
    /**
     * Gets the end time of the report.
     * @return the report's ending time
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Assembles a brief report out of report elements
     * taking into account the start and end times of the
     * time period which the report should describe.
     * @param reportFormat the format of the report
     * @param title the title of the report
     * @param start the starting time of the report's period
     * @param end the ending time of the report's period
     */
    void makeBriefReport(final Format reportFormat, final String title,
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

    /**
     * Writes the elements of the report to a file.
     */
    void writeToFile() {
        for (Element element : elements) {
            element.accept(format);
        }
    }

    /**
     * Gets the root projects of the report in a <code>List</code>.
     * @return the report's root projects
     */
    List<Project> getProjects() {
        return projects;
    }
    /**
     * Sets the root projects of the report in a <code>List</code>.
     * @param rootProjects the report's root projects
     */
    void setProjects(final List<Project> rootProjects) {
        projects = rootProjects;
    }
    /**
     * Gets the elements of the report in a <code>List</code>.
     * @return the report's elements
     */
    List<Element> getElements() {
        return elements;
    }
    /**
     * Sets the elements of the report in a <code>List</code>.
     * @param reportElements the report's elements
     */
    void setElements(final List<Element> reportElements) {
        elements = reportElements;
    }

    /**
     * Gets the format of the report.
     * @return the report's format
     */
    Format getFormat() {
        return format;
    }
}
