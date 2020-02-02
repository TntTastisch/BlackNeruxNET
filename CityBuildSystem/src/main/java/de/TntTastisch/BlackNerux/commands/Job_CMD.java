package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.listeners.JobsListener;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.JobNPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Job_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){
                JobsListener.JobInventory(player);
            } else if(strings.length >= 1){
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/jobs");
            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
