package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.feed")){
                if(strings.length == 0){
                    player.setFoodLevel(20);
                    player.sendMessage(Data.prefix + "§7Du wurdest gefüttert.");
                } else if(strings.length == 1){
                    Player target = Bukkit.getPlayer(strings[0]);

                    if(target != null){
                        if(target != player){

                            target.setFoodLevel(20);
                            target.sendMessage(Data.prefix + "§7Du wurdest von §6" + Data.getPlayerPrefix(player) + " §7gefüttert.");
                            player.sendMessage(Data.prefix + "§7Du wurdest hast §6" + Data.getPlayerPrefix(target) + " §7gefüttert.");

                        } else {
                            player.setFoodLevel(20);
                            player.sendMessage(Data.prefix + "§7Du wurdest gefüttert.");
                        }
                    } else {
                        player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                    }

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
