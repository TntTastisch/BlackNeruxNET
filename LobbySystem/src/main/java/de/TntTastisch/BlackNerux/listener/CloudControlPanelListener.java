package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudProxy;
import de.dytanic.cloudnet.lib.server.ProxyGroup;
import de.dytanic.cloudnet.lib.server.SimpleServerGroup;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CloudControlPanelListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §b§lCloud Control Panel")){
            Inventory inventory = Bukkit.createInventory(null, 9*3, "§7➟ §4§lCloud Control");
            mainInventory(player, inventory);
        }
    }

    public void mainInventory(Player player, Inventory inventory){

        for(int i = 0; i < 9; i++){
            inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
        }

        inventory.setItem(4, ItemAPI.SkullBuilder("§8➦ §c§lSERVER CONTROL LIST", "MHF_Exclamation"));


        for (final String template : CloudAPI.getInstance().getProxyGroupMap().keySet()) {
            List<String> infos = new ArrayList<String>();

            infos.add("§8§m-------------------");
            infos.add("Proxy Online §8× §b" + CloudAPI.getInstance().getProxys(template).size());
            infos.add("Arbeitsspeicher §8× §b" + CloudAPI.getInstance().getProxyGroupData(template).getMemory());
            infos.add("Spieler Online §8× §a" + CloudAPI.getInstance().getOnlineCount() + "§7/§c" + CloudAPI.getInstance().getProxyGroupData(template).getProxyConfig().getMaxPlayers());

            inventory.addItem( new ItemAPI(Material.COMMAND).setDisplayname("§8➦ §c" + template).setLore(infos).create());
        }

        for (final String template : CloudAPI.getInstance().getServerGroupMap().keySet()) {

            List<String> infos = new ArrayList<>();

            infos.add("§8§m-------------------");
            infos.add("Server Online §8× §b" + CloudAPI.getInstance().getServers(template).size());
            infos.add("Arbeitsspeicher §8× §b" + CloudAPI.getInstance().getServerGroupData(template).getMemory());
            if(CloudAPI.getInstance().getServerGroupData(template).isMaintenance() == true) {
                infos.add("Wartungen §8× §b" + "JA");
            } else {
                infos.add("Wartungen §8× §b" + "NEIN");
            }

            infos.add("Modus §8× §b" + CloudAPI.getInstance().getServerGroupData(template).getMode());

            if(template.contains("Lobby")){
                inventory.addItem(new ItemStack[]{new ItemAPI(Material.REDSTONE_COMPARATOR).setDisplayname("§8➦ §a" + template).setLore(infos).create()});
            } else {
                inventory.addItem(new ItemStack[]{new ItemAPI(Material.COMMAND_MINECART).setDisplayname("§8➦ §a" + template).setLore(infos).create()});
            }


        }


        player.openInventory(inventory);
    }
}
