package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FlyListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §c§lFly")){
            if(MySQL.getFlyTool(player.getUniqueId().toString()) == 0) {
                MySQL.setFlyTool(player.getUniqueId().toString(), 1);
                PlayerInventory.playerInventory(player);

                player.sendMessage(Data.prefix + "§c§lFlugmodus §aaktiviert§7.");
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                player.setAllowFlight(true);
                event.getView().close();
            } else if(MySQL.getFlyTool(player.getUniqueId().toString()) == 1) {
                MySQL.setFlyTool(player.getUniqueId().toString(), 0);
                PlayerInventory.playerInventory(player);

                player.sendMessage(Data.prefix + "§c§lFlugmodus §cdeaktiviert§7.");
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL,1,1);
                player.setAllowFlight(false);
                event.getView().close();
            }
        }
    }
}
