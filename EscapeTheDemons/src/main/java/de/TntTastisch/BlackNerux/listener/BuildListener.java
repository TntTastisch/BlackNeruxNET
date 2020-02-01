package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        event.setCancelled(true);
    }
}
