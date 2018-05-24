package me.albus.receivefoodplugin.receivefood;

import me.albus.receivefoodplugin.receivefood.commands.CommandFly;
import me.albus.receivefoodplugin.receivefood.commands.CommandInfo;
import me.albus.receivefoodplugin.receivefood.events.pressurePlateListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.broadcastMessage("Hello");
        getCommand("info").setExecutor(new CommandInfo());
        getCommand("fly").setExecutor(new CommandFly());
        getServer().getPluginManager().registerEvents(new pressurePlateListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("Server is shutting down");
    }


}

