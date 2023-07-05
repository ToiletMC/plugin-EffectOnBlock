package net.toiletmc.effectonblock.tasks;


import net.toiletmc.effectonblock.EffectOnBlock;


public class TaskManager {

    public TaskManager(EffectOnBlock plugin) {
        new NormalTask().runTaskTimer(plugin, 20L, 2L);
    }

}