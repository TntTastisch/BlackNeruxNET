package de.TntTastisch.BlackNeruxNetworks.commands;

import de.TntTastisch.BlackNeruxNetworks.system.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Shutdown_CMD extends Command {
    public Shutdown_CMD() {
        super("shutdown", "system.admin.shutdown");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if(player.hasPermission("system.admin.shutdown")){
                player.sendMessage(new TextComponent(Data.prefix + "§cDieses Feature ist derzeitig deaktiviert!"));
            } else {
                player.sendMessage(new TextComponent(Data.noPerms));
            }
        } else {
            commandSender.sendMessage(new TextComponent(Data.noPlayer));
        }
    }
}
