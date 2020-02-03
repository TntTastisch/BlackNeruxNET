package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerTime_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){
                player.sendMessage(Data.prefix +"§7Benutze §8× §e/ptime <Zeit | night,day>");
            } else if(strings.length == 1){

                if(strings[0].equalsIgnoreCase("day")){
                    player.setPlayerTime(1000, true);
                    player.sendMessage(Data.prefix + "§7Du hast deine §6Tageszeit §7auf §c7:00 Uhr §7gesetzt!");

                }

                if(strings[0].equalsIgnoreCase("night")){
                    player.setPlayerTime(14000, true);
                    player.sendMessage(Data.prefix + "§7Du hast deine §6Tageszeit §7auf §c20:00 Uhr §7gesetzt!");

                }

            } else if(strings.length == 1){
                long ticks = Long.valueOf(strings[0]);

                player.setPlayerTime(ticks, true);
                player.sendMessage(Data.prefix + "§7Du hast deine §6Tageszeit §7auf §c" + ticks + " Ticks §7gesetzt!");

            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
