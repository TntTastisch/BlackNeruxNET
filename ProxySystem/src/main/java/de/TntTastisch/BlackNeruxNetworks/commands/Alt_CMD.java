package de.TntTastisch.BlackNeruxNetworks.commands;

import com.google.common.collect.ImmutableSet;
import de.TntTastisch.BlackNeruxNetworks.ProxySystem;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import de.dytanic.cloudnet.bridge.event.proxied.ProxiedPlayerFallbackEvent;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Alt_CMD extends Command implements TabExecutor {
    public Alt_CMD() {
        super("alt", "system.admin.alt");
    }

    public void execute(final CommandSender commandSender, final String[] strings) {
        if (!commandSender.hasPermission("system.admin.alt")) {
            commandSender.sendMessage(new TextComponent(Data.noPerms));
            return;
        }
        if (strings.length == 1) {
            final ProxiedPlayer player = BungeeCord.getInstance().getPlayer(strings[0]);
            final String ip = MySQL.getIp(player.getUniqueId().toString());

            ProxySystem.iplist.clear();
            ProxySystem.iplist.put(player.getName(), MySQL.getIpFechter(player));

            commandSender.sendMessage(new TextComponent(Data.prefix + "§7Spieler auf dem Netzwerk registiert mit §d" + ip + "§8:"));
            for (final Map.Entry<String, String> entry : ProxySystem.iplist.entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                if (value.equalsIgnoreCase(ip)) {
                    commandSender.sendMessage(new TextComponent("§7» §6" + key));
                }
            }
        }
        else {
            commandSender.sendMessage(new TextComponent(Data.prefix +  "§7Benutze §8× §e/alt <Spieler>"));
        }
    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] strings) {
        if (strings.length > 2 || strings.length == 0) {
            return ImmutableSet.of();
        }

        Set<String> matches = new HashSet<String>();
        if (strings.length == 1) {
            String search = strings[0].toLowerCase();
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().toLowerCase().startsWith(search)) {
                    matches.add(player.getName());
                }
            }
        } else {
            String search = strings[1].toLowerCase();
            for (String server : ProxyServer.getInstance().getServers().keySet()) {
                if (server.toLowerCase().startsWith(search)) {
                    matches.add(server);
                }
            }
        }
        return matches;
    }
}
