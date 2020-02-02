package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;

public class MsgToggle_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(MySQL.getMsgEnable(player.getUniqueId().toString()) == 1){
                MySQL.setMsgEnable(player.getUniqueId().toString(), 0);
                player.sendMessage(Data.prefix + "§7Du hast deine §6privaten Nachrichten §cdeaktiviert.");
            } else if(MySQL.getMsgEnable(player.getUniqueId().toString()) == 0){
                MySQL.setMsgEnable(player.getUniqueId().toString(), 1);
                player.sendMessage(Data.prefix + "§7Du hast deine §6privaten Nachrichten §aaktiviert.");
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
