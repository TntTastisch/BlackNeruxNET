package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SocialSpy_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.socialspy")){
                if(MySQL.getSocialSpy(player.getUniqueId().toString()) == 0){
                    MySQL.setSocialSpy(player.getUniqueId().toString(), 1);
                    player.sendMessage(Data.prefix + "§7Du hast §6SocialSpy §aaktiviert§7.");
                } else if(MySQL.getSocialSpy(player.getUniqueId().toString()) == 1){
                    MySQL.setSocialSpy(player.getUniqueId().toString(), 0);
                    player.sendMessage(Data.prefix + "§7Du hast §6SocialSpy §cdeaktiviert§7.");
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
