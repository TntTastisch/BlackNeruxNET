package de.TntTastisch.BlackNerux.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){

        if(event.getEntity() instanceof Player){
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }

        if(event.getEntity() instanceof Snowman){
            event.setCancelled(true);
        }

        if(event.getEntity() instanceof Skeleton){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event){event.setCancelled(true);}
}
