package de.TntTastisch.BlackNeruxNetworks;

import com.google.gson.JsonObject;
import de.dytanic.cloudnet.api.CloudAPI;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.java.JavaPlugin;

public class iSystems extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() { }

    public static void sendCurrentPlayingGamemode( Player p, boolean visible, String gamemodeName ) {
        JsonObject object = new JsonObject();
        object.addProperty( "show_gamemode", visible ); // Gamemode visible for everyone
        object.addProperty( "gamemode_name", gamemodeName ); // Name of the current playing gamemode

        // Send to LabyMod using the API
        LabyModPlugin.getInstance().sendServerMessage( p, "server_gamemode", object );
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage("§8» §4§lBlackNerux.net §7▏ " + getPlayerPrefix(event.getPlayer()) + " §7hat nun Lust zu bauen!");

        sendCurrentPlayingGamemode(event.getPlayer(), true, "§4BlackNerux §eBauServer");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage("§8» §4§lBlackNerux.net §7▏ " + getPlayerPrefix(event.getPlayer()) + " §7hat nun keine Lust mehr zu bauen!");
    }

    public static String getPlayerPrefix(Player player){
        if(player.hasPermission("prefix.owner")){
            return "§4Owner §8▏ §4" + player.getName();
        } else if(player.hasPermission("prefix.admin")) {
            return "§cAdministrator §8▏ §c" + player.getName();
        } else if(player.hasPermission("prefix.builder")){
            return "§9Builder §8▏ §9" + player.getName();
        } else if(player.hasPermission("prefix.spieler")){
            return "§aBesucher ▏ §a" + player.getName();
        }
        return null;
    }



    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();

        if(player.hasPermission("prefix.owner")){
            event.setFormat("§4Owner §8▏ §4" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        } else if(player.hasPermission("prefix.admin")){
            event.setFormat("§cAdministrator §8▏ §c" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        } else if(player.hasPermission("prefix.builder")){
            event.setFormat("§9Builder §8▏ §9" + player.getName() + " §8➥ §7" + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        } else if(player.hasPermission("prefix.spieler")){
            event.setFormat("§aBesucher §8▏ §a" + player.getName() + " §8➥ §7" + event.getMessage());
        }
    }

    @EventHandler
    public void onProcess(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();

        String befehl = event.getMessage().split(" ")[0];
        HelpTopic helptopic = Bukkit.getHelpMap().getHelpTopic(befehl);
        if (helptopic == null) {
            player.sendMessage("§8» §4§lBlackNerux.net §7▏ §7§cDieser Befehl ist dem System nicht bekannt! §8(§e" + befehl +"§8)§c");
            event.setCancelled(true);
        }

        if (event.getMessage().startsWith("/reload") || event.getMessage().startsWith("/bukkit:reload") ||
                event.getMessage().startsWith("/bukkit:rl") || event.getMessage().startsWith("/rl")) {
            event.setCancelled(true);
            player.sendMessage("§8» §4§lBlackNerux.net §7▏ §cDieser Befehl ist dem System nicht bekannt! §8(§e" + befehl +"§8)§c");
        }

        if (event.getMessage().startsWith("/spigot:restart") || event.getMessage().startsWith("/restart")) {
            event.setCancelled(true);
            player.sendMessage("§8» §4§lBlackNerux.net §7▏ §cDieser Befehl ist dem System nicht bekannt! §8(§e" + befehl +"§8)§c");
        }

        if (event.getMessage().startsWith("//calc") || event.getMessage().startsWith("worldedit:/calc")) {
            event.setCancelled(true);
            player.sendMessage("§8» §4§lBlackNerux.net §7▏ §cDieser Befehl ist dem System nicht bekannt! §8(§e" + befehl +"§8)§c");
        }

        if(event.getMessage().equalsIgnoreCase("/stop")){
            event.setCancelled(true);

            Bukkit.broadcastMessage("§8» §4§lBlackNerux.net §7▏ §7Der Server wird von §e" + getPlayerPrefix(player) + " §7neugestartet!");
            for(Player all : Bukkit.getOnlinePlayers()){
                all.kickPlayer(" ");
            }

            Bukkit.shutdown();

        }
    }
}
