package de.TntTastisch.BlackNerux.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class Home_CMD implements CommandExecutor {

    public static File homes = new File("plugins/CityBuildSystem/data", "homes.yml");
    public static YamlConfiguration homesCFG = YamlConfiguration.loadConfiguration(homes);

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }



    public static void setConfigOnJoin(Player player){

        homesCFG.set(player.getUniqueId().toString() + ".Name" , player.getName());
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "1" + ".X" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "1" + ".Y" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "1" + ".Z" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "1" + ".Yaw" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "1" + ".Pitch" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "1" + ".World" , "");

        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "2" + ".X" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "2" + ".Y" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "2" + ".Z" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "2" + ".Yaw" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "2" + ".Pitch" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "2" + ".World" , "");

        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "3" + ".X" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "3" + ".Y" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "3" + ".Z" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "3" + ".Yaw" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "3" + ".Pitch" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "3" + ".World" , "");

        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "4" + ".X" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "4" + ".Y" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "4" + ".Z" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "4" + ".Yaw" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "4" + ".Pitch" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "4" + ".World" , "");

        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "5" + ".X" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "5" + ".Y" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "5" + ".Z" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "5" + ".Yaw" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "5" + ".Pitch" , "");
        homesCFG.set(player.getUniqueId().toString() + ".Homes." + "5" + ".World" , "");

        try {
            homesCFG.save(homes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
