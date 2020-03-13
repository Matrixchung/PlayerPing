package com.matrix.PlayerPing;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{

    @Override
    public void onLoad(){
        System.out.println("PlayerPing Loading...");
    }
    @Override
    public void onEnable(){
        System.out.println("PlayerPing by Matrixchung Loaded and Enabled.");
        Bukkit.getPluginCommand("ping").setExecutor(new Command());
    }
    @Override
    public void onDisable(){
        System.out.println("Disabling PlayerPing...");
    }
}
