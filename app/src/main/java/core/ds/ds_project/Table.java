package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Table implements Elements {
    private List<String> content;
    private Table() {
        content = new ArrayList<>();
    }
    @Override
    public void accept(final Text text) {
        /// add implementation
    }
    @Override
    public void accept(final HTML html) {
        /// add implementation
    }
    public Table createPeriodTable(final Report report) {

    }
    public Table createProjectTable(final Project rootProject) {

    }
    public Table createSubprojectTable(final Project rootProject) {

    }
    public Table createTaskTable(final Project rootProject) {

    }
    public Table createIntervalTable(final Project rootProject) {

    }
}
