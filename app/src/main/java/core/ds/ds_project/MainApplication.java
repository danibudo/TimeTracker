package core.ds.ds_project;

import android.widget.ActionMenuView;

import java.util.concurrent.TimeUnit;

public class MainApplication {
    public static void main(String[] args) {

        Clock clock = Clock.getInstance();

        Activity project1 = new Project(null, "P1");
        Printer printer = new Printer((Project) project1);
        clock.addPropertyChangeListener(printer);


        Activity task3 = new Task((Project) project1, "T3");
        ((Project) project1).addActivity(task3);
        Activity project2 = new Project( (Project) project1, "P2");
        Activity task2 = new Task((Project) project2, "T2");
        Activity task1 = new Task((Project) project2, "T1");
        ((Project) project2).addActivity(task2);
        ((Project) project2).addActivity(task1);

        Activity task4 = new Task((Project) project1, "T4");
        ((Project) project1).addActivity(task4);

        clock.run();

        ((Task) task3).start();
        ((Task) task4).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task3).stop();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task4).stop();

        Activity project3 = new Project((Project) project1, "P3");
        ((Project) project1).addActivity(project3);
        Activity task5 = new Task((Project) project3, "T5");
        Activity task6 = new Task((Project) project3, "T6");
        ((Project) project3).addActivity(task5);
        ((Project) project3).addActivity(task6);
        ((Task) task5).start();
        ((Task) task6).start();
    }
}
