package com.spaceniklas.villagerfixes;

import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.potion.PotionEffectType;

public class Listeners implements Listener {

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


}
