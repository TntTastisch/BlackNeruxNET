package de.TntTastisch.BlackNerux.listener;

import org.bukkit.Location;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class EnterhakenListener implements Listener {

    @org.bukkit.event.EventHandler
    public void onFish(PlayerFishEvent event)
    {
        Player player = event.getPlayer();
        Fish h = event.getHook();

        if ((event.getState() == PlayerFishEvent.State.IN_GROUND) ||
                (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) ||
                (event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)))
        {


            if (org.bukkit.Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ()).getType() != org.bukkit.Material.AIR) {
                Location lc = player.getLocation();
                Location to = event.getHook().getLocation();

                lc.setY(lc.getY() + 0.8D);
                player.teleport(lc);
                player.playSound(player.getLocation(), org.bukkit.Sound.WOOD_CLICK, 3.0F, 2.0F);

                double g = -0.08D;
                double d = to.distance(lc);
                double t = d;
                double v_x = (1.0D + 0.07D * t) * (to.getX() - lc.getX()) / t;
                double v_y = (1.0D + 0.03D * t) * (to.getY() - lc.getY()) / t - 0.5D * g * t;
                double v_z = (1.0D + 0.07D * t) * (to.getZ() - lc.getZ()) / t;

                Vector v = player.getVelocity();
                v.setX(v_x);
                v.setY(v_y);
                v.setZ(v_z);
                player.setVelocity(v);
            }
        }
    }


}
