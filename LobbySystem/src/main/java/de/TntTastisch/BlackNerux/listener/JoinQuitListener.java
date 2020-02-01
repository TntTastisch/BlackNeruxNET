package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.AronixDE.OnlineInstance;
import de.TntTastisch.BlackNerux.CoinsAPI;
import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import de.TntTastisch.BlackNerux.utils.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(null);

        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);

        String eventtyp = LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Events.type");

        if (eventtyp.equalsIgnoreCase("Silvester")) {
            MySQL.setGadgets(player.getUniqueId().toString(), 99);
        } else if (!eventtyp.equalsIgnoreCase("Silvester")) {
            MySQL.setGadgets(player.getUniqueId().toString(), 0);
        }

        player.setGameMode(GameMode.SURVIVAL);
        player.setLevel(y);
        player.setExp(0);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.setAllowFlight(false);
        player.getInventory().clear();
        PlayerInventory.playerInventory(player);
        ScoreboardManager.setScoreboard(player);

        Location locs = player.getLocation();

        if (!MySQL.playerExists(player.getUniqueId().toString())) {
            LocationManager.getSpawn(player);
        }

        MySQL.createPlayer(player.getUniqueId().toString(), player, locs.getX(), locs.getY(), locs.getZ(), locs.getYaw(), locs.getPitch(), locs.getWorld().getName());

        if (MySQL.playerExists(player.getUniqueId().toString())) {
            locs.setX(MySQL.getX(player.getUniqueId().toString()));
            locs.setY(MySQL.getY(player.getUniqueId().toString()));
            locs.setZ(MySQL.getZ(player.getUniqueId().toString()));
            locs.setYaw(MySQL.getYaw(player.getUniqueId().toString()));
            locs.setPitch(MySQL.getPitch(player.getUniqueId().toString()));
            locs.setWorld(Bukkit.getWorld(MySQL.getWorld(player.getUniqueId().toString())));
            player.teleport(locs);
        }


        if (MySQL.getFlyTool(player.getUniqueId().toString()) == 0) {
            player.setAllowFlight(false);
            player.setFlying(false);
        } else if (MySQL.getFlyTool(player.getUniqueId().toString()) == 1) {
            player.setAllowFlight(true);
            player.setFlying(true);
        }

        if (MySQL.getPlayerHider(player.getUniqueId().toString()) == 0) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.showPlayer(all);
            }
        } else if (MySQL.getPlayerHider(player.getUniqueId().toString()) == 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.hasPermission("lobbysystem.team")) {
                    player.showPlayer(all);
                } else {
                    player.hidePlayer(all);
                }
            }
        } else if (MySQL.getPlayerHider(player.getUniqueId().toString()) == 2) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(all);
            }
        }

        for(Player all : Bukkit.getOnlinePlayers()){
            if(MySQL.getPlayerHider(all.getUniqueId().toString()) == 1) {
                if (all.hasPermission("lobbysytem.team")) {
                    all.showPlayer(player);
                } else {
                    all.hidePlayer(player);
                }
            } else if(MySQL.getPlayerHider(all.getUniqueId().toString()) == 2){
                if(all != player){
                    all.hidePlayer(player);
                }
            }
        }


        if (MySQL.getGadgets(player.getUniqueId().toString()) == 0) {
            player.getInventory().setItem(3, new ItemAPI(Material.BARRIER).setDisplayname("§8➦ §4§lGadget §8× §c§lKein Gadget").create());
        } else if (MySQL.getGadgets(player.getUniqueId().toString()) == 1) {
            player.getInventory().setItem(3, new ItemAPI(Material.ENDER_PEARL).setDisplayname("§8➦ §5§lEnderperle").create());
        } else if (MySQL.getGadgets(player.getUniqueId().toString()) == 2) {
            ItemStack itemStack = new ItemStack(Material.FISHING_ROD);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§8➦ §e§lEnterhaken");
            itemMeta.spigot().setUnbreakable(true);
            itemStack.setItemMeta(itemMeta);

            player.getInventory().setItem(3, itemStack);

        } else if (MySQL.getGadgets(player.getUniqueId().toString()) == 99) {
            player.getInventory().setItem(3, new ItemAPI(Material.FIREWORK).setDisplayname("§8➦ §3Feuerwerk Kracher").create());
        }


        if (MySQL.getKoepfe(player.getUniqueId().toString()) == 0) {
            player.getInventory().setHelmet(new ItemAPI(Material.AIR).create());
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 1) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.prefix")
                    , LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.1slot.player")));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 2) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.prefix")
                    , LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.2slot.player")));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 3) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.prefix")
                    , LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.3slot.player")));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 4) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.prefix")
                    , LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.4slot.player")));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 5) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.prefix")
                    , LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.5slot.player")));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 6) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.prefix")
                    , LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.6slot.player")));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 7) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder(LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.prefix")
                    , LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Koepfe.7slot.player")));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 8) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5FlooTastisch", "FlooTastisch"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 9) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5AbgegrieftHD", "AbgegrieftHD"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 10) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5rewinside", "rewinside"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 11) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5Floex", "Floex"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 12) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5GommeHD", "GommeHD"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 13) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5MOOO", "MOOO"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 14) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§5Gamerstime", "Gamerstime"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 15) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bTNT", "MHF_TNT2"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 16) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bKiste", "MHF_CHEST"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 17) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bMelone", "MHF_MELON"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 18) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bVillager", "MHF_VILLAGER"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 19) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bEnderman", "MHF_ENDERMAN"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 20) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bBlaze", "MHF_BLAZE"));
        } else if (MySQL.getKoepfe(player.getUniqueId().toString()) == 21) {
            player.getInventory().setHelmet(ItemAPI.SkullBuilder("§bCreeper", "MHF_CREEPER"));
        }
        if (MySQL.getSchutzschild(player.getUniqueId().toString()) == 1) {
            ShieldListener.run.put(player, new BukkitRunnable() {

                public void run() {
                    Location location = player.getLocation();
                    for (int i = 1; i <= ShieldListener.strands; i++) {
                        for (int j = 1; j <= ShieldListener.particles; j++) {
                            float ratio = (float) j / ShieldListener.particles;
                            double angle = ShieldListener.curve * ratio * 2 * Math.PI / ShieldListener.strands + (2 * Math.PI * i / ShieldListener.strands) + ShieldListener.rotation;
                            double x = Math.cos(angle) * ratio * ShieldListener.radius;
                            double z = Math.sin(angle) * ratio * ShieldListener.radius;
                            location.add(x, 0, z);
                            location.getWorld().playEffect(location, ShieldListener.particle, 600);
                            location.subtract(x, 0, z);
                        }
                    }
                }
            });
            ShieldListener.run.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);

        } else if (MySQL.getSchutzschild(player.getUniqueId().toString()) == 0) {
            if (ShieldListener.run.containsKey(player)) {
                ShieldListener.run.remove(player);
                ShieldListener.run.get(player).cancel();
            }
        }
/*
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
            @Override
            public void run() {
                for (Player all : Bukkit.getOnlinePlayers()) {
                   Data.setSubtitle(all, player.getUniqueId(), "§6Onlinezeit §8× §b" + OnlineInstance.getHours(player) + "§3h §8▏ §6Coins §8× §e" + CoinsAPI.getCoins(player));
                }
            }
        },1,20);


 */

    }



    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(null);

        Location locs = player.getLocation();
        MySQL.setX(player.getUniqueId().toString(), locs.getX());
        MySQL.setY(player.getUniqueId().toString(), locs.getY());
        MySQL.setZ(player.getUniqueId().toString(), locs.getZ());
        MySQL.setYaw(player.getUniqueId().toString(), locs.getYaw());
        MySQL.setPitch(player.getUniqueId().toString(), locs.getPitch());
        MySQL.setWorld(player.getUniqueId().toString(), locs.getWorld().getName());

        if(MySQL.getSchutzschild(player.getUniqueId().toString()) == 1){
            ShieldListener.run.get(player).cancel();
            ShieldListener.run.remove(player);
        }

    }
}
