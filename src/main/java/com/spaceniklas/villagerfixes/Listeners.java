package com.spaceniklas.villagerfixes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class Listeners implements Listener {

    public static HashMap<Player, Boolean> countdown = new HashMap<>();

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if(e.getEntity() instanceof Pillager && e.getEntity().getKiller() != null){

            Pillager pillager = (Pillager) e.getEntity();
            if(pillager.isPatrolLeader()){
                if(e.getEntity().getKiller().hasPotionEffect(PotionEffectType.BAD_OMEN)){
                    e.getEntity().getKiller().removePotionEffect(PotionEffectType.BAD_OMEN);
                }
            }
        }
    }

    @EventHandler
    public void onVillagerJobEvent(VillagerCareerChangeEvent e){
        if(e.getReason().equals(VillagerCareerChangeEvent.ChangeReason.EMPLOYED)){
            Villager villager = e.getEntity();
            villager.setVillagerExperience(1);
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!e.getPlayer().hasPlayedBefore()){
            countdown.put(p, true);
            Bukkit.getScheduler().runTaskLater(Villagerfixes.instance, () -> {countdown.put(p, false);},  Villagerfixes.config.getInt("cooldown") *60*20);
        }
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
        if(Villagerfixes.config.getBoolean("make-invincible-players-damage-vincible-entities") && countdown.get(e.getDamager()) != null && countdown.get(e.getDamager()) && countdown.get(e.getEntity()) == null || Villagerfixes.config.getBoolean("make-invincible-players-damage-vincible-entities") && countdown.get(e.getDamager()) != null && countdown.get(e.getDamager()) && !countdown.get(e.getEntity())) {
            if(!e.isCancelled()){
                e.setCancelled(false);
            }

        }else
        if(countdown.get(e.getDamager()) != null && countdown.get(e.getDamager())){
            e.setCancelled(true);
            e.getDamager().sendMessage(ChatColor.translateAlternateColorCodes('&', Villagerfixes.config.getString("still-invincible-damager")));
        }
        if(countdown.get(e.getEntity()) != null && countdown.get(e.getEntity())){
            e.getDamager().sendMessage(ChatColor.translateAlternateColorCodes('&', Villagerfixes.config.getString("still-invincible-victim")));
            e.setCancelled(true);
        }
    }
}