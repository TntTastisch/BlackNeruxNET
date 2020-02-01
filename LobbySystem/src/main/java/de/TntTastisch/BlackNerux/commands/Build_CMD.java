package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Build_CMD implements CommandExecutor {


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if(player.hasPermission("lobbysystem.build")){

                if(Data.build.contains(player)){
                    Data.build.remove(player);

                    player.getInventory().clear();
                    PlayerInventory.playerInventory(player);
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND,1,1);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(Data.prefix + "§3Baumodus §cdeaktiviert§7.");
                } else {
                    Data.build.add(player);

                    player.getInventory().clear();
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND,1,1);
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(Data.prefix + "§3Baumodus §aaktiviert§7.");
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
