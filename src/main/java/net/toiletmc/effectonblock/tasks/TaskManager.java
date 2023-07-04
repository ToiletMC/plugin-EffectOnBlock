package net.toiletmc.effectonblock.tasks;


import net.toiletmc.effectonblock.EffectOnBlock;
import org.bukkit.scheduler.BukkitTask;


public class TaskManager {

    public TaskManager(EffectOnBlock plugin) {
        plugin = plugin;
        new NormalTask().runTaskTimer(plugin, 20L, 2L);
    }

}