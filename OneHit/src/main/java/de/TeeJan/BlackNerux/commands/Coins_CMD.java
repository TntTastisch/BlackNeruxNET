package de.TeeJan.BlackNerux.commands;

import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Coins_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){
                player.sendMessage(Data.prefix + "§7Deine Coins §8× §a" + MySQL.getCoins(player.getUniqueId().toString()));
            } else if(strings.length == 1){
                Player target = Bukkit.getPlayer(strings[0]);

                if(player.hasPermission("onehit.command.coins.other")){
                    if(target != null){
                        if(target != player){
                            player.sendMessage(Data.prefix + Data.getPlayerPrefix(target)  + "'s §7Coins §8× §a" + MySQL.getCoins(target.getUniqueId().toString()));
                        } else {
                            player.sendMessage(Data.prefix + "§7Deine Coins §8× §a" + MySQL.getCoins(player.getUniqueId().toString()));
                        }
                    } else {
                        player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                    }
                } else {
                    player.sendMessage(Data.prefix + "§7Deine Coins §8× §a" + MySQL.getCoins(player.getUniqueId().toString()));
                }
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }

        return false;
    }
}
