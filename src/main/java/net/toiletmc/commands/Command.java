package net.toiletmc.commands;

import net.toiletmc.effectonblock.EffectOnBlock;
import net.toiletmc.effectonblock.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {
    private final EffectOnBlock plugin;

    public Command(EffectOnBlock plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (!sender.hasPermission("hotspring.command.admin")) {
            sender.sendMessage(ChatColor.RED + "未知的指令");
            return true;
        }

        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    plugin.reloadPluginConfig();
                    sender.sendMessage("HotSpring 重载成功！");
                }
                case "debug" -> {
                    sender.sendMessage("x1: " + Config.x1 + ",y1: " + Config.y1 + ",z1: " + Config.x1);
                    sender.sendMessage("x2: " + Config.x2 + ",y2: " + Config.y2 + ",z2: " + Config.x2);
                    sender.sendMessage("当前温泉玩家数量：" + Config.playercount);
                    sender.sendMessage("单次给予经验点数：" + Config.exp_points);
                }
                default -> sender.sendMessage(ChatColor.RED + "未知的指令");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "未知的指令");
        }

        return true;
    }
}
