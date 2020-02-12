package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class LobbyInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4Einstellungen")){
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §c§lEinstellungen");

                for(int i = 0; i != 27; i++){
                    inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname("").create());
                }

                List<String> demonen = new ArrayList<String>();
                List<String> police = new ArrayList<String>();

                demonen.add("§8§m---------------");
                demonen.add("§7Du besitzt §4" + MySQL.getDemonPasses(player.getUniqueId().toString()) + " Dämonen-Pässe.");
                demonen.add("");

                police.add("§8§m---------------");
                police.add("§7Du besitzt §9" + MySQL.getPolicePasses(player.getUniqueId().toString()) + " Polizei-Pässe.");
                police.add("");

                inventory.setItem(0, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(8, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(9, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(17, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(18, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
                inventory.setItem(26, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());

                inventory.setItem(11, new ItemAPI(Material.WOOL,14).setDisplayname("§8➦ §4Dämonen-Pass").setLore(demonen).create());
                inventory.setItem(13, new ItemAPI(Material.DIAMOND).setDisplayname("§8➦ §aStart").create());
                inventory.setItem(15, new ItemAPI(Material.WOOL,11).setDisplayname("§8➦ §9Polizei-Pass").setLore(police).create());


                player.openInventory(inventory);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cVerlassen")){
                player.kickPlayer(" ");
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §c§lEinstellungen")){
            event.setCancelled(true);

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §aStart")){
                if(player.hasPermission("escapethedemons.vip.start")) {
                    if(!Bukkit.getScheduler().isQueued(JoinQuitListener.LobbyTimer)){
                        player.sendMessage(Data.prefix + "§cWarten auf weitere Spieler...");
                        event.getView().close();
                    } else {
                        if (JoinQuitListener.timer <= 10) {
                            player.sendMessage(Data.prefix + "§cDas Spiel startet bereits!");
                        } else {
                            JoinQuitListener.timer = 10;
                            player.sendMessage(Data.prefix + "§7Du hast das Spiel §aerfolgreich §7gestartet!");
                        }
                    }
                } else {
                    player.sendMessage(Data.noPerms);
                    event.getView().close();
                }

            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4Dämonen-Pass")){
                if(MySQL.getDemonPasses(player.getUniqueId().toString()) != 0){

                    Data.demon.add(player);
                    player.sendMessage(Data.prefix + "§7Du hast einen §4Dämonen-Pass §aausgewählt§7.");

                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keinen Dämonen-Pass!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §9Polizei-Pass")){
                if(MySQL.getPolicePasses(player.getUniqueId().toString()) != 0){

                    Data.police.add(player);
                    player.sendMessage(Data.prefix + "§7Du hast einen §9Polizei-Pass §aausgewählt§7.");

                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keinen Polizei-Pass!");
                    event.getView().close();
                }
            }
        }
    }
}
