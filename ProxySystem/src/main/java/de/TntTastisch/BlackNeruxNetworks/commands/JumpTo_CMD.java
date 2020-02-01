package de.TntTastisch.BlackNeruxNetworks.commands;

import com.google.common.collect.ImmutableSet;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JumpTo_CMD extends Command implements TabExecutor {

    public JumpTo_CMD() {
        super("jumpto", "system.staff.report.jumpto");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer ){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;



            if(player.hasPermission("system.staff.report.jumpto")) {
                if (strings.length == 0) {
                    player.sendMessage(new TextComponent(Data.prefix + "§7Benutze §8× " + ChatColor.YELLOW + "/jumpto <Spieler>"));
                } else if(strings.length == 1){
                    ProxiedPlayer target = BungeeCord.getInstance().getPlayer(strings[0]);
                    final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

                    if(target == null){
                        player.sendMessage(new TextComponent(Data.prefix + "§cDieser Spieler ist Offline"));
                    }

                    if(target == player){
                        player.sendMessage(new TextComponent(Data.prefix + "§cDu kannst nicht zu dir selbst springen!"));
                    } else {

                        player.sendMessage(new TextComponent(Data.prefix + "§aDu wurdest zum Spieler gesendet!"));
                        Data.connectPlayer(cloudPlayer, CloudAPI.getInstance().getOnlinePlayer(target.getUniqueId()).getServer());
                    }
                }
            } else {
                player.sendMessage(new TextComponent(Data.noPerms));
            }

        } else {
            commandSender.sendMessage(new TextComponent(Data.noPlayer));
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
