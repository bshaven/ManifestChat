package com.bshaven.manifestchat.commands;

import com.bshaven.manifestchat.ManifestChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class SetPrefix implements CommandExecutor {
    private final FileConfiguration config;
    private final ManifestChat plugin;

    public SetPrefix(ManifestChat plugin, FileConfiguration config) {
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("manifestchat.setprefix")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /setprefix <username> <group>");
            return true;
        }

        String username = args[0];
        String group = args[1];

        // Check if the group is valid and exists in the configuration
        if (!config.contains("prefixes." + group)) {
            sender.sendMessage(ChatColor.RED + "Invalid group. Please use an existing group as the prefix.");
            return true;
        }

        String prefix = config.getString("prefixes." + group);

        // Save the custom prefix to the configuration
        config.set("prefixes." + username, prefix);
        sender.sendMessage(ChatColor.GREEN + "Prefix for player '" + username + "' set to: " + prefix);

        // Save the configuration
        saveConfig();

        return true;
    }

    // Method to save the configuration
    private void saveConfig() {
        plugin.saveConfig();
    }
}
