package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaToggle_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(MySQL.getTpaToggle(player.getUniqueId().toString()) == 0){
                MySQL.setTpaToggle(player.getUniqueId().toString(), 1);
                player.sendMessage(Data.prefix + "§7Du hast deine §6TPA-Anfragen §aakiviviert§7.");
            } else if(MySQL.getTpaToggle(player.getUniqueId().toString()) == 1){
                MySQL.setTpaToggle(player.getUniqueId().toString(), 0);
                player.sendMessage(Data.prefix + "§7Du hast deine §6TPA-Anfragen §cdeakiviviert§7.");
            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
