package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import javax.swing.*;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(GameState.getGameState() == GameState.LOBBY || GameState.getGameState() == GameState.END){
                event.setCancelled(true);
            } else if(GameState.getGameState() == GameState.INGAME){
                if(Data.spectator.contains(event.getEntity())){
                    event.setCancelled(true);
                } else {
                    if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(false);
                    }
                }
            }

        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event){

        if(event.getEntity() instanceof Player){
            event.setCancelled(true);
        }

    }
}
