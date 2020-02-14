package de.TeeJan.BlackNerux.utils;

import de.TeeJan.BlackNerux.api.ItemAPI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class PlayerInvetory {

    public static void setItems(Player player) {
        player.getInventory().setItem(0, new ItemAPI(Material.STICK).setDisplayname("").addUnsafeEnchantment(Enchantment.KNOCKBACK, 2).create());

/*
        for (int i = 9; i != 35; i++) {
            player.getInventory().setItem(i, new de.TeeJan.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
        }

        player.getInventory().setItem(9, new de.TeeJan.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        player.getInventory().setItem(10, new de.TeeJan.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        player.getInventory().setItem(17, new de.TeeJan.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        player.getInventory().setItem(26, new de.TeeJan.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        player.getInventory().setItem(27, new de.TeeJan.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        player.getInventory().setItem(35, new de.TeeJan.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());

        player.getInventory().setItem(22, new de.TeeJan.BlackNerux.api.ItemAPI(Material.CHEST).setDisplayname("§8➦ §5Gadget").create());

 */

    }
}