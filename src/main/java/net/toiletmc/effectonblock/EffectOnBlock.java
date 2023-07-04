package net.toiletmc.effectonblock;

import net.toiletmc.commands.CommandCompleter;
import net.toiletmc.commands.Command;
import net.toiletmc.effectonblock.config.Config;
import net.toiletmc.effectonblock.tasks.TaskManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class EffectOnBlock extends JavaPlugin {
    private TaskManager taskManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.taskManager = new TaskManager(this);
        reloadPluginConfig();
        getCommand("hotspring").setExecutor(new Command(this));
        getCommand("hotspring").setTabCompleter(new CommandCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reloadPluginConfig() {
        this.reloadConfig();
        FileConfiguration config = getConfig();
        Config.x1 = config.getDouble("location.x1");
        Config.y1 = config.getDouble("location.y1");
        Config.z1 = config.getDouble("location.z1");
        Config.x2 = config.getDouble("location.x2");
        Config.y2 = config.getDouble("location.y2");
        Config.z2 = config.getDouble("location.z2");
        Config.exp_points = config.getInt("exp_points");
        Config.init();
    }
}