package de.TntTastisch.BlackNerux.utils;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    public static File locations = new File(LobbySystem.getPlugin(LobbySystem.class).getDataFolder().getPath() + "/location.yml");
    public static YamlConfiguration locationcfg = YamlConfiguration.loadConfiguration(locations);


    public static void setSpawn(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("Spawn.X", location.getX());
        locationcfg.set("Spawn.Y", location.getY());
        locationcfg.set("Spawn.Z", location.getZ());
        locationcfg.set("Spawn.Yaw", location.getYaw());
        locationcfg.set("Spawn.Pitch", location.getPitch());
        locationcfg.set("Spawn.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSpawn(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("Spawn.X"));
            location.setY(locationcfg.getDouble("Spawn.Y"));
            location.setZ(locationcfg.getDouble("Spawn.Z"));
            location.setYaw((float) locationcfg.getDouble("Spawn.Yaw"));
            location.setPitch((float) locationcfg.getDouble("Spawn.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("Spawn.World")));
            player.teleport(location);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDer Spawn wurde noch gesetzt, kontaktiere einen Administrator, dass er den Spawn setzt.");
        }

    }

    public static void setF13(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("F13.X", location.getX());
        locationcfg.set("F13.Y", location.getY());
        locationcfg.set("F13.Z", location.getZ());
        locationcfg.set("F13.Yaw", location.getYaw());
        locationcfg.set("F13.Pitch", location.getPitch());
        locationcfg.set("F13.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getF13(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("F13.X"));
            location.setY(locationcfg.getDouble("F13.Y"));
            location.setZ(locationcfg.getDouble("F13.Z"));
            location.setYaw((float) locationcfg.getDouble("F13.Yaw"));
            location.setPitch((float) locationcfg.getDouble("F13.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("F13.World")));

            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDieser Spielmodus ist noch in Wartungsarbeiten!");
        }

    }


    public static void setWoK(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("WarOfKingdoms.X", location.getX());
        locationcfg.set("WarOfKingdoms.Y", location.getY());
        locationcfg.set("WarOfKingdoms.Z", location.getZ());
        locationcfg.set("WarOfKingdoms.Yaw", location.getYaw());
        locationcfg.set("WarOfKingdoms.Pitch", location.getPitch());
        locationcfg.set("WarOfKingdoms.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getWoK(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("WarOfKingdoms.X"));
            location.setY(locationcfg.getDouble("WarOfKingdoms.Y"));
            location.setZ(locationcfg.getDouble("WarOfKingdoms.Z"));
            location.setYaw((float) locationcfg.getDouble("WarOfKingdoms.Yaw"));
            location.setPitch((float) locationcfg.getDouble("WarOfKingdoms.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("WarOfKingdoms.World")));

            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDieser Spielmodus ist noch in Wartungsarbeiten!");
        }

    }
    public static void setCityBuild(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("CityBuild.X", location.getX());
        locationcfg.set("CityBuild.Y", location.getY());
        locationcfg.set("CityBuild.Z", location.getZ());
        locationcfg.set("CityBuild.Yaw", location.getYaw());
        locationcfg.set("CityBuild.Pitch", location.getPitch());
        locationcfg.set("CityBuild.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getCityBuild(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("CityBuild.X"));
            location.setY(locationcfg.getDouble("CityBuild.Y"));
            location.setZ(locationcfg.getDouble("CityBuild.Z"));
            location.setYaw((float) locationcfg.getDouble("CityBuild.Yaw"));
            location.setPitch((float) locationcfg.getDouble("CityBuild.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("CityBuild.World")));

            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDieser Spielmodus ist noch in Wartungsarbeiten!");
        }

    }

    public static void setLaserTag(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("LaserTag.X", location.getX());
        locationcfg.set("LaserTag.Y", location.getY());
        locationcfg.set("LaserTag.Z", location.getZ());
        locationcfg.set("LaserTag.Yaw", location.getYaw());
        locationcfg.set("LaserTag.Pitch", location.getPitch());
        locationcfg.set("LaserTag.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getLaserTag(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("LaserTag.X"));
            location.setY(locationcfg.getDouble("LaserTag.Y"));
            location.setZ(locationcfg.getDouble("LaserTag.Z"));
            location.setYaw((float) locationcfg.getDouble("LaserTag.Yaw"));
            location.setPitch((float) locationcfg.getDouble("LaserTag.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("LaserTag.World")));

            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDieser Spielmodus ist noch in Wartungsarbeiten!");
        }

    }

    public static void setEscapeTheDemons(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("EscapeTheDemons.X", location.getX());
        locationcfg.set("EscapeTheDemons.Y", location.getY());
        locationcfg.set("EscapeTheDemons.Z", location.getZ());
        locationcfg.set("EscapeTheDemons.Yaw", location.getYaw());
        locationcfg.set("EscapeTheDemons.Pitch", location.getPitch());
        locationcfg.set("EscapeTheDemons.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getEscapeTheDemons(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("EscapeTheDemons.X"));
            location.setY(locationcfg.getDouble("EscapeTheDemons.Y"));
            location.setZ(locationcfg.getDouble("EscapeTheDemons.Z"));
            location.setYaw((float) locationcfg.getDouble("EscapeTheDemons.Yaw"));
            location.setPitch((float) locationcfg.getDouble("EscapeTheDemons.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("EscapeTheDemons.World")));

            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDieser Spielmodus ist noch in Wartungsarbeiten!");
        }

    }

    public static void setFiveNightsAtMinecraft(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("FiveNightsAtMinecraft.X", location.getX());
        locationcfg.set("FiveNightsAtMinecraft.Y", location.getY());
        locationcfg.set("FiveNightsAtMinecraft.Z", location.getZ());
        locationcfg.set("FiveNightsAtMinecraft.Yaw", location.getYaw());
        locationcfg.set("FiveNightsAtMinecraft.Pitch", location.getPitch());
        locationcfg.set("FiveNightsAtMinecraft.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFiveNightsAtMinecraft(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("FiveNightsAtMinecraft.X"));
            location.setY(locationcfg.getDouble("FiveNightsAtMinecraft.Y"));
            location.setZ(locationcfg.getDouble("FiveNightsAtMinecraft.Z"));
            location.setYaw((float) locationcfg.getDouble("FiveNightsAtMinecraft.Yaw"));
            location.setPitch((float) locationcfg.getDouble("FiveNightsAtMinecraft.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("FiveNightsAtMinecraft.World")));

            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDieser Spielmodus ist noch in Wartungsarbeiten!");
        }

    }

    public static void setOneHit(Player player){
        org.bukkit.Location location = player.getLocation();


        locationcfg.set("OneHit.X", location.getX());
        locationcfg.set("OneHit.Y", location.getY());
        locationcfg.set("OneHit.Z", location.getZ());
        locationcfg.set("OneHit.Yaw", location.getYaw());
        locationcfg.set("OneHit.Pitch", location.getPitch());
        locationcfg.set("OneHit.World", location.getWorld().getName());

        try {
            locationcfg.save(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getOneHit(Player player){
        try {
            Location location = player.getLocation();

            location.setX(locationcfg.getDouble("OneHit.X"));
            location.setY(locationcfg.getDouble("OneHit.Y"));
            location.setZ(locationcfg.getDouble("OneHit.Z"));
            location.setYaw((float) locationcfg.getDouble("OneHit.Yaw"));
            location.setPitch((float) locationcfg.getDouble("OneHit.Pitch"));
            location.setWorld(Bukkit.getWorld(locationcfg.getString("OneHit.World")));

            player.teleport(location);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,1,1);
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } catch (Exception e){
            player.sendMessage(Data.prefix + "§4§lACHTUNG! §c§lDieser Spielmodus ist noch in Wartungsarbeiten!");
        }

    }
}
