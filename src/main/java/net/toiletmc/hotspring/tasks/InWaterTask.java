package net.toiletmc.hotspring.tasks;

import net.toiletmc.hotspring.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InWaterTask extends BukkitRunnable {
    private final Set<PotionEffectType> negativeEffects = Set.of(
            PotionEffectType.BLINDNESS,
            PotionEffectType.CONFUSION,
            PotionEffectType.DARKNESS,
            PotionEffectType.HUNGER,
            PotionEffectType.POISON,
            PotionEffectType.SLOW,
            PotionEffectType.SLOW_DIGGING,
            PotionEffectType.UNLUCK,
            PotionEffectType.WEAKNESS,
            PotionEffectType.WITHER
    );

    public void run() {
        int playercount = 0;
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.isInWater() && this.isInArea(player.getLocation())) {
                player.giveExp(Config.exp_points);
                increaseHP(player);
                removeAllNegativePoints(player);
                playercount++;
            }
        }
        Config.playercount = playercount;
    }

    private void increaseHP(Player player) {
        Set<PotionEffectType> effectTypes = player.getActivePotionEffects()
                .stream()
                .map(PotionEffect::getType)
                .collect(Collectors.toUnmodifiableSet());
        if (!effectTypes.contains(PotionEffectType.REGENERATION)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, true));
        } else {
            Optional<PotionEffect> effectOptional = player.getActivePotionEffects()
                    .stream()
                    .filter(potionEffect -> potionEffect.getType().equals(PotionEffectType.REGENERATION))
                    .findAny();
            if (effectOptional.isPresent()) {
                PotionEffect effect = effectOptional.get();
                int remainingDuration = effect.getDuration();
                if (remainingDuration < 30) {
                    player.removePotionEffect(PotionEffectType.REGENERATION);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, true));
                }
            }
        }
    }

    private void removeAllNegativePoints(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (this.negativeEffects.contains(effect.getType())) {
                player.removePotionEffect(effect.getType());
            }
        }

    }

    private boolean isInArea(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        return x >= Config.x1 &&
                x <= Config.x2 &&
                y >= Config.y1 &&
                y <= Config.y2 &&
                z >= Config.z1 &&
                z <= Config.z2;
    }
}