package com.bshaven.manifestchat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.ChatColor;

public class ChatListener implements Listener {
    private final FileConfiguration config;

    public ChatListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String rank = ""; // Default rank prefix if not found

        // Check if the player has a custom prefix set
        if (config.contains("prefixes." + player.getName())) {
            rank = config.getString("prefixes." + player.getName(), "");
        } else {
            // Check if the player has a default rank (permission-based prefix)
            // Example: player has "manifestchat.admin" permission, and rank is "Admin"
            for (String rankKey : config.getConfigurationSection("prefixes").getKeys(false)) {
                if (player.hasPermission("manifestchat." + rankKey.toLowerCase())) {
                    rank = config.getString("prefixes." + rankKey, "");
                    break;
                }
            }
        }

        String message = rank + event.getMessage();
        event.setMessage(message);
    }
}
