package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.invsee")) {
                if (strings.length == 0) {

                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/invsee <Spieler>");

                } else if (strings.length == 1) {
                    Player target = Bukkit.getPlayer(strings[0]);

                    if (target != null) {
                        if (target != player) {

                            player.openInventory(target.getInventory());
                            player.sendMessage(Data.prefix + "§7Du hast das §6Inventar §7von §6" + Data.getPlayerPrefix(target) + " §ageöffnet7.");

                        } else {
                            player.sendMessage(Data.prefix + "§cDu kannst nicht dein eigenes Iventar öffnen!");
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
