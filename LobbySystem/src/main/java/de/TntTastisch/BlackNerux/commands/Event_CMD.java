package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;

public class Event_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("system.lobbysystem.event")){

            String EventType = LobbySystem.getPlugin(LobbySystem.class).configuration.getString("Events.type");

            if(strings.length == 0){
                commandSender.sendMessage(Data.prefix + "§7Benutze §8× §e/event get/set <Silvester,Weihnachten,Halloween, Oster, null>");
            } else if(strings.length == 1){

                if(strings[0].equalsIgnoreCase("get")){
                    commandSender.sendMessage(Data.prefix + "§7Derzeit laufendes Event §8× §e" + EventType);
                }

                if(strings[0].equalsIgnoreCase("set")){
                    commandSender.sendMessage(Data.prefix + "§7Benutze §8× §e/event set <Silvester,Weihnachten,Halloween, Oster, null>");
                }
            } else if(strings.length == 2){

                if(strings[0].equalsIgnoreCase("set")){
                    commandSender.sendMessage(Data.prefix + "§7Benutze §8× §e/event set <Silvester,Weihnachten,Halloween, Oster, null>");

                    if(strings[1].equalsIgnoreCase("Silvester")){
                        LobbySystem.getPlugin(LobbySystem.class).configuration.set("Events.type", "Silvester");
                        LobbySystem.getPlugin(LobbySystem.class).saveConfig();

                        commandSender.sendMessage(Data.prefix + "§7Du hast den §6EventTyp §7auf §aSilvester §7gesetzt!");
                    } else if(strings[1].equalsIgnoreCase("Weihnachten")){
                        LobbySystem.getPlugin(LobbySystem.class).configuration.set("Events.type", "Weihnachten");
                        LobbySystem.getPlugin(LobbySystem.class).saveConfig();

                        commandSender.sendMessage(Data.prefix + "§7Du hast den §6EventTyp §7auf §aWeihnachten §7gesetzt!");
                    } else if(strings[1].equalsIgnoreCase("Halloween")){
                        LobbySystem.getPlugin(LobbySystem.class).configuration.set("Events.type", "Halloween");
                        LobbySystem.getPlugin(LobbySystem.class).saveConfig();

                        commandSender.sendMessage(Data.prefix + "§7Du hast den §6EventTyp §7auf §aHalloween §7gesetzt!");
                    } else if(strings[1].equalsIgnoreCase("Oster")){
                        LobbySystem.getPlugin(LobbySystem.class).configuration.set("Events.type", "Oster");
                        LobbySystem.getPlugin(LobbySystem.class).saveConfig();

                        commandSender.sendMessage(Data.prefix + "§7Du hast den §6EventTyp §7auf §aOster §7gesetzt!");
                    } else if(strings[1].equalsIgnoreCase("null")){
                        LobbySystem.getPlugin(LobbySystem.class).configuration.set("Events.type", "null");
                        LobbySystem.getPlugin(LobbySystem.class).saveConfig();

                        commandSender.sendMessage(Data.prefix + "§7Du hast den §6EventTyp §7auf §akein Event §7gesetzt!");
                    }
                }
            }

        } else {
            commandSender.sendMessage(Data.noPermission);
        }
        return false;
    }
}
