package de.TntTastisch.BlackNerux.listeners;

import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.*;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        MySQL.createPlayer(player);


        if(MySQL.getVanish(player.getUniqueId().toString()) == 1){
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3600000, 360000));
            for(Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission("citybuild.command.vanish.bypass") || all.hasPermission("citybuild.command.vanish")) {
                    all.showPlayer(player);
                } else {
                    all.hidePlayer(player);
                }
            }
        }

        for(Player all : Bukkit.getOnlinePlayers()){
            if(MySQL.getVanish(all.getUniqueId().toString()) == 1) {
                if (player.hasPermission("citybuild.command.vanish.bypass") || player.hasPermission("citybuild.command.vanish")) {
                    player.showPlayer(all);
                } else {
                    player.hidePlayer(all);
                }

            }

        }


    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
    }
}
