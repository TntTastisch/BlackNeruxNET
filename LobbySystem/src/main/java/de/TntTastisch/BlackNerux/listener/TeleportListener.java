package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.utils.LocationManager;
import net.minecraft.server.v1_8_R3.Blocks;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.swing.*;

public class TeleportListener implements Listener {

    /*
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if (player.getLocation().getBlock().getType() == Material.WATER || player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
            LocationManager.getSpawn(player);
        }

        }

     */

    @EventHandler
    public void onDamage(EntityDamageEvent event){

        if(event.getEntity() instanceof Player){
            if(event.getCause() == EntityDamageEvent.DamageCause.VOID){
                LocationManager.getSpawn(((Player) event.getEntity()).getPlayer());
            }
        }
    }
}
