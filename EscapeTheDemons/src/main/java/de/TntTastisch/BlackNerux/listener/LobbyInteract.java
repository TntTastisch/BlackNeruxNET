package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class LobbyInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4Einstellungen")){
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §c§lEinstellungen");

                for(int i = 0; i != 27; i++){
                    inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname("").create());
                }
                inventory.setItem(0, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(8, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(9, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(17, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(18, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(26, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());

                inventory.setItem(11, new ItemAPI(Material.WOOL,14).setDisplayname("§8➦ §4Demonen-Pass").create());
                inventory.setItem(13, new ItemAPI(Material.DIAMOND).setDisplayname("§8➦ §aStart").create());
                inventory.setItem(15, new ItemAPI(Material.WOOL,11).setDisplayname("§8➦ §9Polizei-Pass").create());


                player.openInventory(inventory);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cVerlassen")){
                player.kickPlayer(" ");
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §c§lEinstellungen")){
            event.setCancelled(true);

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §aStart")){
                if(player.hasPermission("escapethedemons.vip.start")) {
                    if (JoinQuitListener.timer != 10) {
                        JoinQuitListener.timer = 10;
                        player.sendMessage(Data.prefix + "§7Du hast das Spiel §aerfolgreich §7gestartet!");
                        event.getView().close();
                    } else if (JoinQuitListener.timer <= 10) {
                        player.sendMessage(Data.prefix + "§cDas Spiel startet bereits!");
                        event.getView().close();
                    }
                } else {
                    player.sendMessage(Data.noPerms);
                    event.getView().close();
                }

            }
        }
    }
}
