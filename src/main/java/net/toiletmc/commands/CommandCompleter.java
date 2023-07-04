package net.toiletmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        final String[] COMMANDS = {"debug", "reload"};

        List<String> list = new ArrayList<>();

        StringUtil.copyPartialMatches(args[0], List.of(COMMANDS), list);

        Collections.sort(list);

        return list;
    }
}
