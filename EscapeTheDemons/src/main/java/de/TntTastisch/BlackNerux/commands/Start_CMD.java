package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.listener.JoinQuitListener;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Start_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("escapethedemons.vip.start")){
                if(JoinQuitListener.timer != 10){
                    JoinQuitListener.timer = 10;
                    player.sendMessage(Data.prefix + "§7Du hast den Lobby Countdown verkürzt!");
                } else if(JoinQuitListener.timer <= 10){
                    player.sendMessage(Data.prefix + "§cDas Spiel startet bereits!");
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
