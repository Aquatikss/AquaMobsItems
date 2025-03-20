package com.aquamobs.aquaMobsItems;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class Main extends JavaPlugin {

    private static Main plugin;

    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getLogger().info("AquaMobs Items has started!");
        ConfigUtil.getInstance().load();

        getCommand("item").setExecutor(new ItemCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("AquaMobs Items has stopped!");
    }

    public static Main getPlugin() {
        return plugin;
    }
}