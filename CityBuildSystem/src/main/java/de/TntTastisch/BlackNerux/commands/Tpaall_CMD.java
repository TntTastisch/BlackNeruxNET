package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Tpaall_CMD implements CommandExecutor {

    public static HashMap<Player, Player> TpaALL = new HashMap<>();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.tpaall")) {
                if (strings.length == 0) {
                    player.sendMessage(Data.prefix + "§cDerzeitig deaktiviert!");
                    /*
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if(all != player) {
                            if (MySQL.getTpaToggle(all.getUniqueId().toString()) == 1) {
                                TpaALL.put(player, all);
                                TpaALL.put(all, player);


                                all.sendMessage(Data.prefix + Data.getPlayerPrefix(player) + " §7möchte sich zu ihm teleportieren.");
                                all.sendMessage(Data.prefix + "§7Benutze §a/annehmen§7, um anzunehmen oder §c/ablehnen§7, um abzulehnen.");


                            } else if (MySQL.getTpaToggle(all.getUniqueId().toString()) == 0) {
                            }
                        }
                    }
                    player.sendMessage(Data.prefix + "§7Du hast an alle Spieler eine §cTPA-Anfrage §6gesendet§7.");

                 */
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
