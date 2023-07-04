package net.toiletmc.hotspring.tasks;


import java.util.Timer;

public class TaskManager {
    public TaskManager() {
        Timer t = new Timer();
        ExpTask task = new ExpTask();
        t.schedule(task, 100L, 100L);
    }
}