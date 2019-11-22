package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Table implements Element {
    private String name;
    private List<String> content;
    private int columnCount;
    private Table(final String tableName, final int columns) {
        name = tableName;
        columnCount = columns;
        content = new ArrayList<>();
    }

    List<String> getContent() {
        return content;
    }
    int getColumnCount() {
        return columnCount;
    }
    String getName() {
        return name;
    }

    /**
     * Accepts a visitor to allow it to print the <code>Table</code>.
     * @param format the visitor that accesses the object
     */
    @Override
    public void accept(final Format format) {
        format.visit(this);
    }

    /**
     * Creates a table that contains information about the period
     * of the inform.
     * @param report the report to take into account
     * @return the period table
     */
    static Table createPeriodTable(final Report report) {
        String start = Time.getDateAndTime(report.getStartTime());
        String end = Time.getDateAndTime(report.getEndTime());
        String current = Time.getDateAndTime(
                Clock.getInstance().getCurrentTime());

        Table table = new Table("Periode", 2);

        table.content.add("\t\t\t");
        table.content.add("Data");
        table.content.add("Des de\t\t");
        table.content.add(start);
        table.content.add("Fins a\t\t");
        table.content.add(end);
        table.content.add("Data actual\t");
        table.content.add(current);
        return table;
    }

    /**
     * Creates a table that contains information about the projects
     * of the inform.
     * @param reportStartTime the report's starting time
     * @param reportEndTime the report's ending time
     * @param rootProjects the report's root projects
     * @return the project table
     */
    static Table createProjectTable(final long reportStartTime,
                                    final long reportEndTime,
                                    final List<Project> rootProjects) {
        Table table = new Table("Projectes de primer nivell", 4);
        table.content.add("\t");
        table.content.add("Data inici\t\t");
        table.content.add("Data final\t\t");
        table.content.add("Temps total\n");
        for (Project project : rootProjects) {
            boolean shouldInclude = shouldBeIncluded(project,
                    reportStartTime, reportEndTime);
            if (shouldInclude) {
                table.content.add(project.getName() + "\t");
                String start = Time.getDateAndTime(project.getStartTime());
                if (start.equals("")) {
                    table.content.add("\t\t\t");
                } else {
                    table.content.add(start + "\t");
                }
                String end = Time.getDateAndTime(project.getEndTime());
                if (end.equals("")) {
                    table.content.add("\t\t\t");
                } else {
                    table.content.add(end + "\t");
                }
                long duration = project.getDuration(reportStartTime,
                        reportEndTime);
                String durationString = Time.getTime(duration);
                table.content.add(durationString + "\n");
            }
        }
        return table;
    }
    /**
     * Creates a table that contains information about the subprojects
     * of the inform.
     * @param reportStartTime the report's starting time
     * @param reportEndTime the report's ending time
     * @param rootProjects the report's root projects
     * @return the subproject table
     */
    static Table createSubprojectTable(final long reportStartTime,
                                              final long reportEndTime,
                                              final List<Project> rootProjects) {
        Table table = new Table("Subprojectes", 5);
        table.content.add("\t");
        table.content.add("Dins de\t");
        table.content.add("Data inici\t\t");
        table.content.add("Data final\t\t");
        table.content.add("Temps total\n");
        for (Project project : rootProjects) {
            for (Activity activity : project.getActivities()) {
                boolean shouldInclude = shouldBeIncluded(activity,
                        reportStartTime, reportEndTime);
                if (activity instanceof Project && shouldInclude) {
                    table.content.add(activity.getName() + "\t");
                    table.content.add(activity.getOwnerProjectName() + "\t");
                    String start = Time.getDateAndTime(activity.getStartTime());
                    if (start.equals("")) {
                        table.content.add("\t\t\t");
                    } else {
                        table.content.add(start + "\t");
                    }
                    String end = Time.getDateAndTime(activity.getEndTime());
                    if (end.equals("")) {
                        table.content.add("\t\t\t");
                    } else {
                        table.content.add(end + "\t");
                    }
                    long duration = activity.getDuration(reportStartTime,
                            reportEndTime);
                    String durationString = Time.getTime(duration);
                    table.content.add(durationString + "\n");
                }
            }
        }
        return table;
    }
    /**
     * Creates a table that contains information about the tasks
     * of the inform.
     * @param reportStartTime the report's starting time
     * @param reportEndTime the report's ending time
     * @param rootProjects the report's root projects
     * @return the task table
     */
    static Table createTaskTable(final long reportStartTime,
                                        final long reportEndTime,
                                        final List<Project> rootProjects) {
        Table table = new Table("Tasques", 5);
        table.content.add("\t");
        table.content.add("Projecte");
        table.content.add("Data inici\t\t");
        table.content.add("Data final\t\t");
        table.content.add("Temps total\n");
        for (Project project : rootProjects) {
            for (Task task : project.getTasks()) {
                boolean shouldInclude = shouldBeIncluded(task,
                        reportStartTime, reportEndTime);
                if (shouldInclude) {
                    table.content.add(task.getName() + "\t");
                    table.content.add(task.getOwnerProjectName() + "\t");
                    String start = Time.getDateAndTime(task.getStartTime());
                    if (start.equals("")) {
                        table.content.add("\t\t\t");
                    } else {
                        table.content.add(start + "\t");
                    }
                    String end = Time.getDateAndTime(task.getEndTime());
                    if (end.equals("")) {
                        table.content.add("\t\t\t");
                    } else {
                        table.content.add(end + "\t");
                    }
                    long duration = task.getDuration(reportStartTime,
                            reportEndTime);
                    String durationString = Time.getTime(duration);
                    table.content.add(durationString + "\n");
                }
            }
        }
        return table;
    }
    /**
     * Creates a table that contains information about the interval
     * of the inform.
     * @param reportStartTime the report's starting time
     * @param reportEndTime the report's ending time
     * @param rootProjects the report's root projects
     * @return the interval table
     */
    static Table createIntervalTable(final long reportStartTime,
                                            final long reportEndTime,
                                            final List<Project> rootProjects) {
        Table table = new Table("Intervals", 6);
        table.content.add("Tasca\t");
        table.content.add("Projecte");
        table.content.add("Numero\t");
        table.content.add("Data inici\t\t");
        table.content.add("Data final\t\t");
        table.content.add("Durada\n");
        for (Project project : rootProjects) {
            for (Task task : project.getTasks()) {
                String projectName = task.getOwnerProjectName();
                String taskName = task.getName();
                int intervalNumber = 1;
                for (Interval interval : task.getIntervals()) {
                    boolean shouldInclude = shouldBeIncluded(interval,
                            reportStartTime, reportEndTime);
                    if (shouldInclude) {
                        table.content.add(taskName + "\t");
                        table.content.add(projectName + "\t");
                        table.content.add(intervalNumber + "\t");
                        String start =
                                Time.getDateAndTime(interval.getStartTime());
                        if (start.equals("")) {
                            table.content.add("\t\t\t");
                        } else {
                            table.content.add(start + "\t");
                        }
                        String end = Time.getDateAndTime(interval.getEndTime());
                        if (end.equals("")) {
                            table.content.add("\t\t\t");
                        } else {
                            table.content.add(end + "\t");
                        }
                        long duration = interval.getDuration(reportStartTime,
                                                             reportEndTime);
                        String durationString = Time.getTime(duration);
                        table.content.add(durationString + "\n");
                        intervalNumber++;
                    }
                }
            }
        }
        return table;
    }

    /**
     * Checks if an <code>Activity</code> should be included in a report.
     * @param activity the activity to verify
     * @param startTime the report's starting time
     * @param endTime the report's ending time
     * @return true if the <code>Activity</code> should be included, else false
     */
    private static boolean shouldBeIncluded(final Activity activity,
                                    final long startTime,
                                    final long endTime) {
        return Time.getSeconds(activity.getEndTime())
                > Time.getSeconds(startTime)
                && Time.getSeconds(activity.getStartTime())
                < Time.getSeconds(endTime);
    }
    /**
     * Checks if an <code>Interval</code> should be included in a report.
     * @param interval the interval to verify
     * @param startTime the report's starting time
     * @param endTime the report's ending time
     * @return true if the <code>Interval</code> should be included, else false
     */
    private static boolean shouldBeIncluded(final Interval interval,
                                    final long startTime,
                                    final long endTime) {
        return Time.getSeconds(interval.getEndTime())
                > Time.getSeconds(startTime)
                && Time.getSeconds(interval.getStartTime())
                < Time.getSeconds(endTime);
    }
}
