package net.toiletmc.effectonblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandCompleter implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final String[] COMMANDS = {"debug", "reload"};

        List<String> list = new ArrayList<>();

        StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), list);

        Collections.sort(list);

        return list;
    }
}
