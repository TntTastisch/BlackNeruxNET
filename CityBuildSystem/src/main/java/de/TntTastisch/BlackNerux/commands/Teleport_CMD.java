package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.teleport")){

                if(strings.length == 0){
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/teleport <Spieler> | <X> <Y> <Z> oder §e/tp <Spieler> | <X> <Y> <Z> oder /tp <X> <Y> <Z> <Yaw> <Pitch> und" +
                            "/teleport <X> <Y> <Z> <Yaw> <Pitch>");
                } else if(strings.length == 1){
                    Player target = Bukkit.getPlayer(strings[0]);

                    if(target != null){
                        if(target != player){

                            player.teleport(target);
                            player.sendMessage(Data.prefix +"§7Du hast dich zu §6" + Data.getPlayerPrefix(target) + " §ateleportiert§7.");

                        } else {
                            player.sendMessage(Data.prefix + "§cDu kannst dich nicht zu dir selbst teleportieren!");
                        }
                    } else {
                        player.sendMessage(Data.prefix +"§cDieser Spieler ist Offline!");
                    }
                } else if(strings.length == 3){
                    try {
                        Location location = player.getLocation();

                        location.setX(Double.valueOf(strings[0]));
                        location.setY(Double.valueOf(strings[1]));
                        location.setZ(Double.valueOf(strings[2]));
                        location.setWorld(player.getWorld());

                        player.teleport(location);
                        player.sendMessage(Data.prefix + "§7Du hast dich zu §e" + strings[0] + ", " + strings[1] + ", " + strings[2] + " §ateleportiert§7.");
                    } catch (NumberFormatException e){
                        return false;
                    }
                } else if(strings.length == 5){
                    try {
                        Location location = player.getLocation();

                        location.setX(Double.valueOf(strings[0]));
                        location.setY(Double.valueOf(strings[1]));
                        location.setZ(Double.valueOf(strings[2]));
                        location.setYaw(Float.valueOf(strings[3]));
                        location.setPitch(Float.valueOf(strings[4]));
                        location.setWorld(player.getWorld());

                        player.teleport(location);
                        player.sendMessage(Data.prefix + "§7Du hast dich zu §e" + strings[0] + ", " + strings[1] + ", " + strings[2] + ", " + strings[3] + ", " + strings[4] + " §ateleportiert§7.");
                    } catch (NumberFormatException e){
                        return false;
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
