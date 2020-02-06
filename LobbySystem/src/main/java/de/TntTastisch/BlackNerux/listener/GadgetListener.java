package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import javax.persistence.Lob;
import java.util.HashMap;


public class GadgetListener implements Listener {

    public static HashMap<Player, BukkitRunnable> villager = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> wasser = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> lava = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> feuerwerk = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> enderman = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> herz = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> feuer = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> schnee = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> schleim = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> noten = new HashMap<Player, BukkitRunnable>();
    public static HashMap<Player, BukkitRunnable> verzaubert = new HashMap<Player, BukkitRunnable>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §6§lGadgets")){
            Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lExtras");
            mainInventory(player, inventory);
        }
    }

    @EventHandler
    public void onCLick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equalsIgnoreCase("§7➟ §e§lExtras")) {
            event.setCancelled(true);


            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §6§lAlle Extras entfernen")){
                if(MySQL.getGadgets(player.getUniqueId().toString()) != 0 || MySQL.getKoepfe(player.getUniqueId().toString()) != 0 || MySQL.getPaticle(player.getUniqueId().toString()) != 0){
                    MySQL.setGadgets(player.getUniqueId().toString(), 0);
                    MySQL.setParticle(player.getUniqueId().toString(), 0);
                    MySQL.setKoepfe(player.getUniqueId().toString(), 0);
                    PlayerInventory.playerInventory(player);
                    player.updateInventory();
                    player.getInventory().setHelmet(new ItemAPI(Material.AIR).create());
                    player.getInventory().setItem(3, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §4§lGadget §8× §c§lKein Gadget").create());

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(herz.containsKey(player)){
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(feuerwerk.containsKey(player)){
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)){
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)){
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)){
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)){
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    player.sendMessage(Data.prefix + "§7Du hast alle Extras entfernt!");
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keine Extras ausgewählt!");
                    event.getView().close();
                }
            }


            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §3§lKöpfe")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lExtras §8▏ §3§lKöpfe");

                for (int i = 0; i != inventory.getSize(); i++) {
                    inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
                }

                inventory.setItem(0, ItemAPI.SkullBuilder("§8➦ §cZurück", "MHF_ArrowLeft"));
                inventory.setItem(9, new ItemAPI(Material.REDSTONE_COMPARATOR).setDisplayname("§8➦ §cAlle Köpfe entfernen").create());



                inventory.setItem(2, ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.prefix"),
                        LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.player")));

                inventory.setItem(3, ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.prefix"),
                        LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.player")));

                inventory.setItem(4, ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.prefix"),
                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.player")));

                inventory.setItem(5, ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.prefix"),
                        LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.player")));

                inventory.setItem(6, ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.prefix"),
                        LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.player")));

                inventory.setItem(7, ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.prefix"),
                        LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.player")));

                inventory.setItem(8, ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.prefix"),
                        LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.player")));


                inventory.setItem(11, ItemAPI.SkullBuilder("§5FlooTastisch", "FlooTastisch"));
                inventory.setItem(12, ItemAPI.SkullBuilder("§5AbgegrieftHD", "AbgegrieftHD"));
                inventory.setItem(13, ItemAPI.SkullBuilder("§5rewinside", "rewinside"));
                inventory.setItem(14, ItemAPI.SkullBuilder("§5Floex", "Floex"));
                inventory.setItem(15, ItemAPI.SkullBuilder("§5GommeHD", "GommeHD"));
                inventory.setItem(16, ItemAPI.SkullBuilder("§5MOOO", "MOOO"));
                inventory.setItem(17, ItemAPI.SkullBuilder("§5Gamerstime", "Gamerstime"));

                inventory.setItem(20, ItemAPI.SkullBuilder("§bTNT", "MHF_TNT2"));
                inventory.setItem(21, ItemAPI.SkullBuilder("§bKiste", "MHF_Chest"));
                inventory.setItem(22, ItemAPI.SkullBuilder("§bMelone", "MHF_MELON"));
                inventory.setItem(23, ItemAPI.SkullBuilder("§bVillager", "MHF_VILLAGER"));
                inventory.setItem(24, ItemAPI.SkullBuilder("§bCreeper", "MHF_CREEPER"));
                inventory.setItem(25, ItemAPI.SkullBuilder("§bEnderman", "MHF_ENDERMAN"));
                inventory.setItem(26, ItemAPI.SkullBuilder("§bBlaze", "MHF_BLAZE"));

                player.openInventory(inventory);
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5§lGadgets")){
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lExtras §8▏ §5§lGadgets");

                for (int i = 0; i != inventory.getSize(); i++) {
                    inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
                }

                inventory.setItem(3, new ItemAPI(Material.ENDER_PEARL).setDisplayname("§8➦ §5Enderperle").create());
                inventory.setItem(4, new ItemAPI(Material.FISHING_ROD).setDisplayname("§8➦ §eEnterhaken").create());
                inventory.setItem(5, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());

                inventory.setItem(11, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
                inventory.setItem(12, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
                inventory.setItem(13, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
                inventory.setItem(14, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
                inventory.setItem(15, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());

                inventory.setItem(21, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
                inventory.setItem(22, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());
                inventory.setItem(23, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §cComming Soon").create());

                /*
                   inventory.setItem(5, new ItemAPI(Material.FIREWORK_CHARGE).setDisplayname("§8➦ §cStacker").create());

                inventory.setItem(11, new ItemAPI(Material.LEATHER_BOOTS).setColor(Color.GREEN).setUnbreakable(true).setDisplayname("§8➦ §aSpeedschuhe").create());
                inventory.setItem(12, new ItemAPI(Material.IRON_AXE).setDisplayname("§8➦ §fThors Hammer").create());
                inventory.setItem(13, new ItemAPI(Material.BLAZE_POWDER).setDisplayname("§8➦ §6Partikel Bombe").create());
                 */


                inventory.setItem(0, ItemAPI.SkullBuilder("§8➦ §cZurück", "MHF_ArrowLeft"));
                inventory.setItem(9, new ItemAPI(Material.REDSTONE_COMPARATOR).setDisplayname("§8➦ §cAlle Gadgets entfernen").create());

                player.openInventory(inventory);
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4§lPartikel")){
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lExtras §8▏ §4§lPartikel");

                for (int i = 0; i != inventory.getSize(); i++) {
                    inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
                }

                inventory.setItem(3, new ItemAPI(Material.EMERALD).setDisplayname("§8➦ §aVillager").create());
                inventory.setItem(4, new ItemAPI(Material.INK_SACK, 4).setDisplayname("§8➦ §9Wasser").create());
                inventory.setItem(5, new ItemAPI(Material.FIREBALL).setDisplayname("§8➦ §6Lava").create());

                inventory.setItem(11, new ItemAPI(Material.FIREWORK).setDisplayname("§8➦ §fFeuerwerk").create());
                inventory.setItem(12, new ItemAPI(Material.ENDER_PORTAL_FRAME).setDisplayname("§8➦ §9Enderman").create());
                inventory.setItem(13, new ItemAPI(Material.REDSTONE).setDisplayname("§8➦ §4Herz").create());
                inventory.setItem(14, new ItemAPI(Material.FLINT_AND_STEEL).setDisplayname("§8➦ §eFeuer").create());
                inventory.setItem(15, new ItemAPI(Material.SNOW_BALL).setDisplayname("§8➦ §fSchnee").create());

                inventory.setItem(21, new ItemAPI(Material.SLIME_BALL).setDisplayname("§8➦ §aSchleim").create());
                inventory.setItem(22, new ItemAPI(Material.NOTE_BLOCK).setDisplayname("§8➦ §9N§ao§4t§5e§3n").create());
                inventory.setItem(23, new ItemAPI(Material.ENCHANTMENT_TABLE).setDisplayname("§8➦ §5Verzaubert").create());

                inventory.setItem(0, ItemAPI.SkullBuilder("§8➦ §cZurück", "MHF_ArrowLeft"));
                inventory.setItem(9, new ItemAPI(Material.REDSTONE_COMPARATOR).setDisplayname("§8➦ §cAlle Partikel entfernen").create());

                player.openInventory(inventory);
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §fF§2e§a§cu§3e§br§6w§7e§1r§9k §bK§cr§4a§5c§8h§9e§0r")){
                if(MySQL.getGadgets(player.getUniqueId().toString()) != 99){
                    MySQL.setGadgets(player.getUniqueId().toString(), 99);

                    player.getInventory().setItem(3, new ItemAPI(Material.FIREWORK).setDisplayname("§8➦ §3Feuerwerk Kracher").create());
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits den §fF§2e§a§cu§3e§br§6w§7e§1r§9k §bK§cr§4a§5c§8h§9e§0r §causgewählt!");
                    event.getView().close();
                }
            }
        }



        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §e§lExtras §8▏ §4§lPartikel")){
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cZurück")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lExtras");
                mainInventory(player, inventory);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cAlle Partikel entfernen")) {
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 0) {
                    MySQL.setParticle(player.getUniqueId().toString(), 0);
                    player.sendMessage(Data.prefix + "§7Du hast deine Partikel entfernt!");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(herz.containsKey(player)){
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(feuerwerk.containsKey(player)){
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)){
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)){
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)){
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)){
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keinen Partikel ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §aVillager")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 1) {
                    MySQL.setParticle(player.getUniqueId().toString(), 1);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §aVillager §7ausgewählt");

                    if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(lava.containsKey(player)){
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(feuer.containsKey(player)){
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(enderman.containsKey(player)){
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    villager.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.HAPPY_VILLAGER, 600);
                        }
                    });
                    villager.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);




                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §9Wasser")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 2) {
                    MySQL.setParticle(player.getUniqueId().toString(), 2);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §9Wasser §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    wasser.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.WATERDRIP, 600);
                        }
                    });
                    wasser.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);




                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §6Lava")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 3) {
                    MySQL.setParticle(player.getUniqueId().toString(), 3);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §6Lava §7ausgewählt");


                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    lava.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.LAVADRIP, 600);
                        }
                    });
                    lava.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);



                    
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §fFeuerwerk")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 4) {
                    MySQL.setParticle(player.getUniqueId().toString(), 4);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §fFeuerwerk §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    feuerwerk.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.FIREWORKS_SPARK, 600);
                        }
                    });
                    feuerwerk.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);



                    
                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §9Enderman")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 5) {
                    MySQL.setParticle(player.getUniqueId().toString(), 5);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §9Enderman §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    enderman.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.ENDER_SIGNAL, 600);
                        }
                    });
                    enderman.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);




                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §4Herz")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 6) {
                    MySQL.setParticle(player.getUniqueId().toString(), 6);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §4Herz §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    herz.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.HEART, 600);
                        }
                    });
                    herz.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);




                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §eFeuer")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 7) {
                    MySQL.setParticle(player.getUniqueId().toString(), 7);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §eFeuer §7ausgewählt");


                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    feuer.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.LAVA_POP, 600);
                        }
                    });
                    feuer.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);




                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §fSchnee")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 8) {
                    MySQL.setParticle(player.getUniqueId().toString(), 8);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §fSchnee §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schleim.containsKey(player)){
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    schnee.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.SNOWBALL_BREAK, 600);
                        }
                    });
                    schnee.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §aSchleim")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 9) {
                    MySQL.setParticle(player.getUniqueId().toString(), 9);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §aSchleim §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(herz.containsKey(player)) {
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(wasser.containsKey(player)) {
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(noten.containsKey(player)) {
                        noten.get(player).cancel();
                        noten.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    schleim.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.SLIME, 600);
                        }
                    });
                    schleim.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);




                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §9N§ao§4t§5e§3n")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 10) {
                    MySQL.setParticle(player.getUniqueId().toString(), 10);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §9N§ao§4t§5e§3n §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if(lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(herz.containsKey(player)){
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(wasser.containsKey(player)){
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(enderman.containsKey(player)){
                        enderman.get(player).cancel();
                        enderman.remove(player);
                    } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(schleim.containsKey(player)) {
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(verzaubert.containsKey(player)) {
                        verzaubert.get(player).cancel();
                        verzaubert.remove(player);
                    }

                    noten.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.NOTE, 600);
                        }
                    });
                    noten.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);




                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5Verzaubert")){
                if (MySQL.getPaticle(player.getUniqueId().toString()) != 11) {
                    MySQL.setParticle(player.getUniqueId().toString(), 11);
                    player.sendMessage(Data.prefix + "§7Du hast den Partikel-Effekt §5Verzaubert §7ausgewählt");

                    if(villager.containsKey(player)) {
                        villager.get(player).cancel();
                        villager.remove(player);
                    } else if (lava.containsKey(player)) {
                        lava.get(player).cancel();
                        lava.remove(player);
                    } else if(herz.containsKey(player)){
                        herz.get(player).cancel();
                        herz.remove(player);
                    } else if(wasser.containsKey(player)){
                        wasser.get(player).cancel();
                        wasser.remove(player);
                    } else if(enderman.containsKey(player)) {
                        enderman.get(player).cancel();
                        enderman.remove(player);
                     } else if(feuerwerk.containsKey(player)) {
                        feuerwerk.get(player).cancel();
                        feuerwerk.remove(player);
                    } else if(schnee.containsKey(player)) {
                        schnee.get(player).cancel();
                        schnee.remove(player);
                    } else if(feuer.containsKey(player)) {
                        feuer.get(player).cancel();
                        feuer.remove(player);
                    } else if(schleim.containsKey(player)){
                        schleim.get(player).cancel();
                        schleim.remove(player);
                    } else if(noten.containsKey(player)){
                        noten.get(player).cancel();
                        noten.remove(player);
                    }

                    verzaubert.put(player, new BukkitRunnable() {

                        public void run() {
                            Location location = player.getLocation();
                            player.playEffect(location, Effect.WITCH_MAGIC, 600);
                        }
                    });
                    verzaubert.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);



                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits diesen Partikel-Effekt ausgewählt!");
                    event.getView().close();
                }
            }
        }

        if(event.getInventory().getName().equalsIgnoreCase("§7➟ §e§lExtras §8▏ §5§lGadgets")){
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cZurück")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lExtras");
                mainInventory(player, inventory);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cAlle Gadgets entfernen")) {
                if (MySQL.getGadgets(player.getUniqueId().toString()) != 0) {
                    MySQL.setGadgets(player.getUniqueId().toString(), 0);
                    player.getInventory().setItem(3, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §4§lGadget §8× §c§lKein Gadget").create());
                    player.sendMessage(Data.prefix + "§7Du hast deine Gadgets entfernt!");

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keinen Gadgets ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5Enderperle")){
                if(MySQL.getGadgets(player.getUniqueId().toString()) != 1){
                    MySQL.setGadgets(player.getUniqueId().toString(), 1);
                    player.getInventory().setItem(3, new ItemAPI(Material.ENDER_PEARL).setDisplayname("§8➦ §5§lEnderperle").create());
                    player.sendMessage(Data.prefix + "§7Du hast das Gadget §5§lEnderperle §7ausgewählt.");

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits dieses Gadget ausgewählt!");
                    event.getView().close();
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §eEnterhaken")){
                if(MySQL.getGadgets(player.getUniqueId().toString()) != 2){
                    MySQL.setGadgets(player.getUniqueId().toString(), 2);
                    ItemStack itemStack = new ItemStack(Material.FISHING_ROD);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("§8➦ §e§lEnterhaken");
                    itemMeta.spigot().setUnbreakable(true);
                    itemStack.setItemMeta(itemMeta);

                    player.getInventory().setItem(3, itemStack);
                    player.sendMessage(Data.prefix + "§7Du hast das Gadget §e§lEnterhaken §7ausgewählt.");

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast bereits dieses Gadget ausgewählt!");
                    event.getView().close();
                }
            }


        }

        if (event.getInventory().getName().equalsIgnoreCase("§7➟ §e§lExtras §8▏ §3§lKöpfe")) {
            event.setCancelled(true);

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cZurück")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "§7➟ §e§lExtras");
                mainInventory(player, inventory);
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §cAlle Köpfe entfernen")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 0) {

                    MySQL.setKoepfe(player.getUniqueId().toString(), 0);
                    player.getInventory().setHelmet(new ItemAPI(Material.AIR).create());
                    player.sendMessage(Data.prefix + "§7Du hast deinen Kopf abgelegt!");

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keinen Kopf auf!");
                    event.getView().close();
                }
            }

                        /*
                        configuration.addDefault("Koepfe.1slot.player", "TntTastisch");
                        configuration.addDefault("", "§4§lOwner ▏ §4§lTntTastisch");
                 */

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.prefix"))) {
                if(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.prefix") != "§cTeammitglied...") {
                    if (MySQL.getKoepfe(player.getUniqueId().toString()) != 1) {
                        MySQL.setKoepfe(player.getUniqueId().toString(), 1);
                        player.sendMessage(Data.prefix + "§7Du hast den Kopf von " + LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.prefix") + " §7aufgesetzt!");
                        player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.prefix"),
                                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.player")));

                        event.getView().close();
                    } else {
                        player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                        event.getView().close();
                    }
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.prefix"))) {
                if(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.prefix") != "§cTeammitglied...") {
                    if (MySQL.getKoepfe(player.getUniqueId().toString()) != 2) {
                        MySQL.setKoepfe(player.getUniqueId().toString(), 2);
                        player.sendMessage(Data.prefix + "§7Du hast den Kopf von " + LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.prefix") + " §7aufgesetzt!");
                        player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.prefix"),
                                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.player")));

                        event.getView().close();
                    } else {
                        player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                        event.getView().close();
                    }
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.prefix"))) {
                if(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.prefix") != "§cTeammitglied...") {
                    if (MySQL.getKoepfe(player.getUniqueId().toString()) != 3) {
                        MySQL.setKoepfe(player.getUniqueId().toString(), 3);
                        player.sendMessage(Data.prefix + "§7Du hast den Kopf von " + LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.prefix") + " §7aufgesetzt!");
                        player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.prefix"),
                                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.player")));

                        event.getView().close();
                    } else {
                        player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                        event.getView().close();
                    }
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.prefix"))) {
                if(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.prefix") != "§cTeammitglied...") {
                    if (MySQL.getKoepfe(player.getUniqueId().toString()) != 4) {
                        MySQL.setKoepfe(player.getUniqueId().toString(), 4);
                        player.sendMessage(Data.prefix + "§7Du hast den Kopf von " + LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.prefix") + " §7aufgesetzt!");
                        player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.prefix"),
                                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.player")));

                        event.getView().close();
                    } else {
                        player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                        event.getView().close();
                    }
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.prefix"))) {
                if(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.prefix") != "§cTeammitglied...") {
                    if (MySQL.getKoepfe(player.getUniqueId().toString()) != 5) {
                        MySQL.setKoepfe(player.getUniqueId().toString(), 5);
                        player.sendMessage(Data.prefix + "§7Du hast den Kopf von " + LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.prefix") + " §7aufgesetzt!");
                        player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.prefix"),
                                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.player")));

                        event.getView().close();
                    } else {
                        player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                        event.getView().close();
                    }
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.prefix"))) {
                if(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.prefix") != "§cTeammitglied...") {
                    if (MySQL.getKoepfe(player.getUniqueId().toString()) != 6) {
                        MySQL.setKoepfe(player.getUniqueId().toString(), 6);
                        player.sendMessage(Data.prefix + "§7Du hast den Kopf von " + LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.prefix") + " §7aufgesetzt!");
                        player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.prefix"),
                                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.player")));

                        event.getView().close();
                    } else {
                        player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                        event.getView().close();
                    }
                }
            }

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.prefix"))) {
                if(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.prefix") != "§cTeammitglied..."){
                    if (MySQL.getKoepfe(player.getUniqueId().toString()) != 7) {
                        MySQL.setKoepfe(player.getUniqueId().toString(), 7);
                        player.sendMessage(Data.prefix + "§7Du hast den Kopf von " + LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.prefix") + " §7aufgesetzt!");
                        player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.prefix"),
                                LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.player")));

                        event.getView().close();
                    } else {
                        player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                        event.getView().close();
                    }
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5FlooTastisch")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 8) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 8);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §5FlooTastisch §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5FlooTastisch", "FlooTastisch"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5AbgegrieftHD")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 9) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 9);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §5AbgegrieftHD §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5AbgegrieftHD", "AbgegrieftHD"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5rewinside")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 10) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 10);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §5rewinside §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5rewinside", "rewinside"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Floex")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 11) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 11);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §5Floex §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5Floex", "Floex"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5GommeHD")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 12) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 12);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §5GommeHD §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5GommeHD", "GommeHD"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5MOOO")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 13) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 13);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §5MOOO §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5MOOO", "MOOO"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Gamerstime")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 14) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 14);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §5Gamerstime §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5Gamerstime", "Gamerstime"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }


            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bTNT")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 15) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 15);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §bTNT §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bTNT", "MHF_TNT2"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bKiste")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 16) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 16);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §bKiste §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bKiste", "MHF_CHEST"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bMelone")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 17) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 17);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §bMelone §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bMelone", "MHF_MELON"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bVillager")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 18) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 18);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §bVillager §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bVillager", "MHF_VILLAGER"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bEnderman")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 19) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 19);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §bEnderman §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bEnderman", "MHF_ENDERMAN"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bBlaze")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 20) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 20);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §bBlaze §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bBlaze", "MHF_BLAZE"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCreeper")) {
                if (MySQL.getKoepfe(player.getUniqueId().toString()) != 21) {
                    MySQL.setKoepfe(player.getUniqueId().toString(), 21);
                    player.sendMessage(Data.prefix + "§7Du hast den Kopf von §bCreeper §7aufgesetzt!");
                    player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bCreeper", "MHF_CREEPER"));

                    event.getView().close();
                } else {
                    player.sendMessage(Data.prefix + " §cDu hast diesen Kopf bereits auf!");
                    event.getView().close();
                }
            }

        }
    }

    public void mainInventory(Player player, Inventory inventory){

        String eventtyp = LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Events.type");

        for(int i = 0; i != inventory.getSize(); i++){
            inventory.setItem(i, new ItemAPI(Material.STAINED_GLASS_PANE).setDisplayname(" ").create());
        }
        inventory.setItem(0, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        inventory.setItem(8, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        inventory.setItem(9, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        inventory.setItem(17, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        inventory.setItem(18, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());
        inventory.setItem(26, new ItemAPI(Material.STAINED_GLASS_PANE, 15).setDisplayname(" ").create());

        if(eventtyp.equalsIgnoreCase("Silvester")) {
            inventory.setItem(19, new ItemAPI(Material.FIREWORK).setDisplayname("§8➦ §fF§2e§a§cu§3e§br§6w§7e§1r§9k §bK§cr§4a§5c§8h§9e§0r").create());
        }
        inventory.setItem(4, ItemAPI.SkullBuilder("§8➦ §3§lKöpfe", player.getName()));
        inventory.setItem(12, new ItemAPI(Material.FISHING_ROD).setDisplayname("§8➦ §5§lGadgets").create());
        inventory.setItem(14, new ItemAPI(Material.REDSTONE).setDisplayname("§8➦ §4§lPartikel").create());

        inventory.setItem(25, new ItemAPI(Material.REDSTONE_COMPARATOR).setDisplayname("§8➦ §6§lAlle Extras entfernen").create());

        player.openInventory(inventory);
    }

}