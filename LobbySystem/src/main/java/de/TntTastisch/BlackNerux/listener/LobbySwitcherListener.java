package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class LobbySwitcherListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §9§lLobby-Switcher")) {
                Inventory inventory = Bukkit.createInventory(null, 9, "§7➟ §6Wähle eine Lobby");
                final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

                Collection<ServerInfo> premiumLobby = CloudAPI.getInstance().getServers("P-Lobby");
                Collection<ServerInfo> Lobby = CloudAPI.getInstance().getServers("Lobby");

                for(final ServerInfo serverInfo : Lobby) {
                    if (serverInfo.isOnline()) {
                        ArrayList<String> lob = new ArrayList<>();
                        lob.add(serverInfo.getServiceId().getServerId());


                        if (cloudPlayer.getServer().startsWith(serverInfo.getServiceId().getServerId())) {
                            inventory.addItem(new ItemAPI(Material.SUGAR).setDisplayname("§7➦ §a" + lob.toString().replace("[", "").replace("]", "")).addUnsafeEnchantment(Enchantment.DURABILITY, 1).setAmount(serverInfo.getOnlineCount()).create());
                        } else {
                            inventory.addItem(new ItemAPI(Material.SULPHUR).setDisplayname("§7➦ §a" + lob.toString().replace("[", "").replace("]", "")).setAmount(serverInfo.getOnlineCount()).create());
                        }
                    }
                }

                player.openInventory(inventory);

            }
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §6Wähle eine Lobby")){
            event.setCancelled(true);

            Collection<ServerInfo> premiumLobby = CloudAPI.getInstance().getServers("P-Lobby");
            Collection<ServerInfo> Lobby = CloudAPI.getInstance().getServers("Lobby");

            for(final ServerInfo serverInfo : premiumLobby) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7➦ §6" + serverInfo.getServiceId().getServerId())) {
                    if (event.getCurrentItem().getType() != Material.SUGAR) {
                        if (serverInfo.isOnline() == true) {
                            if (serverInfo.getMaxPlayers() >= serverInfo.getOnlineCount()) {


                                player.sendMessage(Data.globalPrefix + "§aEine Verbindung zu §e" + serverInfo.getServiceId().getServerId() + " §awird hergestellt...");

                                Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                                    @Override
                                    public void run() {
                                        Data.connectPlayer(cloudPlayer, serverInfo.getServiceId().getServerId());
                                    }
                                }, 2 * 20L);

                                event.getView().close();
                            } else if (serverInfo.getMaxPlayers() <= serverInfo.getOnlineCount()) {
                                player.sendMessage(Data.globalPrefix + "§cDer Server ist voll!");
                                event.getView().close();
                            }
                        } else {
                            player.sendMessage(Data.globalPrefix + "§cDieser Server ist Offline!");
                            event.getView().close();
                        }
                    } else {
                        event.getView().close();
                        player.sendMessage(Data.globalPrefix + "§cDu bist bereits auf diesem Server!");
                    }
                }
            }

            for(final ServerInfo serverInfo : Lobby) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7➦ §a" + serverInfo.getServiceId().getServerId())) {
                    if (event.getCurrentItem().getType() != Material.SUGAR) {
                        if (serverInfo.isOnline() == true) {
                            if (serverInfo.getMaxPlayers() >= serverInfo.getOnlineCount()) {

                                player.sendMessage(Data.globalPrefix + "§aEine Verbindung zu §e" + serverInfo.getServiceId().getServerId() + " §awird hergestellt...");

                                Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                                    @Override
                                    public void run() {
                                        Data.connectPlayer(cloudPlayer, serverInfo.getServiceId().getServerId());
                                    }
                                }, 2 * 20L);

                                event.getView().close();
                            } else if (serverInfo.getMaxPlayers() <= serverInfo.getOnlineCount()) {
                                player.sendMessage(Data.globalPrefix + "§cDer Server ist voll!");
                                event.getView().close();
                            }
                        } else {
                            player.sendMessage(Data.globalPrefix + "§cDieser Server ist Offline!");
                            event.getView().close();
                        }
                    } else {
                        event.getView().close();
                        player.sendMessage(Data.globalPrefix + "§cDu bist bereits auf diesem Server!");
                    }
                }
            }



        }

    }
}
