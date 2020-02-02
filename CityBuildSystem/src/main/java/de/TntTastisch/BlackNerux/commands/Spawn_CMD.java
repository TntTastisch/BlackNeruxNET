package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;

public class Spawn_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            LocationManager.getSpawn(player);
            player.sendMessage(Data.prefix +"§7Du wurdest zum §6Spawn §ateleportiert§7.");
            player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 600);

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
