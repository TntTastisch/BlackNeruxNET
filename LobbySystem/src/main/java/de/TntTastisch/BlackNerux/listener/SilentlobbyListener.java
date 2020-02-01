package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.systems.Data;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collection;

public class SilentlobbyListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4§lSilentlobby")) {
            final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
            final Collection<ServerInfo> silentLobby = CloudAPI.getInstance().getServers("S-Lobby");

            for (final ServerInfo serverInfo : silentLobby) {
                if (serverInfo.isOnline() == true) {
                    if (serverInfo.getMaxPlayers() > serverInfo.getOnlineCount()) {
                        if (!cloudPlayer.getServer().equalsIgnoreCase(serverInfo.getServiceId().getServerId())) {

                            player.sendMessage(Data.globalPrefix + "§aEine Verbindung zu §e" + serverInfo.getServiceId().getGroup() + " §awird hergestellt...");

                            Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                                @Override
                                public void run() {
                                    Data.connectPlayer(cloudPlayer, serverInfo.getServiceId().getServerId());
                                }
                            }, 2 * 20L);

                            event.getView().close();
                        } else {
                            player.sendMessage(Data.globalPrefix + "§cDu bist bereits auf diesem Server!");
                            event.getView().close();
                        }
                    } else if (serverInfo.getMaxPlayers() <= serverInfo.getOnlineCount()) {
                        player.sendMessage(Data.globalPrefix + "§cDer Server ist voll!");
                        event.getView().close();
                    }
                } else {
                    player.sendMessage(Data.globalPrefix + "§cDieser Server ist Offline!");
                    event.getView().close();
                }
            }
        }
    }
}
