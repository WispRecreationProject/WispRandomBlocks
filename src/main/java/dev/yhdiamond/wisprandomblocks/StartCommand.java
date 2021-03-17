package dev.yhdiamond.wisprandomblocks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    WispRandomBlocks plugin;

    public StartCommand(WispRandomBlocks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("wisprandomblocks.toggle")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        plugin.gamestarted = true;
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Wisp Random Blocks challenge has started!");
                        for (Player others : Bukkit.getOnlinePlayers()){
                            plugin.storedloc.put(others.getUniqueId(), others.getWorld().getBlockAt(others.getLocation()).getLocation());
                        }
                    }
                    else if (args[0].equals("stop")){
                        plugin.gamestarted = false;
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Wisp Random Blocks challenge has ended!");
                        for (Player others : Bukkit.getOnlinePlayers()){
                            plugin.storedloc.remove(others);
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.RED+"/wisprandomblocks <start/stop>");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED+"/wisprandomblocks <start/stop>");
                }
            }
            else {
                p.sendMessage(ChatColor.RED+"You do not have the required permission to run this command!");
            }
        }
        else{
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }

        return true;
    }
}