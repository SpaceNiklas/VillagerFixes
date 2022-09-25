package com.spaceniklas.villagerfixes;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Villagerfixes extends JavaPlugin {

    public static Villagerfixes instance;
    public static FileConfiguration config;

    @Override
    public void onEnable() {

        instance = this;

        Bukkit.getLogger().info("[VillagerFixes] Plugin is up!");
        getCommand("invincibility").setExecutor(new InvincibilityCommand());

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        saveDefaultConfig();
        config = getConfig();
    }
}
