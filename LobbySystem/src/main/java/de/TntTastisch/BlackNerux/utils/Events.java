package de.TntTastisch.BlackNerux.utils;

import de.TntTastisch.BlackNerux.LobbySystem;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Events {

    private static void winterEvents(Player player, Location location, float speed, int amount){
        final EnumParticle enumParticle = EnumParticle.FIREWORKS_SPARK;
        final PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(enumParticle, true, (float)location.getX(),
                (float)location.getY(), (float)location.getZ(), 20.0f, 20.0f, 20.0f,speed,amount, (int[])null);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void startSnowParticles() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(LobbySystem.getPlugin(LobbySystem.class),
                () -> Bukkit.getOnlinePlayers().forEach(target -> winterEvents(target, target.getLocation(), 0.0f, 100)), 1,1);
    }
}
