package net.toiletmc.effectonblock.commands;

import net.toiletmc.effectonblock.EffectOnBlock;
import net.toiletmc.effectonblock.config.Config;
import net.toiletmc.effectonblock.config.Debug;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {
    private final EffectOnBlock plugin;

    public Command(EffectOnBlock plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (!sender.hasPermission("effectonblock.command.admin")) {
            sender.sendMessage(Config.unknownCommand);
            return true;
        }

        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    plugin.reloadPluginConfig();
                    sender.sendMessage(Config.successfulReload);
                    sender.sendMessage("Total Areas: " + EffectOnBlock.areas.size());
                }
                case "debug" -> {
                    sender.sendMessage("Total Players: " + Debug.playercount);
                    sender.sendMessage("Total Areas: " + EffectOnBlock.areas.size());
                }
                default -> sender.sendMessage(Config.unknownCommand);
            }
        } else {
            sender.sendMessage(Config.unknownCommand);
        }

        return true;
    }
}
