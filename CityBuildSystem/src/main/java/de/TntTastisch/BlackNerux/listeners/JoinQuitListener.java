package de.TntTastisch.BlackNerux.listeners;

import de.TntTastisch.BlackNerux.CityBuildSystem;
import de.TntTastisch.BlackNerux.commands.Home_CMD;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import de.TntTastisch.BlackNerux.utils.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        MySQL.createPlayer(player);
        Bukkit.getScheduler().runTaskLater(CityBuildSystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                LocationManager.getSpawn(player);
            }
        }, 20L*5);

        Home_CMD.setConfigOnJoin(player);
        ScoreboardManager.setScoreboard(player);

        if(player.hasPermission("citybuild.command.vanish")){
            if(MySQL.getVanish(player.getUniqueId().toString()) == 0){
                MySQL.setVanish(player.getUniqueId().toString(), 1);

                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3600000, 360000));

                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.hasPermission("citybuild.command.vanish.bypass") || !all.hasPermission("citybuild.command.vanish")) {
                        all.hidePlayer(player);
                    }
                }

                player.sendMessage(Data.prefix + "§7Du wurdest automatisch in den §6Vanishmodus §7gesetzt.");
            }
        }

        if(player.hasPermission("citybuild.command.fly")){
            if(MySQL.getFly(player.getUniqueId().toString()) == 0){
                MySQL.setFly(player.getUniqueId().toString(), 1);

                player.setAllowFlight(true);

                player.sendMessage(Data.prefix + "§7Du wurdest automatisch in den §6Flugmodus §7gesetzt.");
            }
        }

        if(player.getGameMode() == GameMode.CREATIVE){
            player.setAllowFlight(true);
        }


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
