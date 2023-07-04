package net.toiletmc.hotspring.tasks;


import net.toiletmc.hotspring.HotSpring;
import org.bukkit.scheduler.BukkitTask;


public class TaskManager {
    private BukkitTask task;
    private final HotSpring plugin;

    public TaskManager(HotSpring plugin) {
        this.plugin = plugin;
        task = new InWaterTask().runTaskTimer(plugin, 20L, 2L);
    }

    public void resetTask() {
        task.cancel();
        task = new InWaterTask().runTaskTimer(plugin, 20L, 2L);
    }
}