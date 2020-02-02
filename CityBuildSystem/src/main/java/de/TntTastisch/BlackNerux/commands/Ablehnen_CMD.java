package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.CityBuildSystem;
import de.TntTastisch.BlackNerux.api.effects.TpaEffect;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ablehnen_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (strings.length == 0) {
                final Player target = Tpa_CMD.tpa.get(player);

                if (Tpa_CMD.tpa.containsKey(player)) {
                    if (Tpa_CMD.tpa.containsKey(target)) {
                        player.sendMessage(Data.prefix + "§7Du hast die TPA-Anfrage von §6" + Data.getPlayerPrefix(target) + " §cabgelehnt§7.");
                        target.sendMessage(Data.prefix + Data.getPlayerPrefix(player) + " §7hat deine TPA-Anfrage §cabgelehnt§7.");
                        Tpa_CMD.tpa.remove(player, target);
                        Tpa_CMD.tpa.remove(target, player);
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keine TPA-Anfrage erhalten!");
                }

            /*} else if(strings.length == 0){
                final Player target = Tpahere_CMD.TPAHere.get(player);

                if (Tpahere_CMD.TPAHere.containsKey(player)) {
                    if (Tpahere_CMD.TPAHere.containsKey(target)) {
                        player.sendMessage(Data.prefix + "§7Du hast die TPA-Anfrage von §6" + Data.getPlayerPrefix(target) + " §cabgelehnt§7.");
                        target.sendMessage(Data.prefix + Data.getPlayerPrefix(player) + " §7hat deine TPA-Anfrage §cabgelehnt§7.");
                        Tpahere_CMD.TPAHere.remove(player, target);
                        Tpahere_CMD.TPAHere.remove(target, player);
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keine TPA-Anfrage erhalten!");
                }
            } else if(strings.length == 0){
                final Player target = Tpaall_CMD.TpaALL.get(player);

                if (Tpaall_CMD.TpaALL.containsKey(player)) {
                    if (Tpaall_CMD.TpaALL.containsKey(target)) {
                        player.sendMessage(Data.prefix + "§7Du hast die TPA-Anfrage von §6" + Data.getPlayerPrefix(target) + " §cabgelehnt§7.");
                        Tpaall_CMD.TpaALL.remove(player, target);
                        Tpaall_CMD.TpaALL.remove(target, player);
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keine TPA-Anfrage erhalten!");
                }

                */

            } else if (strings.length >= 1) {
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/ablehnen");
            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
