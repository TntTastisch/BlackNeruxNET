package de.TeeJan.BlackNerux.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class SettingsListener implements Listener {

    @EventHandler
    public void onGadget(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5Settings")) {
            Inventory inventory = Bukkit.createInventory(null, 4, "§7➟ §e§lSettings");

            for (int i = 0; i != inventory.getSize(); i++) {
                inventory.setItem(i, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
            }

            inventory.setItem(0, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
            inventory.setItem(8, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
            inventory.setItem(9, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
            inventory.setItem(17, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
            inventory.setItem(18, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
            inventory.setItem(26, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());

            inventory.setItem(11, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.LEATHER_BOOTS).setColor(Color.GREEN).setDisplayname("§8➦ §bSpeed Gadget").create());
            inventory.setItem(13, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.BARRIER).setDisplayname("§8➦ §4Gadget entfernen").create());
            inventory.setItem(15, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.DIAMOND).setDisplayname("§8➦ §aJumpBoost Gadget").create());

            player.openInventory(inventory);
        }

    }

}