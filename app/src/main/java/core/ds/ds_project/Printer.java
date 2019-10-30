package core.ds.ds_project;

import java.beans.PropertyChangeEvent;

public class Printer implements Visitor {

    /**
     * Highest project in the tree of activities to print.
     */
    private Project rootProject;

    Printer(final Project project) {
        rootProject = project;
    }

    /**
     * Visits the project and prints all information about it.
     * @param project The project of which information is printed.
     */
    @Override
    public final void visitProject(final Project project) {

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

    /**
     * Visits the task and prints all information about it.
     * @param task The task of which information is printed.
     */
    @Override
    public final void visitTask(final Task task) {

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

    /**
     * Each time the clock ticks, the header of the table
     * is printed and then the tree of activities.
     * @param pce The <code>PropertyChangeEvent</code> (ticking) that
     *            <code>Printer</code> listens to.
     */
    @Override
    public final void propertyChange(final PropertyChangeEvent pce) {
        printHeader();
        rootProject.acceptVisitor(this);
    }

    private void printHeader() {
        String header;
        header = "\nNom   Temps inici\t\t\t Temps final\t\t\tDurada (hh:mm:ss)";
        header += "\n-----+---------------------"
                + "+---------------------+-----------------";

        System.out.println(header);
    }
}
