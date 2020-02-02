package de.TntTastisch.BlackNerux.utils;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    public static File locations = new File("plugins/CityBuildSystem/data", "location.yml");
    public static YamlConfiguration locationsCFG = YamlConfiguration.loadConfiguration(locations);

    public static void setSpawn(Player player){
        Location location = player.getLocation();

        locationsCFG.set("Spawn.X", location.getX());
        locationsCFG.set("Spawn.Y", location.getY());
        locationsCFG.set("Spawn.Z", location.getZ());
        locationsCFG.set("Spawn.Yaw", location.getYaw());
        locationsCFG.set("Spawn.Pitch", location.getPitch());
        locationsCFG.set("Spawn.World", location.getWorld().getName());

        try {
            locationsCFG.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSpawn(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationsCFG.getDouble("Spawn.X"));
            location.setY(locationsCFG.getDouble("Spawn.Y"));
            location.setZ(locationsCFG.getDouble("Spawn.Z"));
            location.setYaw((float) locationsCFG.getDouble("Spawn.Yaw"));
            location.setPitch((float) locationsCFG.getDouble("Spawn.Pitch"));
            location.setWorld(Bukkit.getWorld(locationsCFG.getString("Spawn.World")));

            player.teleport(location);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §cDer Spawn wurde noch nicht gesetzt. Bitte kontaktiere einen Administrator, das er diesen setzt!");
        }
    }
}
