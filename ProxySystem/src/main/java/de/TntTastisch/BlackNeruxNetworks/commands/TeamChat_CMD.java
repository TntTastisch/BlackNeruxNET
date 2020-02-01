package de.TntTastisch.BlackNeruxNetworks.commands;

import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TeamChat_CMD extends Command {
    public TeamChat_CMD() {
        super("teamchat", "system.staff.teamchat", "tc");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (player.hasPermission("system.staff.teamchat")) {
                if (strings.length == 0) {
                    player.sendMessage(new TextComponent(Data.prefixTeamChat + "§7Benutze §8× " + ChatColor.YELLOW + "/teamchat <Nachricht | login, §elogout> oder /tc <Nachricht | login, logout>"));
                } else if (strings.length >= 1) {
                    if (strings[0].equalsIgnoreCase("login")) {
                        if (MySQL.getTeamChat(player.getUniqueId().toString()) == 0) {

                            MySQL.setTeamChat(player.getUniqueId().toString(), "1");
                            player.sendMessage(new TextComponent(Data.prefixTeamChat + "§7Du hast dich §aeingeloggt§7."));

                        } else if (MySQL.getTeamChat(player.getUniqueId().toString()) == 1) {

                            player.sendMessage(new TextComponent(Data.prefixTeamChat + "§cDu bist bereits eingeloggt!"));
                        }
                    } else

                    if (strings[0].equalsIgnoreCase("logout")) {
                        if (MySQL.getTeamChat(player.getUniqueId().toString()) == 1) {

                            MySQL.setTeamChat(player.getUniqueId().toString(), "0");
                            player.sendMessage(new TextComponent(Data.prefixTeamChat + "§7Du hast dich §causgeloggt§7."));

                        } else if (MySQL.getTeamChat(player.getUniqueId().toString()) == 0) {
                            player.sendMessage(new TextComponent(Data.prefixTeamChat + "§cDu bist bereits ausgeloggt!"));
                        }
                    } else

                    if (MySQL.getTeamChat(player.getUniqueId().toString()) == 1) {
                        for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
                            if (all.hasPermission("system.staff.teamchat")) {
                                String nachricht = "";


                                for (int i = 0; i < strings.length; i++) {
                                    nachricht = nachricht + " " + strings[i];
                                }

                                if(MySQL.getTeamChat(all.getUniqueId().toString()) == 1) {
                                    all.sendMessage(new TextComponent(Data.prefixTeamChat + Data.getPlayerPrefix(player) + " §8➟§b§l" + ChatColor.translateAlternateColorCodes('&', nachricht)));
                                }
                            }
                        }
                    } else if (MySQL.getTeamChat(player.getUniqueId().toString()) == 0) {
                        player.sendMessage(new TextComponent(Data.prefixTeamChat + "§cDu musst eingeloggt sein!"));
                    }
                }
            } else {
                player.sendMessage(new TextComponent(Data.noPerms));
            }
        } else {

        }
    }
}
