package de.TntTastisch.BlackNerux.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ColouredAnvilListener implements Listener {

    @EventHandler
    public void colouredAnvil(final InventoryClickEvent event){
        try {
            if (event.getWhoClicked().hasPermission("citybuild.feature.colouredAnvil")) {
                final Inventory inv = event.getInventory();
                if (inv instanceof AnvilInventory) {
                    final InventoryView view = event.getView();
                    final int rawSlot = event.getRawSlot();
                    if (rawSlot == view.convertSlot(rawSlot) && rawSlot == 2) {
                        final ItemStack item = event.getCurrentItem();
                        if (item != null) {
                            final ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(meta.getDisplayName().replace("&", "§"));
                            item.setItemMeta(meta);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
}
