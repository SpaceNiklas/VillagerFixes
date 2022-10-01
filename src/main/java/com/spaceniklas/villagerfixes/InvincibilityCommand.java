package com.spaceniklas.villagerfixes;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class InvincibilityCommand extends Command {
    public InvincibilityCommand(){
        super(Villagerfixes.config.getString("command-name"), new String[]{}, "", "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(Listeners.countdown.get(p) != null && Listeners.countdown.get(p)) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Villagerfixes.config.getString("command-message")));
                Listeners.countdown.put(p, false);
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Villagerfixes.config.getString("already-vincible")));
            }
        }
    }
}
