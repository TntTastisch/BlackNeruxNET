package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.PlayerInfoSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Clan_CMD extends Command {
    public Clan_CMD() {
        super("clan");
    }

    public void execute(CommandSender commandSender, String[] strings) {

        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if(strings.length == 0){

                /*player.sendMessage(new TextComponent(Data.prefix + "§e/clan toggleinvites"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan create <Clan Name> <Clan Tag>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan delete <Clan Name>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan promote <Spieler>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan demote <Spieler>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan color"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan uInfo <Spieler>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan tInfo <Clan Tag>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan info"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan leave "));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan join <Spieler>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan deny <Spieler>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan kick <Spieler>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/clan rename <Clan Name> <Clan Tag>"));
                player.sendMessage(new TextComponent(Data.prefix + "§e/cc"));
                 */

                player.sendMessage(new TextComponent(Data.prefix + "§cDieses System ist derzeitig deaktivert!"));

            } else if(strings.length >= 1){
                /*
                if(strings[0].equalsIgnoreCase("create")){
                    String clanname = strings[1];
                    String clantag = strings[2];
                    player.sendMessage(new TextComponent(Data.prefix + "§7Benutze §8× §e/clan create <Clan Name> <Clan Tag>"));

                    if(clanname != null){
                        if(clantag != null){
                            if(MySQL.isPlayerInClan(player.getUniqueId().toString()) != "null") {
                                if (MySQL.clanExists(clanname) == true) {
                                    player.sendMessage(Data.prefix + "§cDieser Clan existiert bereits!");
                                } else {
                                    MySQL.createClan(player.getUniqueId().toString(), clanname, clantag);
                                    MySQL.setClanPlayer(player.getUniqueId().toString(), clanname);
                                    MySQL.setRank(player.getUniqueId().toString(), "Anführer");
                                    player.sendMessage(Data.prefix + "§aDu hast den Clan §e" + clanname + " §aerfolgreich erstellt!");
                                }
                            } else {
                                player.sendMessage(Data.prefix + "§cDu bist bereits in einem Clan!");
                            }

                        }
                    }
                }

                 */
            }
        } else {
            commandSender.sendMessage(new TextComponent(Data.noPlayer));
        }
    }
}
