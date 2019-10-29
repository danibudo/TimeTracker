package core.ds.ds_project;

import android.widget.ActionMenuView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class MainApplication {
    public static void main(String[] args) {

        Printer savedDataPrinter;

        try {
            File file = new File("serializedTree.ser");
            if (file.exists()) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Activity p = (Project) in.readObject();
                savedDataPrinter = new Printer((Project) p);
                p.acceptVisitor(savedDataPrinter);
                in.close();
                fileIn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        Clock clock = Clock.getInstance();
//
//        final Activity project1 = new Project(null, "P1");
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
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//                try {
//                    File file = new File("serializedTree.ser");
//                    boolean deleted = true;
//                    boolean created = true;
//                    if (file.exists()) {
//                        deleted = file.delete();
//                        created = file.createNewFile();
//                    }
//                    if (deleted && created) {
//                        System.out.println("\n\nSerializing...");
//                        FileOutputStream fileOut = new FileOutputStream(file);
//                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//                        out.writeObject(project1);
//                        out.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
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

        final Activity project1 = new Project(null, "P1");
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

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    File file = new File("serializedTree.ser");
                    boolean deleted = true;
                    boolean created = true;
                    if (file.exists()) {
                        deleted = file.delete();
                        created = file.createNewFile();
                    }
                    if (deleted && created) {
                        System.out.println("\n\nSerializing...");
                        FileOutputStream fileOut = new FileOutputStream(file);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(project1);
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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
            Thread.sleep(1999);
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
