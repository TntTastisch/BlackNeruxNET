package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class SpectatorCompassListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §7Spieler")){
            Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §7Wähle einen Spieler");

            for(Player all : Bukkit.getOnlinePlayers()){
                if(Data.ingame.contains(all)){
                    inventory.addItem(ItemAPI.SkullBuilder("§8➦ §7" + all.getName(), all.getName()));
                }
            }

            player.openInventory(inventory);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §7Wähle einen Spieler")){
            event.setCancelled(true);

            for(Player all : Bukkit.getOnlinePlayers()){
                if(Data.ingame.contains(all)){
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §7" + all.getName())){
                        player.teleport(all);
                        player.sendMessage(Data.prefix + "§7Du hast dich zu §6" + Data.getPlayerPrefix(all) + " §ateleportiert§7.");
                        event.getView().close();
                    }
                }
            }

        }

    }
}
