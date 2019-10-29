package core.ds.ds_project;

import android.widget.ActionMenuView;

import java.util.concurrent.TimeUnit;

public class MainApplication {
    public static void main(String[] args) {

//        Clock clock = Clock.getInstance();
//
//        Activity project1 = new Project(null, "P1");
//        Printer printer = new Printer((Project) project1);
//        clock.addPropertyChangeListener(printer);
//
//
//        Activity task3 = new Task((Project) project1, "T3");
//        ((Project) project1).addActivity(task3);
//        Activity project2 = new Project( (Project) project1, "P2");
//        Activity task2 = new Task((Project) project2, "T2");
//        Activity task1 = new Task((Project) project2, "T1");
//        ((Project) project2).addActivity(task1);
//        ((Project) project2).addActivity(task2);
//        ((Project) project1).addActivity(project2);
//
//        clock.run();
//
//        ((Task) task3).start();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ((Task) task3).stop();
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ((Task) task2).start();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ((Task) task2).stop();
//        ((Task) task3).start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ((Task) task3).stop();

        Clock clock = Clock.getInstance();

        Activity project1 = new Project(null, "P1");
        Printer printer = new Printer((Project) project1);
        clock.addPropertyChangeListener(printer);


        Activity task3 = new Task((Project) project1, "T3");
        ((Project) project1).addActivity(task3);
        Activity project2 = new Project( (Project) project1, "P2");
        Activity task2 = new Task((Project) project2, "T2");
        Activity task1 = new Task((Project) project2, "T1");
        ((Project) project2).addActivity(task1);
        ((Project) project2).addActivity(task2);
        ((Project) project1).addActivity(project2);

        clock.run();
        ((Task) task3).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task2).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task3).stop();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task1).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task1).stop();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task2).stop();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task3).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((Task) task3).stop();
    }
}
