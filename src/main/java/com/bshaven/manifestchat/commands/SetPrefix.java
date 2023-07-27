package com.bshaven.manifestchat.commands;

import com.bshaven.manifestchat.ManifestChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetPrefix implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /setprefix <rank> <prefix>");
            return true;
        }

        String rank = args[0];
        String prefix = ChatColor.translateAlternateColorCodes('&', args[1]);

        // Save the prefix to the configuration
        ManifestChat plugin = ManifestChat.getPlugin(ManifestChat.class);
        plugin.getConfig().set("prefixes." + rank, prefix);
        plugin.saveConfig();

        sender.sendMessage(ChatColor.GREEN + "Prefix for rank '" + rank + "' set to: " + prefix);
        return true;
    }

}
