package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Enderchest_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){

                player.openInventory(player.getEnderChest());
                player.sendMessage(Data.prefix + "§7Du hast deine §6Enderchest §ageöffnet§7.");

            } else if(strings.length == 1){
                Player target = Bukkit.getPlayer(strings[0]);

                if(target != null){
                    if(target != player){

                        player.openInventory(target.getEnderChest());
                        player.sendMessage(Data.prefix + "§7Du hast die §6Enderchest §7von §6" + Data.getPlayerPrefix(target) + " §ageöffnet7.");

                    } else {
                        player.performCommand("enderchest");
                    }
                } else {
                    player.performCommand("enderchest");
                }
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
