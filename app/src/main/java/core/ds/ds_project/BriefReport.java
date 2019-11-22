package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

class BriefReport extends Report {
    /**
     * The constructor of the class <code>BriefReport</code>.
     * It initializes the list of root projects that should
     * be contained in the report and the list of elements
     * that a report should have.
     * @param rootProjects the root projects of the report
     */
    BriefReport(final List<Project> rootProjects) {
        setProjects(rootProjects);
        setElements(new ArrayList<Element>());
    }

    /**
     * Assembles a brief report out of report elements
     * taking into account the start and end times of the
     * time period which the report should describe.
     * @param reportFormat the format of the report
     * @param start the starting time of the report's period
     * @param end the ending time of the report's period
     */
    @Override
    void createReport(final Format reportFormat,
                      final long start, final long end) {
        makeBriefReport(reportFormat, "Informe breu", start, end);
        writeToFile();
        getFormat().finishPrinting();
    }
}
