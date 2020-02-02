package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class R_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if (strings.length == 0) {
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/r <Nachricht>");
            } else if (strings.length >= 1) {
                String message = "";
                for (int i = 0; i < strings.length; i++) {
                    message = message + strings[i] + " ";
                }

                if (Msg_CMD.latestsent.containsKey(player.getUniqueId())) {
                    final Player target = Bukkit.getPlayer((UUID) Msg_CMD.latestsent.get(player.getUniqueId()));

                    if (target != null) {
                        String msg = "";
                        for (int i = 0; i < strings.length; ++i) {
                            msg = String.valueOf(msg) + strings[i] + " ";
                        }
                        Msg_CMD.latestsent.put(target.getUniqueId(), player.getUniqueId());

                        if(player.hasPermission("citybuild.command.msg.coloured")){
                            player.sendMessage(Data.prefixNachrichten + "§cDu §7» §6" + Data.getPlayerPrefix(target) + " §7× §f" + ChatColor.translateAlternateColorCodes('&', msg));
                            target.sendMessage(Data.prefixNachrichten + "§6" + Data.getPlayerPrefix(player) + " §7» §cDir §7× §f" + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            player.sendMessage(Data.prefixNachrichten + "§cDu §7» §6" + Data.getPlayerPrefix(target) + " §7× §f" + msg);
                            target.sendMessage(Data.prefixNachrichten + "§6" + Data.getPlayerPrefix(player) + " §7» §cDir §7× §f" + msg);
                        }
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 120, 120);
                        target.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 120, 120);

                    } else {
                        player.sendMessage(Data.prefix + " §cDieser Spieler ist offline.");
                    }
                } else {
                    player.sendMessage(Data.prefixNachrichten + " §cDu hast niemanden per MSG geschrieben.");
                }
            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
