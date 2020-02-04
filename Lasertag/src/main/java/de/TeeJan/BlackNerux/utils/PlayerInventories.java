package de.TeeJan.BlackNerux.utils;

        import de.TeeJan.BlackNerux.LaserTag;
        import org.bukkit.Material;
        import org.bukkit.entity.Player;

public class PlayerInventories {

    public static void setStartItems(Player player) {
        player.getInventory().setItem(0, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.BED).setDisplayname("§bWähle ein Team").setAmount(1).create());
        player.getInventory().setItem(4, new de.TntTastisch.BlackNerux.api.ItemAPI(Material.TORCH).setDisplayname("§4Einstellungen").setAmount(1).create());
        player.getInventory().setItem(8, de.TntTastisch.BlackNerux.api.ItemAPI.SkullBuilder("§cVerlassen", "MHF_ArrowLeft"));

    }

    public static void setStopItems(Player player){
        player.getInventory().setItem(8, de.TntTastisch.BlackNerux.api.ItemAPI.SkullBuilder("§cVerlassen", "MHF_ArrowLeft"));
    }
}
