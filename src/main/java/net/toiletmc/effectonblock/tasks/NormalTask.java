package net.toiletmc.effectonblock.tasks;

import net.toiletmc.effectonblock.Area;
import net.toiletmc.effectonblock.EffectOnBlock;
import net.toiletmc.effectonblock.utils.PointsEffectsUtils;
import net.toiletmc.effectonblock.config.Config;
import net.toiletmc.effectonblock.config.Debug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NormalTask extends BukkitRunnable {
    public void run() {
        int playercount = 0;
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            for (Area area : EffectOnBlock.areas) {
                if (area.isInArea(player.getLocation())) {
                    if (area.isInWater() && !player.isInWater()) {
                        continue;
                    }

                    player.giveExp(area.getExp());
                    PointsEffectsUtils.givePotionEffects(player, area.getPotionEffectTypes());
                    if (area.isRemoveAllNegativePoints())
                        PointsEffectsUtils.removeAllNegativePoints(player, Config.negativeEffects);
                    playercount++;
                    break;
                }
            }
        }
        Debug.playercount = playercount;
    }
}