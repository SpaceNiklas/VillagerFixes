package com.spaceniklas.villagerfixes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Villagerfixes extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("[VillagerFixes] Plugin is up!");

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);

    }
}
