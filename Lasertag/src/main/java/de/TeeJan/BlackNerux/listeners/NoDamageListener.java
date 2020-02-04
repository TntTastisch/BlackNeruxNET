package de.TeeJan.BlackNerux.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getCause().equals((Object)EntityDamageEvent.DamageCause.FALL)) {
            event.setCancelled(true);
        }
        if (event.getCause().equals((Object)EntityDamageEvent.DamageCause.FIRE)) {
            event.setCancelled(true);
        }
        if (event.getCause().equals((Object)EntityDamageEvent.DamageCause.VOID)) {
            final Player p = (Player)event.getEntity();
            p.setHealth(1.0);
        }
    }


    @EventHandler
    public void onFood(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onRagian(EntityRegainHealthEvent event){
        event.setCancelled(true);
    }
}
