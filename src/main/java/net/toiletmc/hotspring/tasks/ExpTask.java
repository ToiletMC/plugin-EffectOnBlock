package net.toiletmc.hotspring.tasks;

import net.toiletmc.hotspring.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.TimerTask;

public class ExpTask extends TimerTask {
    static double x;
    static double y;
    static double z;

    public void run() {
        int playercount = 0;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.isInWater()) {
                x = p.getLocation().getX();
                y = p.getLocation().getY();
                z = p.getLocation().getZ();
                if (x >= Config.x1 && x <= Config.x2 && y >= Config.y1 && y <= Config.y2 && z >= Config.z1 && z <= Config.z2) {
                    p.giveExp(Config.exp_points);
                    playercount++;
                }
            }
        }
        Config.playercount = playercount;
    }
}
