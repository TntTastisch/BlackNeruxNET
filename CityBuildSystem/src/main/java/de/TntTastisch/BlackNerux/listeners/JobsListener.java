package de.TntTastisch.BlackNerux.listeners;

import de.TntTastisch.BlackNerux.CityBuildSystem;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class JobsListener implements Listener {

    @EventHandler
    public void onNPC(PlayerInteractEntityEvent event){

        Player player = event.getPlayer();

        if(event.getRightClicked().getCustomName().equalsIgnoreCase("§8» §c§lArbeitsamt")){

            JobInventory(player);
        }

    }

    public static void JobInventory(Player player){

        Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §4§lJob-Center");

        for(int i = 0; i != inventory.getSize(); i++){
            inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
        }

        inventory.setItem(0, new ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        inventory.setItem(8, new ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        inventory.setItem(9, new ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        inventory.setItem(17, new ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        inventory.setItem(18, new ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());
        inventory.setItem(26, new ItemAPI(Material.STAINED_GLASS_PANE,15).setDisplayname(" ").create());

        inventory.setItem(3, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
        inventory.setItem(4, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
        inventory.setItem(5, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());

        inventory.setItem(11, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
        inventory.setItem(12, new ItemAPI(Material.DIAMOND_PICKAXE).setDisplayname("§8➦ §eMinenarbeiter").create());
        inventory.setItem(13, new ItemAPI(Material.IRON_AXE).setDisplayname("§8➦ §2Holzfäller").create());
        inventory.setItem(14, new ItemAPI(Material.GOLD_SWORD).setDisplayname("§8➦ §4Metzger").create());
        inventory.setItem(15, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());

        inventory.setItem(21, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
        inventory.setItem(22, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
        inventory.setItem(23, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());

        inventory.setItem(25, new ItemAPI(Material.SLIME_BALL).setDisplayname("§8➦ §cArbeitslos").create());

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventory(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §4§lJob-Center")){
            event.setCancelled(true);

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cArbeitslos")){
                if(!MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Arbeitslos")){
                    MySQL.setJob(player.getUniqueId().toString(), "Arbeitslos");

                    player.sendMessage(Data.prefix + "§7Du hast nun keinen Job mehr.");
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keinen Job!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §eMinenarbeiter")){
                if(!MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Minenarbeiter")){
                    MySQL.setJob(player.getUniqueId().toString(), "Minenarbeiter");

                    player.sendMessage(Data.prefix + "§7Du hast den Job §eMinenarbeiter §7ausgewählt.");
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Job ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §2Holzfäller")){
                if(!MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Holzfäller")){
                    MySQL.setJob(player.getUniqueId().toString(), "Holzfäller");

                    player.sendMessage(Data.prefix + "§7Du hast den Job §2Holzfäller §7ausgewählt.");
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Job ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4Metzger")){
                if(!MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Metzger")){
                    MySQL.setJob(player.getUniqueId().toString(), "Metzger");

                    player.sendMessage(Data.prefix + "§7Du hast den Job §4Metzger §7ausgewählt.");
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Job ausgewählt!");
                    event.getView().close();
                }
            }

        }
    }

    @EventHandler
    public void minenarbeiter(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Minenarbeiter")){
            if(player.getGameMode() == GameMode.SURVIVAL) {

                if(!CityBuildSystem.economy.hasAccount(player)){
                    CityBuildSystem.economy.createPlayerAccount(player);
                }

                if (event.getBlock().getType() == Material.IRON_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 3.0);
                    player.sendMessage(Data.prefix + "§a+§73$");
                } else if (event.getBlock().getType() == Material.GOLD_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 2.0);
                    player.sendMessage(Data.prefix + "§a+§72$");
                } else if (event.getBlock().getType() == Material.COAL_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 1.0);
                    player.sendMessage(Data.prefix + "§a+§71$");
                } else if (event.getBlock().getType() == Material.DIAMOND_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 10.0);
                    player.sendMessage(Data.prefix + "§a+§710$");
                } else if (event.getBlock().getType() == Material.EMERALD_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 8.0);
                    player.sendMessage(Data.prefix + "§a+§78$");
                } else if (event.getBlock().getType() == Material.LAPIS_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 7.0);
                    player.sendMessage(Data.prefix + "§a+§77$");
                } else if (event.getBlock().getType() == Material.REDSTONE_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 2.0);
                    player.sendMessage(Data.prefix + "§a+§72$");
                } else if (event.getBlock().getType() == Material.QUARTZ_ORE) {
                    CityBuildSystem.economy.depositPlayer(player, 4.0);
                    player.sendMessage(Data.prefix + "§a+§74$");
                }
            }
        }
    }

    @EventHandler
    public void holzfäller(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Holzfäller")){
            if(player.getGameMode() == GameMode.SURVIVAL) {

                if(!CityBuildSystem.economy.hasAccount(player)){
                    CityBuildSystem.economy.createPlayerAccount(player);
                }

                if (event.getBlock().getType() == Material.LOG) {
                    CityBuildSystem.economy.depositPlayer(player, 2.0);
                    player.sendMessage(Data.prefix + "§a+§72$");
                } else if (event.getBlock().getType() == Material.LOG_2) {
                    CityBuildSystem.economy.depositPlayer(player, 2.0);
                    player.sendMessage(Data.prefix + "§a+§72$");
                }
            }
        }
    }

    @EventHandler
    public void metzger(EntityDeathEvent event){
        Player player = (Player) event.getEntity().getKiller();

        if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Metzger")){
            if(player.getGameMode() == GameMode.SURVIVAL) {

                if (!CityBuildSystem.economy.hasAccount(player)) {
                    CityBuildSystem.economy.createPlayerAccount(player);
                }

                if (player.getGameMode() == GameMode.SURVIVAL) {

                    if (event.getEntityType() == EntityType.SHEEP) {
                        CityBuildSystem.economy.depositPlayer(player, 3.0);
                        player.sendMessage(Data.prefix + "§a+§73$");

                    } else if (event.getEntityType() == EntityType.PIG) {
                        CityBuildSystem.economy.depositPlayer(player, 4.0);
                        player.sendMessage(Data.prefix + "§a+§74$");

                    } else if (event.getEntityType() == EntityType.COW) {
                        CityBuildSystem.economy.depositPlayer(player, 2.0);
                        player.sendMessage(Data.prefix + "§a+§72$");

                    } else if (event.getEntityType() == EntityType.MUSHROOM_COW) {
                        CityBuildSystem.economy.depositPlayer(player, 6.0);
                        player.sendMessage(Data.prefix + "§a+§76$");

                    } else if (event.getEntityType() == EntityType.CHICKEN) {
                        CityBuildSystem.economy.depositPlayer(player, 1.0);
                        player.sendMessage(Data.prefix + "§a+§71$");

                    } else if (event.getEntityType() == EntityType.HORSE) {
                        CityBuildSystem.economy.depositPlayer(player, 5.0);
                        player.sendMessage(Data.prefix + "§a+§75$");
                    }
                }
            }
        }
    }
}
