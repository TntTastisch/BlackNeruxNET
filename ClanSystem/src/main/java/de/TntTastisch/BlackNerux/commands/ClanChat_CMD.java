package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import net.md_5.bungee.PlayerInfoSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ClanChat_CMD extends Command {
    public ClanChat_CMD() {
        super("cc");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer){

        } else {
            commandSender.sendMessage(new TextComponent(Data.noPlayer));
        }
    }
}
