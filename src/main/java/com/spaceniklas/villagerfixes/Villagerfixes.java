package com.spaceniklas.villagerfixes;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Villagerfixes extends JavaPlugin {

    public static Villagerfixes instance;
    public static FileConfiguration config;

    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();
        config = getConfig();

        Bukkit.getLogger().info("[VillagerFixes] Plugin is up!");
        String cmd = config.getString("command-name");
        Bukkit.getLogger().info(cmd);
        new InvincibilityCommand();
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
    }
}
