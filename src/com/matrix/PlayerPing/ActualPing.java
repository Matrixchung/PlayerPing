package com.matrix.PlayerPing;

import org.bukkit.entity.Player;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.bukkit.Bukkit.getServer;

public class ActualPing {
    public int getPlayerPing(Player player) {
        String version = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if(player != null){
            try{
                Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
                Object converted = craftPlayer.cast(player);
                Method handle = converted.getClass().getMethod("getHandle", new Class[0]);
                Object entityPlayer = handle.invoke(converted, new Object[0]);
                Field pingField = entityPlayer.getClass().getField("ping");
                return pingField.getInt(entityPlayer);
            }
            catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }
}
