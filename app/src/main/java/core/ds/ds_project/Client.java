package core.ds.ds_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    public static void main(final String[] args) {
        testA1();
//        testA2();
//        decoratorTest();
    }

    static void testA1() {
        Printer savedDataPrinter;

        try {
            File file = new File("tmp/serializedTree.ser");
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

        Clock clock = Clock.getInstance();

        final Activity project1 = new Project(null, "P1");
        Printer printer = new Printer((Project) project1);
        clock.addPropertyChangeListener(printer);


        Activity task3 = new TaskImpl((Project) project1, "T3");
        ((Project) project1).addActivity(task3);
        Activity project2 = new Project( (Project) project1, "P2");
        Activity task2 = new TaskImpl((Project) project2, "T2");
        Activity task1 = new TaskImpl((Project) project2, "T1");
        ((Project) project2).addActivity(task1);
        ((Project) project2).addActivity(task2);
        ((Project) project1).addActivity(project2);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    File file = new File("tmp/serializedTree.ser");
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

        ((TaskImpl) task3).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task3).stop();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task2).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task2).stop();
        ((TaskImpl) task3).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task3).stop();
    }

    static void testA2() {
        Printer savedDataPrinter;

        try {
            File file = new File("tmp/serializedTree.ser");
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

        Clock clock = Clock.getInstance();

        final Activity project1 = new Project(null, "P1");
        Printer printer = new Printer((Project) project1);
        clock.addPropertyChangeListener(printer);


        Activity task3 = new TaskImpl((Project) project1, "T3");
        ((Project) project1).addActivity(task3);
        Activity project2 = new Project( (Project) project1, "P2");
        Activity task2 = new TaskImpl((Project) project2, "T2");
        Activity task1 = new TaskImpl((Project) project2, "T1");
        ((Project) project2).addActivity(task1);
        ((Project) project2).addActivity(task2);
        ((Project) project1).addActivity(project2);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    File file = new File("tmp/serializedTree.ser");
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
        ((TaskImpl) task3).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task2).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task3).stop();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task1).start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task1).stop();
        try {
            Thread.sleep(1999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task2).stop();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task3).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((TaskImpl) task3).stop();
    }

    static void decoratorTest() {
        Clock clock = Clock.getInstance();

        final Activity project1 = new Project(null, "P1");
        Printer printer = new Printer((Project) project1);
        clock.addPropertyChangeListener(printer);


        Activity task3 = new TaskImpl((Project) project1, "T3");
        Activity posttask3 = new LimitedTask((Task) task3, Clock.getInstance().getCurrentTime() + Time.setSeconds(12));
        Activity pretask3 = new PreprogrammedTask((Task) posttask3, Clock.getInstance().getCurrentTime() + Time.setSeconds(5));
//        ((Project) project1).addActivity(task3);
        ((Project) project1).addActivity(pretask3);

        clock.run();
//        ((TaskImpl) task3).start();
//        ((LimitedTask) posttask3).start();
    }
}
