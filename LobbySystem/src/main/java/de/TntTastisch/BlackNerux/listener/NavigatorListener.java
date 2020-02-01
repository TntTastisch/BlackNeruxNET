package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import javax.xml.ws.WebEndpoint;
import java.util.Collection;

public class NavigatorListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §3§lNavigator")){
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){

                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §a§lTeleporter");

                for(int i = 0; i != inventory.getSize(); i++){
                    inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                }

                inventory.setItem(0 , ItemAPI.SkullBuilder("§8➦ §4Friday the 13th", "JasonVoorhees"));
                inventory.setItem(8 , ItemAPI.SkullBuilder("§8➦ §bWar Of Kingdom", "Flaversum"));

                inventory.setItem(3 , new ItemAPI(Material.DIAMOND_SWORD).setDisplayname("§8➦ §5OneHit").create());

                inventory.setItem(11 , new ItemAPI(Material.IRON_AXE).setDisplayname("§8➦ §eCityBuild").create());
                inventory.setItem(13 , new ItemAPI(Material.DIAMOND).setDisplayname("§8➦ §6Spawn").create());
                inventory.setItem(15 , new ItemAPI(Material.BLAZE_ROD).setDisplayname("§8➦ §3Laser Tag").create());

                inventory.setItem(26 , ItemAPI.SkullBuilder("§8➦ §9Escape the Demons", "ShadowScyther"));
                inventory.setItem(18, ItemAPI.SkullBuilder("§8➦ §cFive Nights at Minecraft", "Bonnie_Bunny"));

                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §a§lTeleporter")){
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §6Spawn")) {
                LocationManager.getSpawn(player);
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
                player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);
                event.getView().close();

            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5OneHit")){
                LocationManager.getOneHit(player);
                event.getView().close();
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4Friday the 13th")){
                LocationManager.getF13(player);
                event.getView().close();
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §bWar Of Kingdom")){
                LocationManager.getWoK(player);
                event.getView().close();
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §eCityBuild")) {
                final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
                final Collection<ServerInfo> cityBuild = CloudAPI.getInstance().getServers("CityBuild");

                for (final ServerInfo serverInfo : cityBuild) {
                    if (serverInfo.isOnline() == true) {
                        if(CloudAPI.getInstance().getServerGroup("CityBuild").isMaintenance() == false) {
                            if (serverInfo.getMaxPlayers() > serverInfo.getOnlineCount()) {

                                player.sendMessage(Data.globalPrefix + "§aEine Verbindung zu §e" + serverInfo.getServiceId().getGroup() + " §awird hergestellt...");

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
                            player.sendMessage(Data.globalPrefix + "§cDer Server ist derzeitig in Wartungsarbeiten...");
                            event.getView().close();
                        }
                    } else {
                        player.sendMessage(Data.globalPrefix + "§cDieser Server ist Offline!");
                        event.getView().close();
                    }
                }
                event.getView().close();
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §3Laser Tag")){
                LocationManager.getLaserTag(player);
                event.getView().close();
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §9Escape the Demons")){
                LocationManager.getEscapeTheDemons(player);
                event.getView().close();
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cFive Nights at Minecraft")){
                LocationManager.getFiveNightsAtMinecraft(player);
                event.getView().close();
            }
        }
    }
}
