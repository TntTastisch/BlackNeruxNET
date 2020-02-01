package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import javax.swing.*;

public class DoubleJumpListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlightEvent(final PlayerToggleFlightEvent ev) {
        final Player p = ev.getPlayer();
        if (p.hasPermission("lobbysystem.premium.doublejump") && p.getGameMode() == GameMode.SURVIVAL && MySQL.getFlyTool(ev.getPlayer().getUniqueId().toString()) == 0) {
            ev.setCancelled(true);
            p.setFlying(false);
            p.setAllowFlight(false);
            p.setVelocity(p.getLocation().getDirection().multiply(2.5D).add(new Vector(0, 1, 0)));
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS,1,1);
        }
    }

    @EventHandler
    public void onPlayerMoveEvent(final PlayerMoveEvent ev) {
        final Player p = ev.getPlayer();
        if (p.hasPermission("lobbysystem.premium.doublejump") && p.getGameMode() == GameMode.SURVIVAL && p.getLocation().add(0.0, -1.0, 0.0).getBlock().getType() != Material.AIR && MySQL.getFlyTool(ev.getPlayer().getUniqueId().toString()) == 0) {
            p.setFlying(false);
            p.setAllowFlight(true);
        }
    }
}
