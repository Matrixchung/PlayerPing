package com.matrix.PlayerPing;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Supplier;
import java.util.logging.Level;


public class Command implements CommandExecutor{
    @Override
    @Deprecated
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(command.getName().equalsIgnoreCase("ping"))) {return true;}
        if (args.length==0){
            if(!(sender instanceof Player)) {sender.sendMessage("[PlayerPing] 你必须指定玩家名来查看他的Ping.");return true;}
            Player player = (Player) sender;
            try{
                String playerPing = Integer.toString(new ActualPing().getPlayerPing(player));
                if(Integer.parseInt(playerPing)==-1) {player.sendMessage("§c[Ping] 查看延迟时发生错误,玩家可能不在线!");}
                else player.sendMessage("§e您当前的延迟: §2"+playerPing+" ms");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return true;
        }
        if (args.length==1){
            if((sender instanceof Player) && !(sender.hasPermission("playerping.checkothers"))) {sender.sendMessage("§c[Ping] 你没有权限获取他人的Ping值!");}
            else{
                Player player = (Bukkit.getServer().getPlayer(args[0]));
                String playerPing = Integer.toString(new ActualPing().getPlayerPing(player));
                if( player != null ){
                    sender.sendMessage("§2玩家 "+player.getDisplayName()+" §2的延迟: §e"+playerPing+" ms");
                }
                else{
                    sender.sendMessage("§c[Ping] 你所查询的玩家不在线!");
                }
                return true;
            }
        }
        if (args.length>1){
            sender.sendMessage("§c[Ping] 参数过多!");
            return true;
        }
        return true;
    }
}
