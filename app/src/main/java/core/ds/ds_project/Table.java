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
        text.visit(this);
    }
    @Override
    public void accept(final HTML html) {
        html.visit(this);
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
    public static Table createProjectTable(final List<Project> rootProjects) {
        Table table = new Table("Projectes de primer nivell");
        table.content.add("\t");
        table.content.add("Data inici\t");
        table.content.add("Data final\t");
        table.content.add("Temps total\n");
        for (Project project : rootProjects) {
            table.content.add(project.getName());
            table.content.add("\n");
            String start = Time.getDateAndTime(project.getStartTime());
            table.content.add(start);
            table.content.add("\t");
            String end = Time.getDateAndTime(project.getEndTime());
            table.content.add(end);
            table.content.add("\t");
            String duration = Time.getTime(project.getDuration());
            table.content.add(duration);
        }

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
                table.content.add("\t");
                table.content.add(activity.getOwnerProject().getName());
                table.content.add("\t");
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
        Table table = new Table("Tasques");
        table.content.add("\t");
        table.content.add("Projecte\t");
        table.content.add("Data inici\t");
        table.content.add("Data final\t");
        table.content.add("Temps total\n");
        for (Task task : rootProject.getTasks()) {
            table.content.add(task.getName());
            table.content.add("\t");
            table.content.add(task.getOwnerProject().getName());
            table.content.add("\t");
            String start = Time.getDateAndTime(task.getStartTime());
            table.content.add(start);
            table.content.add("\t");
            String end = Time.getDateAndTime(task.getEndTime());
            table.content.add(end);
            table.content.add("\t");
            String duration = Time.getTime(task.getDuration());
            table.content.add(duration);
        }
        return table;
    }
    public static Table createIntervalTable(final Project rootProject) {
        Table table = new Table("Intervals");
        table.content.add("Tasca\t");
        table.content.add("Projecte\t");
        table.content.add("Numero\t");
        table.content.add("Data inici\t");
        table.content.add("Data final\t");
        table.content.add("Durada\n");
        for (Task task : rootProject.getTasks()) {
            String projectName = task.getOwnerProject().getName();
            String taskName = task.getName();
            int intervalNumber = 1;
            for (Interval interval : task.getIntervals()) {
                table.content.add(taskName);
                table.content.add("\t");
                table.content.add(projectName);
                table.content.add("\t");
                table.content.add(String.valueOf(intervalNumber));
                table.content.add("\t");
                String start = Time.getDateAndTime(interval.getStartTime());
                table.content.add(start);
                table.content.add("\t");
                String end = Time.getDateAndTime(interval.getEndTime());
                table.content.add(end);
                table.content.add("\t");
                String duration = Time.getTime(interval.getDuration());
                table.content.add(duration);
                intervalNumber++;
            }

        }
        return table;
    }
}
