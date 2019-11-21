package core.ds.ds_project;

import java.util.List;

public class BriefReport extends Report {
    @Override
    void createReport(final List<Project> rootProjects,
                      final Format reportFormat,
                      final long start, final long end) {
        projects = rootProjects;
        format = reportFormat;
        startTime = start;
        endTime = end;

        elements.add(new Separator());
        elements.add(new Title("Informe breu"));
        elements.add(new Separator());
        elements.add(Table.createPeriodTable(this));
        elements.add(new Separator());
        elements.add(Table.createProjectTable(projects));
        elements.add(new Separator());
    }

    @Override
    void writeToFile() {
        for (Element element : elements) {
            element.accept(format);
        }
    }
}
