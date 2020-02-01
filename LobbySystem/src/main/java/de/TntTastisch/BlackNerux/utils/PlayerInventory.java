package de.TntTastisch.BlackNerux.utils;

import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import sun.util.cldr.CLDRLocaleDataMetaInfo;

import java.util.Collection;

public class PlayerInventory {


    public static void playerInventory(Player player){

        player.getInventory().setItem(0, new ItemAPI(Material.BREWING_STAND_ITEM).setDisplayname("§8➦ §3§lNavigator").create());

        if(MySQL.getPlayerHider(player.getUniqueId().toString()) == 0){
            player.getInventory().setItem(1, new ItemAPI(Material.GREEN_RECORD).setDisplayname("§8➦ §e§lSpieler Verstecken §8× §a§lAlle Spieler").create());
        } else if(MySQL.getPlayerHider(player.getUniqueId().toString()) == 1){
            player.getInventory().setItem(1, new ItemAPI(Material.RECORD_6).setDisplayname("§8➦ §e§lSpieler Verstecken §8× §5§lNur Team-Mitglieder").create());
        } else if(MySQL.getPlayerHider(player.getUniqueId().toString()) == 2){
            player.getInventory().setItem(1, new ItemAPI(Material.RECORD_11).setDisplayname("§8➦ §e§lSpieler Verstecken §8× §c§lKeine Spieler").create());
        }

        player.getInventory().setItem(3, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §4§lGadget §8× §c§lKein Gadget").create());
        player.getInventory().setItem(5, new ItemAPI(Material.CHEST).setDisplayname("§8➦ §6§lGadgets").create());

        player.getInventory().setItem(7, new ItemAPI(Material.NETHER_STAR).setDisplayname("§8➦ §9§lLobby-Switcher").create());
        player.getInventory().setItem(8, ItemAPI.SkullBuilder("§8➦ §a§lFreunde", player.getName()));

        if(player.hasPermission("lobbysystem.nick")){
            player.getInventory().setItem(4, new ItemAPI(Material.NAME_TAG).setDisplayname("§8➦ §5§lNick").create());
        }

        if(player.hasPermission("lobbysystem.fly")){
            player.getInventory().setItem(20, new ItemAPI(Material.FEATHER).setDisplayname("§8➦ §c§lFly").create());
        }
        if(player.hasPermission("lobbysystem.shield")){
            player.getInventory().setItem(21, new ItemAPI(Material.EYE_OF_ENDER).setDisplayname("§8➦ §5§lSchutzschild").create());
        }

        if(player.hasPermission("system.control.cloudcontrolpanel")){
            player.getInventory().setItem(9, new ItemAPI(Material.COMMAND).setDisplayname("§8➦ §b§lCloud Control Panel").create());
        }

        if(player.hasPermission("system.silentlobby")){
            final Collection<ServerInfo> silentLobby = CloudAPI.getInstance().getServers("S-Lobby");

            for(final ServerInfo serverInfo : silentLobby) {
                if(serverInfo.isOnline()) {
                    if(!player.getServer().getServerId().equalsIgnoreCase(serverInfo.getServiceId().getServerId())){
                        player.getInventory().setItem(23, new ItemAPI(Material.TNT).setDisplayname("§8➦ §4§lSilentlobby").create());
                    } else {
                        player.getInventory().setItem(23, new ItemAPI(Material.TNT).setDisplayname("§8➦ §4SLOBBY FEHLER").create());
                    }
                }
            }
        }
    }
}
