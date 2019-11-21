package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class BriefReport extends Report {
    public BriefReport(final List<Project> rootProjects) {
        projects = rootProjects;
        elements = new ArrayList<>();
    }

    @Override
    void createReport(final Format reportFormat,
                      final long start, final long end) {
        makeBriefReport(reportFormat, "Informe breu", start, end);
        writeToFile();
        format.finishPrinting();
    }
}
