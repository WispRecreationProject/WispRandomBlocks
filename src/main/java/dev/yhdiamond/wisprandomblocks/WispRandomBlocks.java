package dev.yhdiamond.wisprandomblocks;

import dev.yhdiamond.wisprandomblocks.bstats.Metrics;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class WispRandomBlocks extends JavaPlugin {

    boolean gamestarted = false;
    Map<UUID, Location> storedloc = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WalkListen(this), this);
        getCommand("wisprandomblocks").setExecutor(new StartCommand(this));
        getCommand("wisprandomblocks").setTabCompleter(new CommandComplete());
        new Metrics(this, 10697);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
