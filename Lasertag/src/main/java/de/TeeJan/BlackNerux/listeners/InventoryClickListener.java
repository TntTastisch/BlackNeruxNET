package de.TeeJan.BlackNerux.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryMove(InventoryClickEvent event){
        event.setCancelled(true);

        if(event.getInventory().getName().equalsIgnoreCase("§cEisntellungen")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Einstellungen")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Inventory inventory = Bukkit.createInventory(null, 9, "§cEisntellungen");

                for (int i = 0; i != inventory.getSize(); i++) {
                    inventory.setItem(i, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
                    inventory.setItem(3, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.TORCH, 15).setDisplayname("§4Spiel Starten").create());
                    inventory.setItem(5, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.PUMPKIN, 15).setDisplayname("§4Comming soon").create());
                }

                player.openInventory(inventory);
            }
        }
    }
}