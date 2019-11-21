package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class DetailedReport extends Report {
    public DetailedReport(final List<Project> rootProjects) {
        projects = rootProjects;
        elements = new ArrayList<>();
    }

    @Override
    void createReport(final Format reportFormat,
                      final long start, final long end) {
        makeBriefReport(reportFormat, "Informe detallat", start, end);

        elements.add(Table.createSubprojectTable(start, end, projects));
        elements.add(new Separator());
        elements.add(Table.createTaskTable(start, end, projects));
        elements.add(new Separator());
        elements.add(Table.createIntervalTable(start, end, projects));
        elements.add(new Separator());

        writeToFile();
        format.finishPrinting();
    }
}
