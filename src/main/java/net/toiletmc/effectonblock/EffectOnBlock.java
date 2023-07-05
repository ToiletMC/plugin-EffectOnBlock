package net.toiletmc.effectonblock;

import net.toiletmc.effectonblock.commands.Command;
import net.toiletmc.effectonblock.commands.CommandCompleter;
import net.toiletmc.effectonblock.config.Config;
import net.toiletmc.effectonblock.tasks.TaskManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class EffectOnBlock extends JavaPlugin {
    public static List<Area> areas;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadPluginConfig();
        getCommand("effectonblock").setExecutor(new Command(this));
        getCommand("effectonblock").setTabCompleter(new CommandCompleter());
        new TaskManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reloadPluginConfig() {
        this.reloadConfig();
        FileConfiguration config = getConfig();

        Config.unknownCommand = config.getString("unknown_command");
        Config.successfulReload = config.getString("successful_reload");

        Config.negativeEffects.clear();
        for (String effect : config.getStringList("negative_effects")) {
            Config.negativeEffects.add(PotionEffectType.getByName(effect));
        }

        ConfigurationSection areasConfig = config.getConfigurationSection("areas");
        areas = new ArrayList<>();
        if (areasConfig != null) {
            for (String key : areasConfig.getKeys(false)) {
                double x1 = config.getDouble("areas." + key + ".location.x1");
                double y1 = config.getDouble("areas." + key + ".location.y1");
                double z1 = config.getDouble("areas." + key + ".location.z1");
                double x2 = config.getDouble("areas." + key + ".location.x2");
                double y2 = config.getDouble("areas." + key + ".location.y2");
                double z2 = config.getDouble("areas." + key + ".location.z2");
                int exp = config.getInt("areas." + key + ".exp_points");
                boolean isInWater = config.getBoolean("areas." + key + ".is_in_water");
                boolean isRemoveAllNegativePoints = config.getBoolean("areas." + key + ".remove_all_negative_points");

                Set<PotionEffectType> givenEffects = config.getStringList("areas." + key + ".effects").stream()
                        .map(PotionEffectType::getByName)
                        .collect(Collectors.toUnmodifiableSet());

                areas.add(new Area(x1, y1, z1, x2, y2, z2, givenEffects, exp, isInWater, isRemoveAllNegativePoints));
            }
        }

    }
}
