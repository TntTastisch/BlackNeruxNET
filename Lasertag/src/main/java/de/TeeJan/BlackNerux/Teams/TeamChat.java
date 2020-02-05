package de.TeeJan.BlackNerux.Teams;

import java.util.Iterator;

import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;

import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;

public class TeamChat implements Listener {
    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        final Player p = e.getPlayer();
        String message = e.getMessage();
        message = e.getMessage();


        if (GameState.getGamestate() == GameState.LOBBY) {
            if (de.TeeJan.BlackNerux.Teams.Teams.blau.contains(p)) {
                Bukkit.broadcastMessage("§8[§9Blau§8] " + Data.getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (de.TeeJan.BlackNerux.Teams.Teams.rot.contains(p)) {
                Bukkit.broadcastMessage("§8[§cRot§8] " + Data.getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (de.TeeJan.BlackNerux.Teams.Teams.gelb.contains(p)) {
                Bukkit.broadcastMessage("§8[§eGelb§8] " + Data.getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (de.TeeJan.BlackNerux.Teams.Teams.grün.contains(p)) {
                Bukkit.broadcastMessage("§8[§aGrün§8] " + Data.getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
        }
    }
}
