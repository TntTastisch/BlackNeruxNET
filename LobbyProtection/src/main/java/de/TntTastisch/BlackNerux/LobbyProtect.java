package de.TntTastisch.BlackNerux;

import com.sun.media.jfxmediaimpl.platform.java.JavaPlatform;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.util.Collection;

public class LobbyProtect extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {}

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

        Collection<ServerInfo> slobby = CloudAPI.getInstance().getServers("S-Lobby");
        Collection<ServerInfo> plobby = CloudAPI.getInstance().getServers("P-Lobby");

        for(ServerInfo serverInfo : slobby){
            if(cloudPlayer.getServer().equalsIgnoreCase(serverInfo.getServiceId().getServerId())){
                if(!player.hasPermission("system.silentlobby")) {
                    player.kickPlayer("§cDu hast keine Berechtigun, um dich auf diesen Server zu verbinden!");
                }
            }
        }

        for(ServerInfo serverInfo : plobby){
            if(cloudPlayer.getServer().equalsIgnoreCase(serverInfo.getServiceId().getServerId())){
                if(!player.hasPermission("system.premiumlobby")) {
                    player.kickPlayer("§cDu hast keine Berechtigun, um dich auf diesen Server zu verbinden!");
                }
            }
        }
    }
}
