package de.TntTastisch.BlackNerux.api.effects;

import de.TntTastisch.BlackNerux.CityBuildSystem;
import javafx.print.PageLayout;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class TpaEffect {

    public static float radius = 1;
    public static int particles = 20;
    public static HashMap<Player, BukkitRunnable> run = new HashMap<Player, BukkitRunnable>();
    public static float grow = .2f;
    public static int rings = 12;
    protected static int step = 0;

    public static void teleportationStart(final Player player) {
        run.put(player, new BukkitRunnable() {

            public void run() {
                Location location = player.getLocation();
                if (step > rings) {
                    step = 0;
                }
                double x, y, z;
                y = step * grow;
                location.add(0, y, 0);
                for (int i = 0; i < particles; i++) {
                    double angle = (double) 2 * Math.PI * i / particles;
                    x = Math.cos(angle) * radius;
                    z = Math.sin(angle) * radius;
                    location.add(x, 0, z);
                    player.playEffect(location, Effect.HAPPY_VILLAGER, particles);
                    location.subtract(x, 0, z);
                }
                location.subtract(0, y, 0);
                step++;
            }
        });
        run.get(player).runTaskTimer(CityBuildSystem.getInstance(), 1L, 1L);
    }

    /*
        run.get(player).cancel();
        run.remove(player);
     */
}
