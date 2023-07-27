package com.bshaven.manifestchat.commands;

import com.bshaven.manifestchat.ManifestChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetPrefix implements CommandExecutor {

    private final FileConfiguration config;

    public SetPrefix(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /setprefix <rank> <prefix>");
            return true;
        }

        String rank = args[0];
        String prefix = ChatColor.translateAlternateColorCodes('&', args[1]);

        config.set("prefixes." + rank, prefix);
        sender.sendMessage(ChatColor.GREEN + "Prefix for rank '" + rank + "' set to: " + prefix);
        return true;
    }

}
