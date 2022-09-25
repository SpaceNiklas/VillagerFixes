package com.spaceniklas.villagerfixes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvincibilityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(Listeners.countdown.get(p) != null && Listeners.countdown.get(p)) {
                p.sendMessage(ChatColor.GREEN + "You are now able to damage/get damaged by other entities!");
                Listeners.countdown.put(p, false);
            }else{
                p.sendMessage(ChatColor.RED + "You are already able to damage/get damaged by other entities! If you believe that this is a bug please contact a server administrator!");
            }
        }
        return false;
    }
}
