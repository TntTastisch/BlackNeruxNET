package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Set_CMD implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("lobbysystem.set")){

                if(strings.length == 0) {

                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/set <Spawn, F13, WoK, LaserTag, EscapeTheDemons, FiveNightsAtMinecraft, OneHit>");

                } else if(strings.length == 1){

                    if(strings[0].equalsIgnoreCase("F13")){
                        LocationManager.setF13(player);
                        player.sendMessage(Data.prefix + "§7Du hast den §6Spawn von §4Friday the 13th §aerfolgreich §7gesetzt");
                    }

                    if(strings[0].equalsIgnoreCase("OneHit")){
                        LocationManager.setOneHit(player);
                        player.sendMessage(Data.prefix + "§7Du hast den §6Spawn von §5OneHit §aerfolgreich §7gesetzt");
                    }

                    if(strings[0].equalsIgnoreCase("Spawn")){
                        LocationManager.setSpawn(player);
                        player.sendMessage(Data.prefix + "§7Du hast den §6Spawn §aerfolgreich §7gesetzt");
                    }

                    if(strings[0].equalsIgnoreCase("Wok")){
                        LocationManager.setWoK(player);
                        player.sendMessage(Data.prefix + "§7Du hast den §6Spawn von §bWar of Kingdoms §aerfolgreich §7gesetzt");
                    }

                    if(strings[0].equalsIgnoreCase("LaserTag")){
                        LocationManager.setLaserTag(player);
                        player.sendMessage(Data.prefix + "§7Du hast den §6Spawn von §bLaserTag §aerfolgreich §7gesetzt");
                    }

                    if(strings[0].equalsIgnoreCase("EscapeTheDemons")){
                        LocationManager.setEscapeTheDemons(player);
                        player.sendMessage(Data.prefix + "§7Du hast den §6Spawn von §bEscapeTheDemons §aerfolgreich §7gesetzt");
                    }

                    if(strings[0].equalsIgnoreCase("FiveNightsAtMinecraft")){
                        LocationManager.setFiveNightsAtMinecraft(player);
                        player.sendMessage(Data.prefix + "§7Du hast den §6Spawn von §bFiveNightsAtMinecraft §aerfolgreich §7gesetzt");
                    }
                }

            } else {
                player.sendMessage(Data.noPermission);
            }
        } else {
            commandSender.sendMessage(Data.noPermission);
        }
        return false;
    }
}
