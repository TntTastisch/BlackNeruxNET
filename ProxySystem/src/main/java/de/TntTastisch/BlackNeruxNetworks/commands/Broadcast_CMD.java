package de.TntTastisch.BlackNeruxNetworks.commands;

import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Broadcast_CMD extends Command {
    public Broadcast_CMD() {
        super("broadcast", "system.admin.broadcast", "bc");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender.hasPermission("system.admin.broadcast")){

            if(strings.length == 0){
                commandSender.sendMessage(new TextComponent(Data.prefix + "§7Benutze §8× " + ChatColor.YELLOW + "/broadcast <Nachricht> §eoder /bc <Nachricht>"));
            } else if(strings.length >= 1){
                String message = "";
                for(int i = 0; i < strings.length; i++){
                    message = message + " " + strings[i];
                }

                BungeeCord.getInstance().broadcast(
                        "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+§r" +
                        "\n" +
                        "\n" +
                        "§7➟ §b§l" + ChatColor.translateAlternateColorCodes('&', message)+
                        "\n" +
                        "\n" +
                        "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");

            }
        } else {
            commandSender.sendMessage(new TextComponent(Data.noPerms));
        }
    }
}
