package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.systems.Data;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.ArrayList;

public class EntityInteractListener implements Listener {

    public ArrayList<Player> vorbau = new ArrayList<Player>();
    public ArrayList<Player> cb1 = new ArrayList<Player>();
    public ArrayList<Player> cb2 = new ArrayList<Player>();
    public ArrayList<Player> cb3 = new ArrayList<Player>();

    @EventHandler
    public void onEntity(PlayerInteractAtEntityEvent event) {
        final Player player = event.getPlayer();

        if (event.getRightClicked().getCustomName().equalsIgnoreCase("§c§lFriday the 13th")) {

        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§b§lPrivate Server")) {
            player.sendMessage(Data.prefix + "§cDieses Tool ist derzeitig noch in Wartungsarbeiten!");
        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§4§lTTT")) {

        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§c§lGadget-Shop")) {

        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§5§lCityBuild-1")) {
            if (!cb1.contains(player)) {
                cb1.add(player);
                if (CloudAPI.getInstance().getServerInfo("CityBuild-1").isOnline()) {
                    final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

                    player.sendMessage(Data.globalPrefix + "§7§lVersuche eine Verbindung zu §5§lCityBuild-1 §7§lherzustellen...");

                    Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                        public void run() {
                            player.sendMessage(Data.globalPrefix + "§a§lDu wirst nun zu §5§lCityBuild-1 §a§lverbunden!");
                            Data.connectPlayer(cloudPlayer, "CityBuild-1");
                        }
                    }, 3 * 20L);
                } else {
                    player.sendMessage(Data.globalPrefix + "§cDieser Server ist derzeit offline!");
                }
            } else {
                player.sendMessage(Data.globalPrefix + "§cDu verbindest dich bereits!");
            }
        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§b§lCityBuild-2")) {
            if (!cb2.contains(player)) {
                cb2.add(player);
                if (CloudAPI.getInstance().getServerInfo("CityBuild-2").isOnline()) {
                    final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

                    player.sendMessage(Data.globalPrefix + "§7§lVersuche eine Verbindung zu §b§lCityBuild-2 §7§lherzustellen...");

                    Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                        public void run() {
                            player.sendMessage(Data.globalPrefix + "§a§lDu wirst nun zu §b§lCityBuild-2 §a§lverbunden!");
                            Data.connectPlayer(cloudPlayer, "CityBuild-2");
                        }
                    }, 3 * 20L);
                } else {
                    player.sendMessage(Data.globalPrefix + "§cDieser Server ist derzeit offline!");
                }
            } else {
                player.sendMessage(Data.globalPrefix + "§cDu verbindest dich bereits!");
            }
        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§b§lCityBuild-2 §7[§4§lWARTUNG§7]")) {
            player.sendMessage(Data.globalPrefix + "§cDieser Server ist in Wartungsarbeiten!");
        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§a§lCityBuild-3 §7[§4§lWARTUNGEN§7]")) {
            player.sendMessage(Data.globalPrefix + "§cDieser Server ist in Wartungsarbeiten!");
        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§a§lCityBuild-3")) {
            if (!cb3.contains(player)) {
                cb3.add(player);
                if (CloudAPI.getInstance().getServerInfo("CityBuild-3").isOnline()) {
                    final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

                    player.sendMessage(Data.globalPrefix + "§7§lVersuche eine Verbindung zu §a§lCityBuild-3 §7§lherzustellen...");

                    Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                        public void run() {
                            player.sendMessage(Data.globalPrefix + "§a§lDu wirst nun zu §a§lCityBuild-3 §a§lverbunden!");
                            Data.connectPlayer(cloudPlayer, "CityBuild-3");
                        }
                    }, 3 * 20L);
                } else {
                    player.sendMessage(Data.globalPrefix + "§cDieser Server ist derzeit offline!");
                }
            } else {
                player.sendMessage(Data.globalPrefix + "§cDieser Server ist derzeit offline!");
            }
        } else if (event.getRightClicked().getCustomName().equalsIgnoreCase("§4§lVorbau-Server")) {
            if (!vorbau.contains(player)) {
                vorbau.add(player);
                if (CloudAPI.getInstance().getServerInfo("Vorbau-1").isOnline()) {
                    final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

                    player.sendMessage(Data.globalPrefix + "§7§lVersuche eine Verbindung zu §4§LVorbau-Server §7§lherzustellen...");

                    Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                        public void run() {
                            player.sendMessage(Data.globalPrefix + "§a§lDu wirst nun zu §4§lVorbau-Server §a§lverbunden!");
                            Data.connectPlayer(cloudPlayer, "Vorbau-1");
                        }
                    }, 3 * 20L);
                } else {
                    player.sendMessage(Data.globalPrefix + "§cDieser Server ist derzeit offline!");
                }
            }
        }
    }
}
