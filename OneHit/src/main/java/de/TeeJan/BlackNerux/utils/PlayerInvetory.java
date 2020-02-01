package de.TeeJan.BlackNerux.utils;

import de.TeeJan.BlackNerux.OneHit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class PlayerInvetory {

    public static void setItems(Player player) {
        player.getInventory().setItem(0, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 5)
                .setDisplayname("§8➦ §4Messer").setUnbreakable(true).setAmount(1).create());
        player.getInventory().setItem(1, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.BOW).setAmount(1).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3)
                .setDisplayname("§8➦ §eBogen").setUnbreakable(true).create());

        for(int i = 9; i != 35; i++){
            player.getInventory().setItem(i, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
        }

        player.getInventory().setItem(9, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        player.getInventory().setItem(17, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        player.getInventory().setItem(18, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        player.getInventory().setItem(26, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        player.getInventory().setItem(27, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        player.getInventory().setItem(35, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());

        player.getInventory().setItem(22, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.CHEST).setDisplayname("§8➦ §5Gadget").create());

        player.getInventory().setItem(8, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.ARROW).setDisplayname("§8➦ §ePfeil").setAmount(32).create());

        player.getInventory().setHelmet(new de.TntTastisch.BlackNerux.api.ItemAPI(Material.LEATHER_HELMET).setDisplayname("§8➦ §aHelm").setUnbreakable(true).create());
        player.getInventory().setChestplate(new de.TntTastisch.BlackNerux.api.ItemAPI(Material.LEATHER_CHESTPLATE).setDisplayname("§8➦ §aHoddie").setUnbreakable(true).create());
        player.getInventory().setLeggings(new de.TntTastisch.BlackNerux.api.ItemAPI(Material.LEATHER_LEGGINGS).setDisplayname("§8➦ §aHose").setUnbreakable(true).create());
        player.getInventory().setBoots(new de.TntTastisch.BlackNerux.api.ItemAPI(Material.LEATHER_BOOTS).setDisplayname("§8➦ §aSchuhe").setUnbreakable(true).create());

    }
}
