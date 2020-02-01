package de.TeeJan.BlackNerux.listeners;

import de.TeeJan.BlackNerux.systems.MySQL;
import de.TeeJan.BlackNerux.utils.PlayerInvetory;
import de.TeeJan.BlackNerux.utils.Scoreboard;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

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
