package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tphere_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.tphere")){

                if(strings.length == 0){
                    player.sendMessage(Data.prefix +"§7Benutze §8× §e/tphere <Spieler>");
                } else if(strings.length == 1){
                    Player target = Bukkit.getPlayer(strings[0]);

                    if(target != null){
                        if(target != player){

                            player.sendMessage(Data.prefix + "§7Du hast §6" + Data.getPlayerPrefix(target) + " §7zu dir §ateleportiert§7.");
                            target.sendMessage(Data.prefix + "§7Du wurdest zu §6" + Data.getPlayerPrefix(player) + " §ateleportiert§7.");
                            target.teleport(player);

                        } else {
                            player.sendMessage(Data.prefix + "§cDu kannst dich nicht selbst zu sich teleportieren.");
                        }
                    } else {
                        player.sendMessage(Data.prefix +"§cDieser Spieler ist Offline!");
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
