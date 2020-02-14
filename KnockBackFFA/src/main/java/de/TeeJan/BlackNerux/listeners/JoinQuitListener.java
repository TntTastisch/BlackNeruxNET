package de.TeeJan.BlackNerux.listeners;

import de.TeeJan.BlackNerux.KnockBackFFA;
import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.systems.MySQL;
import de.TeeJan.BlackNerux.utils.PlayerInvetory;
import de.TeeJan.BlackNerux.utils.Scoreboard;
import de.TeeJan.BlackNerux.utils.SpawnConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        event.setJoinMessage(null);

        Bukkit.broadcastMessage(Data.prefix + Data.getPlayerPrefix(player) + " §7hat das Spiel §abetreten§7.");
        player.setHealthScale(2);
        player.setHealth(2);
        PlayerInvetory.setItems(player);
        SpawnConfig.teleportPlayerToLocation(player, KnockBackFFA.filecfg, "Spawn");

        MySQL.createPlayer(player.getUniqueId().toString());

        Bukkit.getScheduler().runTaskLater(KnockBackFFA.getInstance(), new Runnable() {
            public void run() {
                Scoreboard.setScoreboard(player);
            }
        }, 3);



        for(Player all : Bukkit.getOnlinePlayers()) {
            Scoreboard.setScoreboard(all);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(null);

        Bukkit.broadcastMessage(Data.prefix + Data.getPlayerPrefix(player) + " §7hat das Spiel §cverlassen§7.");

        Bukkit.getScheduler().runTaskLater(KnockBackFFA.getInstance(), new Runnable() {
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    Scoreboard.setScoreboard(all);
                }
            }
        }, 20L);

    }
}
