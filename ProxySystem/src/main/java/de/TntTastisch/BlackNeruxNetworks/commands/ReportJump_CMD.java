package de.TntTastisch.BlackNeruxNetworks.commands;

import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import javax.swing.*;

public class ReportJump_CMD extends Command {
    public ReportJump_CMD() {
        super("reportjump", "system.staff.report");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if(strings.length == 0) {
                player.sendMessage(Data.noPerms);
            } else if(strings.length == 1){
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(strings[0]);
                final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

                if(target == null){
                    player.sendMessage(Data.prefixReport + "§cDieser Spieler ist offline!");
                } else {

                    if (MySQL.getReportStatus(target.getUniqueId().toString()).equalsIgnoreCase("open")) {
                        MySQL.setReportStatus(target.getUniqueId().toString(), "close");
                        Data.connectPlayer(cloudPlayer, target.getServer().getInfo().getName().toString());
                        player.sendMessage(Data.prefixReport + "§aDu bearbeitest nun den Report von §d" + Data.getPlayerPrefix(target) + "§a.");
                    } else if (MySQL.getReportStatus(target.getUniqueId().toString()).equalsIgnoreCase("close")) {
                        player.sendMessage(new TextComponent(Data.prefixReport + ChatColor.RED + "Dieser Report wird bereits von einem Team Mitglied bearbeitet!"));
                    }
                }
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
    }
}
