package de.TntTastisch.BlackNerux.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {


    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        event.setDeathMessage(null);
        event.setKeepLevel(true);
        event.setKeepInventory(true);
    }
}
