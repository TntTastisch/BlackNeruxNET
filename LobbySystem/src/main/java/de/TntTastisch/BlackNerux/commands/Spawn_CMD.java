package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn_CMD implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){

            LocationManager.getSpawn((Player) commandSender);

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
