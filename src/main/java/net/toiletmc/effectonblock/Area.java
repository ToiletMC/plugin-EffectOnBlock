package net.toiletmc.effectonblock;

import org.bukkit.Location;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

public class Area {
    private double x1, y1, z1, x2, y2, z2;
    private final Set<PotionEffectType> potionEffectTypes;
    private final int exp;
    private final boolean isInWater;
    private final boolean isRemoveAllNegativePoints;

    public Set<PotionEffectType> getPotionEffectTypes() {
        return potionEffectTypes;
    }

    public int getExp() {
        return exp;
    }

    public boolean isInWater() {
        return isInWater;
    }

    public boolean isRemoveAllNegativePoints() {
        return isRemoveAllNegativePoints;
    }

    public Area(double x1, double y1, double z1, double x2, double y2, double z2, Set<PotionEffectType> potionEffectTypes, int exp, boolean isInWater, boolean isRemoveAllNegativePoints) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.potionEffectTypes = potionEffectTypes;
        this.exp = exp;
        this.isInWater = isInWater;
        this.isRemoveAllNegativePoints = isRemoveAllNegativePoints;
        this.init();
    }

    private void init() {
        if (x1 > x2) {
            double x3 = x1;
            x1 = x2;
            x2 = x3;
        }
        if (y1 > y2) {
            double y3 = y1;
            y1 = y2;
            y2 = y3;
        }
        if (z1 > z2) {
            double z3 = z1;
            z1 = z2;
            z2 = z3;
        }
    }

    public boolean isInArea(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        return x >= x1 &&
                x <= x2 &&
                y >= y1 &&
                y <= y2 &&
                z >= z1 &&
                z <= z2;
    }
}