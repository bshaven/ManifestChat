package com.bshaven.manifestchat;

import com.bshaven.manifestchat.commands.SetPrefix;
import org.bukkit.plugin.java.JavaPlugin;

public class ManifestChat extends JavaPlugin {

    // Static method to get the plugin instance
    public static ManifestChat getPlugin() {
        return getPlugin(ManifestChat.class);
    }

    @Override
    public void onEnable() {
        // Register the /setprefix command with its executor
        getCommand("setprefix").setExecutor(new SetPrefix(this, getConfig()));

        // Register the ChatListener to listen for player chat events
        getServer().getPluginManager().registerEvents(new ChatListener(getConfig()), this);

        // Load configuration and other initialization tasks
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
