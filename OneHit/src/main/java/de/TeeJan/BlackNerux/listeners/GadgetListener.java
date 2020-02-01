package de.TeeJan.BlackNerux.listeners;

import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.systems.MySQL;
import de.TeeJan.BlackNerux.utils.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class GadgetListener implements Listener {

    @EventHandler
    public void onGadget(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5Gadget")){
            Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lGadgets");

            for(int i = 0; i != inventory.getSize(); i++){
                inventory.setItem(i, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
            }

            inventory.setItem(0, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
            inventory.setItem(8, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
            inventory.setItem(9, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
            inventory.setItem(17, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
            inventory.setItem(18, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
            inventory.setItem(26, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());

            List<String> speedGadget = new ArrayList<String>();
            List<String> jumpGadget = new ArrayList<String>();

            speedGadget.add("§8§m-----------------------");
            speedGadget.add("§7Kosten für dieses Gadget§8:");
            speedGadget.add("§e5.000 Coins");

            jumpGadget.add("§8§m-----------------------");
            jumpGadget.add("§7Kosten für dieses Gadget§8:");
            jumpGadget.add("§e10.000 Coins");

            inventory.setItem(11, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.LEATHER_BOOTS).setColor(Color.GREEN).setDisplayname("§8➦ §bSpeed Gadget").setLore(speedGadget).create());
            inventory.setItem(15, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.DIAMOND).setDisplayname("§8➦ §aJumpBoost Gadget").setLore(jumpGadget).create());

            player.openInventory(inventory);
        }

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §e§lGadgets")){
            event.setCancelled(true);


            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §bSpeed Gadget")) {
                if (MySQL.getCoins(player.getUniqueId().toString()) >= 5000) {
                    MySQL.removeCoins(player.getUniqueId().toString(), 5000);
                    Scoreboard.setScoreboard(player);

                    player.sendMessage(Data.prefix + "§7Du hast das §bSpeed Gadget §7ausgewählt!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 2));
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast nicht genügend Coins.");
                    event.getView().close();
                }

            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §aJumpBoost Gadget")) {
                if (MySQL.getCoins(player.getUniqueId().toString()) >= 10000) {
                    MySQL.removeCoins(player.getUniqueId().toString(), 10000);
                    Scoreboard.setScoreboard(player);

                    player.sendMessage(Data.prefix + "§7Du hast das §aJumpBoost Gadget §7ausgewählt!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000, 2));
                    event.getView().close();

                } else {
                    player.sendMessage(Data.prefix + "§cDu hast nicht genügend Coins.");
                    event.getView().close();
                }
            }
        }
    }
}
