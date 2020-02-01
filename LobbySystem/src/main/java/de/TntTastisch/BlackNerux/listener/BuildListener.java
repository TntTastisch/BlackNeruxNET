package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.systems.Data;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;

public class BuildListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if(Data.build.contains(player)){
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(Data.build.contains(player)){
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player player = event.getPlayer();

        if(Data.build.contains(player)){
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();

        if(Data.build.contains(player)){
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(Data.build.contains(player)){
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }
}
