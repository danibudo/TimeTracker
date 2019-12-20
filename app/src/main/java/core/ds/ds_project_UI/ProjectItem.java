package core.ds.ds_project_UI;

import core.ds.ds_project.Project;
import core.ds.ds_project.Time;

public class ProjectItem {
    private Project project;

    public ProjectItem(final Project project) {
        this.project = project;
    }

    public String getName() {
        return project.getName();
    }

    public String getTime() {
        return Time.getTime(project.getDuration());
    }

    public String getDescription() {
        return project.getDescription();
    }
}
