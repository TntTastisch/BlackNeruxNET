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

public class Heal_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.heal")){
                if(strings.length == 0){
                    player.setHealth(20);
                    player.sendMessage(Data.prefix + "§7Du wurdest geheilt.");
                } else if(strings.length == 1){
                    Player target = Bukkit.getPlayer(strings[0]);

                    if(target != null){
                        if(target != player){

                            target.setHealth(20);
                            target.sendMessage(Data.prefix + "§7Du wurdest von §6" + Data.getPlayerPrefix(player) + " §7geheilt.");
                            player.sendMessage(Data.prefix + "§7Du wurdest hast §6" + Data.getPlayerPrefix(target) + " §7geheilt.");

                        } else {
                            player.setHealth(20);
                            player.sendMessage(Data.prefix + "§7Du wurdest geheilt.");
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
