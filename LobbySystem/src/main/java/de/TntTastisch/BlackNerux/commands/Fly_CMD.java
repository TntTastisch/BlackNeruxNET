package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

public class Fly_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("lobbysystem.fly")){

                if(MySQL.getFlyTool(player.getUniqueId().toString()) == 0){
                    MySQL.setFlyTool(player.getUniqueId().toString(), 1);
                    PlayerInventory.playerInventory(player);
                    player.sendMessage(Data.prefix + "§c§lFlugmodus §aaktiviert§7.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                    player.setAllowFlight(true);
                } else if(MySQL.getFlyTool(player.getUniqueId().toString()) == 1){
                    MySQL.setFlyTool(player.getUniqueId().toString(), 0);
                    PlayerInventory.playerInventory(player);
                    player.sendMessage(Data.prefix + "§c§lFlugmodus §cdeaktiviert§7.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                    player.setAllowFlight(false);
                }

            } else {
                player.sendMessage(Data.noPermission);
            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
