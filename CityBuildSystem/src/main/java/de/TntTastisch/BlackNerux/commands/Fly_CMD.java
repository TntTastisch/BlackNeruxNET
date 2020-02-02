package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fly_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.fly")){
                if(strings.length == 0){
                    if (MySQL.getFly(player.getUniqueId().toString()) == 0) {
                        MySQL.setFly(player.getUniqueId().toString(), 1);
                        player.sendMessage(Data.prefix + "§7Du hast deinen §6Flugmodus §aaktiviert§7.");
                        player.setAllowFlight(true);
                    } else if (MySQL.getFly(player.getUniqueId().toString()) == 1) {
                        MySQL.setFly(player.getUniqueId().toString(), 0);
                        player.sendMessage(Data.prefix + "§7Du hast deinen §6Flugmodus §cdeaktiviert§7.");
                        player.setAllowFlight(false);
                    }
                } else if(strings.length == 1){
                    Player target = Bukkit.getPlayer(strings[0]);

                    if(target != null){
                        if(target != player){
                            if (MySQL.getFly(target.getUniqueId().toString()) == 0) {
                                MySQL.setFly(target.getUniqueId().toString(), 1);

                                player.sendMessage(Data.prefix + "§7Du hast den §6Flugmodus §7von " + Data.getPlayerPrefix(target) + " §aaktiviert§7.");
                                target.sendMessage(Data.prefix + "§7Dein §6Flugmodus §7wurde von " + Data.getPlayerPrefix(player) + " §aaktiviert §7.");

                                target.setAllowFlight(true);
                            } else if (MySQL.getFly(target.getUniqueId().toString()) == 1) {
                                MySQL.setFly(target.getUniqueId().toString(), 0);

                                player.sendMessage(Data.prefix + "§7Du hast den §6Flugmodus §7von " + Data.getPlayerPrefix(target) + " §cdeaktiviert§7.");
                                target.sendMessage(Data.prefix + "§7Dein §6Flugmodus §7wurde von " + Data.getPlayerPrefix(player) + " §cdeaktiviert §7.");

                                target.setAllowFlight(false);
                            }

                        } else {
                            if (MySQL.getFly(player.getUniqueId().toString()) == 0) {
                                MySQL.setFly(player.getUniqueId().toString(), 1);
                                player.sendMessage(Data.prefix + "§7Du hast deinen §6Flugmodus §aaktiviert§7.");
                                player.setAllowFlight(true);
                            } else if (MySQL.getFly(player.getUniqueId().toString()) == 1) {
                                MySQL.setFly(player.getUniqueId().toString(), 0);
                                player.sendMessage(Data.prefix + "§7Du hast deinen §6Flugmodus §cdeaktiviert§7.");
                                player.setAllowFlight(false);
                            }
                        }
                    } else {
                        player.sendMessage(Data.prefix +"§cDieser Spieler ist Offline!");
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
