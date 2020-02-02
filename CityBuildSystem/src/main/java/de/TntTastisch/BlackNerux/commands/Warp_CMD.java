package de.TntTastisch.BlackNerux.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Warp_CMD implements CommandExecutor {

    public static File warps = new File("plugins/CityBuildSystem/data", "warps.yml");
    public static YamlConfiguration warpsCFG = YamlConfiguration.loadConfiguration(warps);

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
