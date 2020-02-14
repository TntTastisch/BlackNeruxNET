package de.TeeJan.BlackNerux.listeners;

import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.systems.MySQL;
import de.TeeJan.BlackNerux.utils.PlayerInvetory;
import de.TeeJan.BlackNerux.utils.Scoreboard;
import de.TeeJan.BlackNerux.utils.SpawnConfig;
import de.TeeJan.BlackNerux.KnockBackFFA;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        final Player player = e.getEntity();
        e.setDeathMessage(null);
        e.setKeepInventory(true);
        e.setKeepLevel(true);

        player.setHealth(2);
        player.setHealthScale(2);
        player.spigot().respawn();
        player.getInventory().clear();
        PlayerInvetory.setItems(player);
        SpawnConfig.teleportPlayerToLocation(player, KnockBackFFA.filecfg, "Spawn");

        if(player.getKiller() != null){
            MySQL.addDeath(player.getUniqueId().toString(), 1);
            MySQL.addKills(player.getKiller().getUniqueId().toString(), 1);
            MySQL.addCoins(player.getKiller().getUniqueId().toString(), 5);
            MySQL.removeCoins(player.getUniqueId().toString(), 5);
            Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §6" + Data.getPlayerPrefix(player) + " §7wurde von §6" + Data.getPlayerPrefix(player.getKiller()) + " §7getötet!");
            player.sendMessage(Data.prefix + "§cDu wurdest von §6" + Data.getPlayerPrefix(player.getKiller()) + " §cgetötet!");
            player.getKiller().sendMessage(Data.prefix + "§aDu hast §6" + Data.getPlayerPrefix(player) + " §agetötet!");
            Scoreboard.setScoreboard(player);
            Scoreboard.setScoreboard(player.getKiller());

        } else {
            MySQL.addDeath(player.getUniqueId().toString(), 1);
            MySQL.removeCoins(player.getUniqueId().toString(), 1);
            Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §6" +Data.getPlayerPrefix(player) + " §7ist gestorben!");
            player.sendMessage(Data.prefix + "§cDu bist gestorben!");
            Scoreboard.setScoreboard(player);

        }

    }
}
