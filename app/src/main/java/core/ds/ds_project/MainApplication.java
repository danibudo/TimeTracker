package core.ds.ds_project;

public class MainApplication {
    public static void main(String[] args) {

        Activity task1 = new Task("Attending theory class");
        //Interval interval1 = new Interval(0,60*60*2);
        //task1.addInterval(interval1);


        Activity task2 = new Task("Study");
        //Interval interval2 = new Interval(0,10);
        Activity proj1 = new Project("DS");

        Activity task3 = new Task("Implementation of composite pattern");
        Activity task4 = new Task("Clock class");
        Activity proj2 = new Project("DS Project");

        Activity task5 = new Task("Do homework");
        Activity proj3 = new Project("DS Problems");


        proj1.addActivity(task1);
        proj1.addActivity(task2);

        proj2.addActivity(task3);
        proj2.addActivity(task4);

        proj3.addActivity(task5);

        proj1.addActivity(proj2);
        proj1.addActivity(proj3);

        proj1.print();

    }
}
