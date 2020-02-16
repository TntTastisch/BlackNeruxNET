package de.TntTastisch.BlackNeruxNetworks;

import com.google.gson.JsonObject;
import com.mojang.authlib.yggdrasil.response.User;
import com.sun.media.jfxmediaimpl.platform.java.JavaPlatform;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class iSystems extends JavaPlugin implements Listener {

    public static String prefix = "§8» §4§lBlackNerux.net §7▏ §7";
    public static final String CHANNEL_NAME = "iSystem";
    public static final String COMMAND_PREFIX = "/";

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    public static String getPlayerPrefix(Player player) {
        if (player.hasPermission("prefix.owner")) {
            return "§4Owner §8▏ §4" + player.getName();
        } else if (player.hasPermission("prefix.admin")) {
            return "§cAdministrator §8▏ §c" + player.getName();
        } else if (player.hasPermission("prefix.srmod")) {
            return "§2SrModerator §8▏ §2" + player.getName();
        } else if (player.hasPermission("prefix.dev")) {
            return "§bDeveloper §8▏ §b" + player.getName();
        } else if (player.hasPermission("prefix.mod")) {
            return "§2Moderator §8▏ §2" + player.getName();
        } else if (player.hasPermission("prefix.sup")) {
            return "§aSupporter §8▏ §a" + player.getName();
        } else if (player.hasPermission("prefix.builder")) {
            return "§9Builder §8▏ §9" + player.getName();
        } else if (player.hasPermission("prefix.designer")) {
            return "§eDesigner §8▏ §3" + player.getName();
        } else if (player.hasPermission("prefix.freund")) {
            return "§dFreund §8▏ §d" + player.getName();
        } else if (player.hasPermission("prefix.twitch")) {
            return "§5Twitch §8▏ §5" + player.getName();
        } else if (player.hasPermission("prefix.youtuber")) {
            return "§fYT §8▏ §f" + player.getName();
        } else if (player.hasPermission("prefix.platinplus")) {
            return "§7Platin§a+§7 §8▏ §7" + player.getName();
        } else if (player.hasPermission("prefix.lapis")) {
            return "§9Lapis §8▏ §1" + player.getName();
        } else if (player.hasPermission("prefix.platin")) {
            return "§7Platin §8▏ §7" + player.getName();
        } else if (player.hasPermission("prefix.obsidian")) {
            return "§0Obsidian §8▏ §0" + player.getName();
        } else if (player.hasPermission("prefix.titan")) {
            return "§1Titan §8▏ §1" + player.getName();
        } else if (player.hasPermission("prefix.diamond")) {
            return "§3Diamond §8▏ §3" + player.getName();
        } else if (player.hasPermission("prefix.emerald")) {
            return "§aEmerald §8▏ §a" + player.getName();
        } else if (player.hasPermission("prefix.bronze")) {
            return "§6Bronze §8▏ §6" + player.getName();
        } else if (player.hasPermission("prefix.spieler")) {
            return "§8Spieler ▏ §8" + player.getName();
        }
        return null;
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("prefix.owner")) {
            event.setFormat("§4Owner §8▏ §4" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.admin")) {
            event.setFormat("§cAdministrator §8▏ §c" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.srmod")) {
            event.setFormat("§2SrModerator §8▏ §2" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.dev")) {
            event.setFormat("§bDeveloper §8▏ §b" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.mod")) {
            event.setFormat("§2Moderator §8▏ §2" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.sup")) {
            event.setFormat("§aSupporter §8▏ §a" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.builder")) {
            event.setFormat("§9Builder §8▏ §9" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.designer")) {
            event.setFormat("§eDesigner §8▏ §e" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.freund")) {
            event.setFormat("§dFreund §8▏ §d" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.twitch")) {
            event.setFormat("§5Twitch §8▏ §5" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.youtuber")) {
            event.setFormat("§fYouTuber §8▏ §f" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.platinplus")) {
            event.setFormat("§7Platin§a+§7 §8▏ §7" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.lapis")) {
            event.setFormat("§9Lapis §8▏ §9" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.platin")) {
            event.setFormat("§7Platin §8▏ §7" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.obsidian")) {
            event.setFormat("§0Obsidian §8▏ §0" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.titan")) {
            event.setFormat("§1Titan §8▏ §1" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.diamond")) {
            event.setFormat("§3Diamond §8▏ §3" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage().replaceAll("%", "%%")));
        } else if (player.hasPermission("prefix.emerald")) {
            event.setFormat("§aEmerald §8▏ §a" + player.getName() + " §8➥ §7" + event.getMessage().replaceAll("%", "%%"));
        } else if (player.hasPermission("prefix.bronze")) {
            event.setFormat("§6Bronze §8▏ §6" + player.getName() + " §8➥ §7" + event.getMessage().replaceAll("%", "%%"));
        } else if (player.hasPermission("prefix.spieler")) {
            event.setFormat("§8Spieler ▏ §8" + player.getName() + " §8➥ §7" + event.getMessage().replaceAll("%", "%%"));
        }
    }



    @EventHandler
    public void onProcess(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();

        String befehl = event.getMessage().split(" ")[0];
        HelpTopic helptopic = Bukkit.getHelpMap().getHelpTopic(befehl);
        if (helptopic == null) {
            player.sendMessage(prefix + "§cDieser Befehl ist dem System nicht bekannt!");
            event.setCancelled(true);
        }

        if (event.getMessage().startsWith("/reload") || event.getMessage().startsWith("/bukkit:reload") ||
                event.getMessage().startsWith("/bukkit:rl") || event.getMessage().startsWith("/rl")) {
            event.setCancelled(true);
            player.sendMessage(prefix + "§cDieser Befehl ist dem System nicht bekannt!");
        }

        if (event.getMessage().startsWith("/spigot:restart") || event.getMessage().startsWith("/restart")) {
            event.setCancelled(true);
            player.sendMessage(prefix + "§cDieser Befehl ist dem System nicht bekannt!");
        }

        if (event.getMessage().startsWith("//calc") || event.getMessage().startsWith("worldedit:/calc")) {
            event.setCancelled(true);
            player.sendMessage(prefix + "§cDieser Befehl ist dem System nicht bekannt!");
        }

        if(event.getMessage().equalsIgnoreCase("/stop")){
            event.setCancelled(true);

            for(Player all : Bukkit.getOnlinePlayers()){
                all.kickPlayer("§7Der Server wird von §e" + getPlayerPrefix(player) + " §7neugestartet!");
            }

            Bukkit.shutdown();

        }
    }
}
