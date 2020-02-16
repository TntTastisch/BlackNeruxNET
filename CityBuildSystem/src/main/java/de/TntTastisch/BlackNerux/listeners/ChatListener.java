package de.TntTastisch.BlackNerux.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();

        if(player.hasPermission("prefix.owner")){
            event.setFormat("§8»\n\n§4Owner §8▏ §4" + player.getName() + " §8➥ §a§l" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n\n§8»");
        } else if(player.hasPermission("prefix.admin")){
            event.setFormat("§8»\n\n§cAdministrator §8▏ §c" + player.getName() + " §8➥ §a§l" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n\n§8»");
        } else if(player.hasPermission("prefix.srmod")){
            event.setFormat("§8»\n§2SrModerator §8▏ §2" + player.getName() + " §8➥ §3§L" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n§8»");
        } else if(player.hasPermission("prefix.dev")){
            event.setFormat("§8»\n§bDeveloper §8▏ §b" + player.getName() + " §8➥ §3§L" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n§8»");
        } else if(player.hasPermission("prefix.mod")){
            event.setFormat("§8»\n§2Moderator §8▏ §2" + player.getName() + " §8➥ §3§L" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n§8»");
        } else if(player.hasPermission("prefix.sup")){
            event.setFormat("§8»\n§aSupporter §8▏ §a" + player.getName() + " §8➥ §3§L" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n§8»");
        } else if(player.hasPermission("prefix.builder")){
            event.setFormat("§8»\n§9Builder §8▏ §9" + player.getName() + " §8➥ §3§L" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n§8»");
        } else if(player.hasPermission("prefix.designer")){
            event.setFormat("§8»\n§eDesigner §8▏ §e" + player.getName() + " §8➥ §3§L" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")) + "\n§8»");
        } else if(player.hasPermission("prefix.freund")){
            event.setFormat("§8»\n§dFreund §8▏ §d" + player.getName() + " §8➥ §6§l" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.twitch")){
            event.setFormat("§8»\n§5Twitch §8▏ §5" + player.getName() + " §8➥ §6§l" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.youtuber")){
            event.setFormat("§8»\n§fYouTuber §8▏ §f" + player.getName() + " §8➥ §6§l" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.platinplus")){
            event.setFormat("§8»\n§7Platin§a+§7 §8▏ §7" + player.getName() + " §8➥ §7" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.lapis")){
            event.setFormat("§9Lapis §8▏ §9" + player.getName() + " §8➥ §7" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.platin")){
            event.setFormat("§7Platin §8▏ §7" + player.getName() + " §8➥ §7" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.obsidian")){
            event.setFormat("§0Obsidian §8▏ §0" + player.getName() + " §8➥ §7" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.titan")){
            event.setFormat("§1Titan §8▏ §1" + player.getName() + " §8➥ §7" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.diamond")){
            event.setFormat("§3Diamond §8▏ §3" + player.getName() + " §8➥ §7" +
                    ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if(player.hasPermission("prefix.emerald")){
            event.setFormat("§aEmerald §8▏ §a" + player.getName() + " §8➥ §7" + event.getMessage().replaceAll("%", "%%"));
        } else if(player.hasPermission("prefix.bronze")){
            event.setFormat("§6Bronze §8▏ §6" + player.getName() + " §8➥ §7" + event.getMessage().replaceAll("%", "%%"));
        } else if(player.hasPermission("prefix.spieler")){
            event.setFormat("§8Spieler ▏ §8" + player.getName() + " §8➥ §7" + event.getMessage().replaceAll("%", "%%"));
        }
    }
}
