package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ETD_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("system.gamemode.etd.admin")){

                if(strings.length == 0){

                }


            } else {
                player.sendMessage(Data.noPerms);
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }

        return false;
    }
}
