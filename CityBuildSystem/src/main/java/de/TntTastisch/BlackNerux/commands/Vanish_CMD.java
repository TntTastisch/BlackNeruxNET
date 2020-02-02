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

public class Vanish_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.vanish")){
                if(strings.length == 0) {
                    if (MySQL.getVanish(player.getUniqueId().toString()) == 0) {
                        MySQL.setVanish(player.getUniqueId().toString(), 1);
                        player.sendMessage(Data.prefix + "§7Du hast deinen §6Vanishmodus §aaktiviert§7.");

                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3600000, 360000));

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("citybuild.command.vanish.bypass") || !all.hasPermission("citybuild.command.vanish")) {
                                all.hidePlayer(player);
                            }
                        }

                    } else if (MySQL.getVanish(player.getUniqueId().toString()) == 1) {
                        MySQL.setVanish(player.getUniqueId().toString(), 0);
                        player.sendMessage(Data.prefix + "§7Du hast deinen §6Vanishmodus §cdeaktiviert§7.");
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);


                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("citybuild.command.vanish.bypass") || !all.hasPermission("citybuild.command.vanish")) {
                                all.showPlayer(player);
                            }
                        }
                    }
                } else if(strings.length == 1){

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
