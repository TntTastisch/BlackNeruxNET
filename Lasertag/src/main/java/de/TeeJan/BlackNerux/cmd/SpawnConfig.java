package de.TeeJan.BlackNerux.cmd;

import de.TeeJan.BlackNerux.systems.Data;
import javafx.scene.shape.Path;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static de.TeeJan.BlackNerux.Teams.Teams.cfg;

public class SpawnConfig {

    public static void setLocation(Player p, YamlConfiguration cgf, File file, String patch) {

        double x = p.getLocation().getX();
        double y = p.getLocation().getY();
        double z = p.getLocation().getZ();

        float Yaw = p.getLocation().getYaw();
        float pitch = p.getLocation().getPitch();

        String world = p.getLocation().getWorld().getName();

        cfg.set("lobby." + patch + ".X", x);
        cfg.set("lobby." + patch + ".Y", y);
        cfg.set("lobby." + patch + ".Z", z);
        cfg.set("lobby." + patch + ".Yaw", Yaw);
        cfg.set("lobby." + patch + ".Pitch", pitch);
        cfg.set("lobby." + patch + ".World", world);

        try {
            cfg.save(file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(Data.prefix + "§4Datei konnte nicht gespeichert werden! §bConfig");


        }
    }

    public static void teleportPlayerToLocation(Player p, YamlConfiguration cfg, String path) {

        double X = cfg.getDouble("lobby." + path + ".X");
        double Y = cfg.getDouble("lobby." + path + ".Y");
        double Z = cfg.getDouble("lobby." + path + ".Z");

        float Yaw = (float) cfg.getDouble("lobby." + ".Yaw");
        float Pitch = (float) cfg.getDouble("lobby." + ".Pitch");

        World World = Bukkit.getWorld(cfg.getString("lobby." + path + ".World"));

        Location loc = new Location(World, X, Y, Z, Yaw, Pitch);

        p.teleport(loc);
    }
}
