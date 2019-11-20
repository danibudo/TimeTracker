package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Table implements Elements {
    private String name;
    private List<String> content;
    private Table(final String tableName) {
        name = tableName;
        content = new ArrayList<>();
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public void accept(final Text text) {
        /// add implementation
    }
    @Override
    public void accept(final HTML html) {
        /// add implementation
    }
    public static Table createPeriodTable(final Report report) {
        String start = Time.getDate(report.getStartTime());
        String end = Time.getDate(report.getEndTime());
        String current = Time.getDate(Clock.getInstance().getCurrentTime());

        Table table = new Table("Periode");
        table.content.add("Des de\t");
        table.content.add(start);
        table.content.add("\n");
        table.content.add("Fins a\t");
        table.content.add(end);
        table.content.add("\n");
        table.content.add("Data actual\t");
        table.content.add(current);
        return table;
    }
    public static Table createProjectTable(final Project rootProject) {
        Table table = new Table("Projectes de primer nivell");
        table.content.add("\t");
        table.content.add("Data inici\t");
        table.content.add("Data final\t");
        table.content.add("Temps total\n");
        table.content.add(rootProject.getName());
        table.content.add("\n");
        String start = Time.getDateAndTime(rootProject.getStartTime());
        table.content.add(start);
        table.content.add("\t");
        String end = Time.getDateAndTime(rootProject.getEndTime());
        table.content.add(end);
        table.content.add("\t");
        String duration = Time.getTime(rootProject.getDuration());
        table.content.add(duration);
        return table;
    }
    public static Table createSubprojectTable(final Project rootProject) {
        Table table = new Table("Subprojectes");
        table.content.add("\t");
        table.content.add("Dins de\t");
        table.content.add("Data inici\t");
        table.content.add("Data final\t");
        table.content.add("Temps total\n");
        for (Activity activity : rootProject.getActivities()) {
            if (activity instanceof Project) {
                table.content.add(activity.getName());
                table.content.add("\n");
                String start = Time.getDateAndTime(activity.getStartTime());
                table.content.add(start);
                table.content.add("\t");
                String end = Time.getDateAndTime(activity.getEndTime());
                table.content.add(end);
                table.content.add("\t");
                String duration = Time.getTime(activity.getDuration());
                table.content.add(duration);
            }
        }
        return table;
    }
    public static Table createTaskTable(final Project rootProject) {

    }
    public static Table createIntervalTable(final Project rootProject) {

    }
}
