package de.TeeJan.BlackNerux.Teams;

import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.inventory.InventoryHolder;


import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.Listener;

public class TeamSelect implements Listener {


    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (GameState.getGamestate() == GameState.LOBBY) {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fWähle ein Team")) {
                if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    e.setCancelled(true);
                    Inventory inv = Bukkit.createInventory(null, 9, "§7Wähle ein Team");

                    if (TeamInteract.getMax("Blau") != 0) {
                        inv.addItem(setTeamSeections((short) 11, "Blau", "§9Blau", Teams.blau, "§9"));
                    }

                    if (TeamInteract.getMax("Rot") != 0) {
                        inv.addItem(setTeamSeections((short) 14, "Rot", "§cRot", Teams.rot, "§c"));
                    }

                    if (TeamInteract.getMax("Gelb") != 0) {
                        inv.addItem(setTeamSeections((short) 4, "Gelb", "§eGelb", Teams.gelb, "§e"));
                    }

                    if (TeamInteract.getMax("Gruen") != 0) {
                        inv.addItem(setTeamSeections((short) 13, "Gruen", "§aGrün", Teams.grün, "§a"));
                    }

                    p.openInventory(inv);
                }
            }
        }
    }


    public static ItemStack setTeamSeections(final short by, final String getMax, final String TeamName, final ArrayList list, final String colorCODE) {
        ItemStack itemStack = new ItemStack(Material.WOOL, 1, by);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("" + TeamName + " §7(" + list.size() + "/" + TeamInteract.getMax(getMax) + ")");
        final List<String> lore = new ArrayList<String>();
        if (list.size() == 0) {
            lore.add(String.valueOf(colorCODE) + "Noch keine Spieler");
        }
        itemMeta.setLore((List)lore);
        itemStack.setItemMeta(itemMeta);
        return  itemStack;
    }
}
