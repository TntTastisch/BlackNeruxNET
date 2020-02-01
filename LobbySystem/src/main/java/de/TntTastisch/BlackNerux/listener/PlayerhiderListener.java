package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerhiderListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §e§lSpieler Verstecken §8× §a§lAlle Spieler")){

                MySQL.setPlayerhiderData(player.getUniqueId().toString(), 1);
                PlayerInventory.playerInventory(player);

                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("lobbysystem.team")) {
                        player.showPlayer(all);
                    } else {
                        player.hidePlayer(all);
                    }
                }

                player.sendMessage(Data.prefix + "§7Du siehst nun nur noch §5Team-Mitglieder§7.");

            } else if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §e§lSpieler Verstecken §8× §5§lNur Team-Mitglieder")){

                MySQL.setPlayerhiderData(player.getUniqueId().toString(), 2);
                PlayerInventory.playerInventory(player);

                for (Player all : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(all);
                }

                player.sendMessage(Data.prefix + "§7Du siehst nun §ckeine Spieler §7mehr.");

            } else if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §e§lSpieler Verstecken §8× §c§lKeine Spieler")){
                MySQL.setPlayerhiderData(player.getUniqueId().toString(), 0);
                PlayerInventory.playerInventory(player);

                player.sendMessage(Data.prefix + "§7Du siehst nun §aalle Spieler§7.");

                for (Player all : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(all);
                }
            }

        }
    }
}
