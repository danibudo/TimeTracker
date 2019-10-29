package core.ds.ds_project;

import java.beans.PropertyChangeEvent;

public class Printer implements Visitor {

    private Project rootProject;

    Printer(final Project project) {
        rootProject = project;
    }

    @Override
    public void visitProject(final Project project) {

        System.out.print("\n" + project.getName());
        System.out.print("\t   ");
        if (project.getStartTime() != 0) {
            System.out.print(Time.getDateAndTime(project.getStartTime()));
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        if (!project.hasRunningTasks() && project.getEndTime() != 0) {
            System.out.print(Time.getDateAndTime(project.getEndTime()));
            System.out.print("\t");
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        System.out.print(Time.getTime(project.getDuration()));

    }

    @Override
    public void visitTask(final Task task) {

        System.out.print("\n" + task.getName());
        System.out.print("\t   ");
        if (task.getStartTime() != 0) {
            System.out.print(Time.getDateAndTime(task.getStartTime()));
        } else {
            System.out.print("\t\t\t\t\t");
        }
            System.out.print("\t");
        if (task.getEndTime() != 0) {
            System.out.print(Time.getDateAndTime(task.getEndTime()));
            System.out.print("\t");
        } else {
            System.out.print("\t\t\t\t\t");
        }
        System.out.print("\t");
        System.out.print(Time.getTime(task.getDuration()));
    }

    @Override
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        printHeader();
        rootProject.acceptVisitor(this);
    }

    private void printHeader() {
        String tableHeader = "\nNom   Temps inici\t\t\t Temps final\t\t\tDurada (hh:mm:ss)";
        tableHeader += "\n-----+---------------------+---------------------+-----------------";

        System.out.println(tableHeader);
    }
}