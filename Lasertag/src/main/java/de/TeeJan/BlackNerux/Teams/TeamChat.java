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
                Bukkit.broadcastMessage("§8[§9Blau§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (de.TeeJan.BlackNerux.Teams.Teams.rot.contains(p)) {
                Bukkit.broadcastMessage("§8[§cRot§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (de.TeeJan.BlackNerux.Teams.Teams.gelb.contains(p)) {
                Bukkit.broadcastMessage("§8[§eGelb§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (de.TeeJan.BlackNerux.Teams.Teams.grün.contains(p)) {
                Bukkit.broadcastMessage("§8[§aGrün§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
        } else {
            p.sendMessage(String.valueOf(Data.prefix) + "§cBitte achte auf deine Wortwahl!");

        }
    }

    public static void checkMessage(final Player p, final String message) {
        if (message.startsWith("@all")) {
            if (Teams.blau.contains(p)) {
                Bukkit.broadcastMessage("§8[§9Blau§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (Teams.rot.contains(p)) {
                Bukkit.broadcastMessage("§8[§cRot§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (Teams.gelb.contains(p)) {
                Bukkit.broadcastMessage("§8[§eGelb§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
            if (Teams.grün.contains(p)) {
                Bukkit.broadcastMessage("§8[§aGrün§8] " + getPlayerPrefix(p) + "§8§§7§o " + message.replaceAll("@all", ""));
            }
        }

    }

    public static String getPlayerPrefix(Player player){
        if(player.hasPermission("prefix.owner")){
            return "§4Owner §8▏ §4" + player.getName();
        } else if(player.hasPermission("prefix.admin")){
            return "§cAdministrator §8▏ §c" + player.getName();
        } else if(player.hasPermission("prefix.srmod")){
            return "§2SrModerator §8▏ §2" + player.getName();
        } else if(player.hasPermission("prefix.dev")){
            return "§bDeveloper §8▏ §b" + player.getName();
        } else if(player.hasPermission("prefix.mod")){
            return "§2Moderator §8▏ §2" + player.getName();
        } else if(player.hasPermission("prefix.sup")){
            return "§aSupporter §8▏ §a" + player.getName();
        } else if(player.hasPermission("prefix.builder")){
            return "§9Builder §8▏ §9" + player.getName();
        } else if(player.hasPermission("prefix.designer")){
            return "§eDesigner §8▏ §3" + player.getName();
        } else if(player.hasPermission("prefix.freund")){
            return "§dFreund §8▏ §d" + player.getName();
        } else if(player.hasPermission("prefix.twitch")){
            return "§5Twitch §8▏ §5" + player.getName();
        } else if(player.hasPermission("prefix.youtuber")){
            return "§fYTber §8▏ §f" + player.getName();
        } else if(player.hasPermission("prefix.platinplus")){
            return "§7Platin§a+§7 §8▏ §7" + player.getName();
        } else if(player.hasPermission("prefix.lapis")){
            return "§9Lapis §8▏ §1" + player.getName();
        } else if(player.hasPermission("prefix.platin")){
            return "§7Platin §8▏ §7" + player.getName();
        } else if(player.hasPermission("prefix.obsidian")){
            return "§0Obsidian §8▏ §§0" + player.getName();
        } else if(player.hasPermission("prefix.titan")){
            return "§1Titan §8▏ §1" + player.getName();
        } else if(player.hasPermission("prefix.diamond")){
            return "§3Diamond §8▏ §3" + player.getName();
        } else if(player.hasPermission("prefix.emerald")){
            return "§aEmerald §8▏ §a" + player.getName();
        } else if(player.hasPermission("prefix.bronze")){
            return "§6Bronze §8▏ §6" + player.getName();
        } else if(player.hasPermission("prefix.spieler")){
            return "§8Spieler ▏ §8" + player.getName();
        }
        return null;
    }

}
