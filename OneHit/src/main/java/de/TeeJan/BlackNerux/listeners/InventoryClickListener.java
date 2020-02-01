package de.TeeJan.BlackNerux.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryMove(InventoryClickEvent event){
        event.setCancelled(true);
    }
}
