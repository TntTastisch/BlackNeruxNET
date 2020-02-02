package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpall_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.tpall")) {

                player.sendMessage(Data.prefix + "§7Du hast alle Spieler zu dir §ateleportiert§7.");

                for(Player all : Bukkit.getOnlinePlayers()){
                    if(all != player) {
                        all.teleport(player);
                        all.sendMessage(Data.prefix + "§7Du wurdest zu §6" + Data.getPlayerPrefix(player) + " §ateleportiert§7.");
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
