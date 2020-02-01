package de.TntTastisch.BlackNeruxNetworks.commands;

import de.TntTastisch.BlackNeruxNetworks.system.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class JoinME_CMD extends Command {
    public JoinME_CMD() {
        super("joinme", "system.vip.joinme");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if(player.hasPermission("system.vip.joinme")){



            } else {
                player.sendMessage(new TextComponent(Data.noPerms));
            }
        } else {
            commandSender.sendMessage(new TextComponent(Data.noPlayer));
        }
    }
}
