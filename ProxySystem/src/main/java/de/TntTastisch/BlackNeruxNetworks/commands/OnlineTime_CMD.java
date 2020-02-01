package de.TntTastisch.BlackNeruxNetworks.commands;

import com.google.common.collect.ImmutableSet;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class OnlineTime_CMD extends Command implements TabExecutor {
    public OnlineTime_CMD() {
        super("onlinetime", null, "online");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (strings.length == 0) {

                int time = MySQL.getTime(player.getUniqueId());
                int translated = time / 3600;

                player.sendMessage(new TextComponent(Data.prefix + "§7Deine Onlinezeit beträgt §8➟ §b" + translated + " §bStunden§7."));
            } else if (strings.length == 1) {
                if (player.hasPermission("system.onlinetime.other")) {
                    ProxiedPlayer target = BungeeCord.getInstance().getPlayer(strings[0]);



                    if (target == null) {
                        player.sendMessage(new TextComponent(Data.prefix + "§cDieser Spieler ist offline!"));
                    } else if (player == target) {
                        int time = MySQL.getTime(player.getUniqueId());
                        int translated = time / 3600;

                        player.sendMessage(new TextComponent(Data.prefix + "§7Deine Onlinezeit beträgt §8➟ §b" + translated + " §bStunden§7."));
                    } else {
                        int time = MySQL.getTime(target.getUniqueId());
                        int translated = time / 3600;

                        player.sendMessage(new TextComponent(Data.prefix + "§7Die Onlinezeit von §6" + Data.getPlayerPrefix(target) + " §7beträgt §8➟ §b" + translated + " §bStunden§7."));

                    }


                } else {
                    int time = MySQL.getTime(player.getUniqueId());
                    int translated = time / 3600;

                    player.sendMessage(new TextComponent(Data.prefix + "§7Deine Onlinezeit beträgt §8➟ §b" + translated + " §bStunden§7."));
                }
            } else if (strings.length > 1) {
                if (!player.hasPermission("system.onlinetime.other")) {
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/onlinetime");
                } else {
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/onlinetime <Spieler>");
                }
            }


        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {
        if ( strings.length > 2 || strings.length == 0 )
        {
            return ImmutableSet.of();
        }

        Set<String> matches = new HashSet<String>();
        if ( strings.length == 1 )
        {
            String search = strings[0].toLowerCase();
            for ( ProxiedPlayer player : ProxyServer.getInstance().getPlayers() )
            {
                if ( player.getName().toLowerCase().startsWith( search ) )
                {
                    matches.add( player.getName() );
                }
            }
        } else
        {
            String search = strings[1].toLowerCase();
            for ( String server : ProxyServer.getInstance().getServers().keySet() )
            {
                if ( server.toLowerCase().startsWith( search ) )
                {
                    matches.add( server );
                }
            }
        }
        return matches;
    }
}
