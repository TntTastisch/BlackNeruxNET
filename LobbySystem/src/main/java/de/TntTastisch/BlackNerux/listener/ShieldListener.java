package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class ShieldListener implements Listener {

    public static HashMap<Player, BukkitRunnable> run = new HashMap<Player, BukkitRunnable>();
    public static Effect particle = Effect.HAPPY_VILLAGER;
    public static int strands = 4;
    public static float radius = 5;
    public static int particles = 80;
    public static float curve = 5;
    public static double rotation = Math.PI / 4;


    @EventHandler
    public void onShield(InventoryClickEvent event){
        final Player player = (Player) event.getWhoClicked();

        if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5§lSchutzschild")){
            if(MySQL.getSchutzschild(player.getUniqueId().toString()) == 0) {
                MySQL.setSchutzschild(player.getUniqueId().toString(), 1);
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                PlayerInventory.playerInventory(player);
                player.sendMessage(Data.prefix + "§5§lSchutzschild §aaktiviert§7.");
                event.getView().close();
                run.put(player, new BukkitRunnable() {

                    public void run() {
                        Location location = player.getLocation();
                        for (int i = 1; i <= strands; i++) {
                            for (int j = 1; j <= particles; j++) {
                                float ratio = (float) j / particles;
                                double angle = curve * ratio * 2 * Math.PI / strands + (2 * Math.PI * i / strands) + rotation;
                                double x = Math.cos(angle) * ratio * radius;
                                double z = Math.sin(angle) * ratio * radius;
                                location.add(x, 0, z);
                                player.playEffect(location, particle, 600);
                                location.subtract(x, 0, z);
                            }
                        }
                    }
                });
                run.get(player).runTaskTimer(LobbySystem.getPlugin(LobbySystem.class), 1L, 1L);

            } else if(MySQL.getSchutzschild(player.getUniqueId().toString()) == 1){
                MySQL.setSchutzschild(player.getUniqueId().toString(), 0);
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                PlayerInventory.playerInventory(player);
                player.sendMessage(Data.prefix + "§5§lSchutzschild §cdeaktiviert§7.");
                run.get(player).cancel();
                run.remove(player);
                event.getView().close();
            }
        }
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        for (final Player player : this.run.keySet()) {
            if (p != player && !p.hasPermission("lobbysystem.shield.bypass") && p.getLocation().distance(player.getLocation()) <= 4.0) {
                final double Ax = p.getLocation().getX();
                final double Ay = p.getLocation().getY();
                final double Az = p.getLocation().getZ();
                final double Bx = player.getLocation().getX();
                final double By = player.getLocation().getY();
                final double Bz = player.getLocation().getZ();
                final double x = Ax - Bx;
                final double y = Ay - By;
                final double z = Az - Bz;
                final Vector v = new Vector(x, y, z).normalize().multiply(1.0).setY(0.3);
                p.setVelocity(v);
            }
        }
        if (this.run.containsKey(p)) {
            for (final Entity entity : p.getNearbyEntities(4.0, 4.0, 4.0)) {
                if (entity instanceof Player) {
                    final Player target = (Player) entity;
                    if (p == target || target.hasPermission("lobbysystem.shield.bypass")) {
                        continue;
                    }
                    final double Ax2 = p.getLocation().getX();
                    final double Ay2 = p.getLocation().getY();
                    final double Az2 = p.getLocation().getZ();
                    final double Bx2 = target.getLocation().getX();
                    final double By2 = target.getLocation().getY();
                    final double Bz2 = target.getLocation().getZ();
                    final double x2 = Bx2 - Ax2;
                    final double y2 = By2 - Ay2;
                    final double z2 = Bz2 - Az2;
                    final Vector v2 = new Vector(x2, y2, z2).normalize().multiply(1.0).setY(0.3);
                    target.setVelocity(v2);
                }
            }
        }
    }
}
