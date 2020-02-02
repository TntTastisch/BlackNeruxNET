package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Msg_CMD implements CommandExecutor {

    public static HashMap<UUID, UUID> latestsent = new HashMap<UUID, UUID>();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (strings.length == 0 || strings.length == 1) {
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/msg <Spieler> <Nachricht>");
            } else if (strings.length >= 2) {
                Player target = Bukkit.getPlayer(strings[0]);
                String msg = "";
                for (int i = 1; i < strings.length; ++i) {
                    msg = String.valueOf(msg) + strings[i] + " ";
                }
                if (target != null) {
                    if (target != player) {
                        if(MySQL.getMsgEnable(target.getUniqueId().toString()) == 1) {
                            latestsent.put(player.getUniqueId(), target.getUniqueId());
                            latestsent.put(target.getUniqueId(), player.getUniqueId());

                            if (player.hasPermission("citybuild.command.msg.coloured")) {
                                player.sendMessage(Data.prefixNachrichten + "§cDu §7» §6" + Data.getPlayerPrefix(target) + " §7× §f" + ChatColor.translateAlternateColorCodes('&', msg));
                                target.sendMessage(Data.prefixNachrichten + "§6" + Data.getPlayerPrefix(player) + " §7» §cDir §7× §f" + ChatColor.translateAlternateColorCodes('&', msg));
                            } else {
                                player.sendMessage(Data.prefixNachrichten + "§cDu §7» §6" + Data.getPlayerPrefix(target) + " §7× §f" + msg);
                                target.sendMessage(Data.prefixNachrichten + "§6" + Data.getPlayerPrefix(player) + " §7» §cDir §7× §f" + msg);
                            }
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 120, 120);
                            target.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 120, 120);
                        } else {
                            player.sendMessage(Data.prefix + "§cDieser Spieler hat seine §6privaten Nachrichten §cdeaktiviert.");
                        }
                    } else {
                        player.sendMessage(Data.prefix + "§cDu kannst keine private Nachricht an dich selbst senden!");
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                }
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
