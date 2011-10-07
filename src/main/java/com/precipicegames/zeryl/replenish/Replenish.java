package com.precipicegames.zeryl.replenish;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class Replenish extends JavaPlugin {
    
    private final ReplenishBlockListener blockListener = new ReplenishBlockListener(this);
    
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        PluginDescriptionFile pdf = this.getDescription();
        pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Priority.Lowest, this);
        
        System.out.println(pdf.getName() + " is now enabled");
    }
    
    public void onDisable() {
        PluginDescriptionFile pdf = this.getDescription();
        System.out.println(pdf.getName() + " is now disbled");
    }
}
