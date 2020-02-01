package de.TntTastisch.BlackNeruxNetworks.commands;

import com.google.common.collect.ImmutableSet;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Report_CMD extends Command {
    public Report_CMD() {
        super("report");
    }



    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (strings.length == 0) {

                player.sendMessage(new TextComponent("§6§lReport Information"));
                player.sendMessage(new TextComponent(""));
                player.sendMessage(new TextComponent("§7» §4Hacking"));
                player.sendMessage(new TextComponent("§7» §4Werbung"));
                player.sendMessage(new TextComponent("§7» §4Chatverhalten"));
                player.sendMessage(new TextComponent("§7» §4Skin/Name"));
                player.sendMessage(new TextComponent("§7» §4Teaming"));
                player.sendMessage(new TextComponent("§7» §4Spamming"));
                player.sendMessage(new TextComponent("§7» §4Bugusing"));
                player.sendMessage(new TextComponent(""));
                player.sendMessage(new TextComponent(Data.prefixReport + "§7Benutze §8× " + ChatColor.YELLOW + "/report <Spieler> <Grund>"));

            } else if (strings.length == 1) {
                if (player.hasPermission("system.staff.report")) {
                    if (strings[0].equalsIgnoreCase("login")) {
                        if (MySQL.getReport(player.getUniqueId().toString()) == 0) {

                            MySQL.setReport(player.getUniqueId().toString(), "1");
                            player.sendMessage(new TextComponent(Data.prefixReport + "§7Du hast dich §aeingeloggt§7."));

                        } else if (MySQL.getReport(player.getUniqueId().toString()) == 1) {
                            player.sendMessage(new TextComponent(Data.prefixReport + "§cDu bist bereits eingeloggt!"));
                        }
                    } else if (strings[0].equalsIgnoreCase("logout")) {
                        if (MySQL.getReport(player.getUniqueId().toString()) == 1) {

                            MySQL.setReport(player.getUniqueId().toString(), "0");
                            player.sendMessage(new TextComponent(Data.prefixReport + "§7Du hast dich §causgeloggt§7."));

                        } else if (MySQL.getReport(player.getUniqueId().toString()) == 0) {
                            player.sendMessage(new TextComponent(Data.prefixReport + "§cDu bist bereits ausgeloggt!"));
                        }
                    }
                }
            } else if (strings.length == 2) {
                ProxiedPlayer target = BungeeCord.getInstance().getPlayer(strings[0]);
                String reason = strings[1];

                if (target == null) {
                    player.sendMessage(new TextComponent(Data.prefixReport + "§cDieser Spieler ist Offline!"));
                } else if (target == player) {
                    player.sendMessage(new TextComponent(Data.prefixReport + "§cDu kannst dich nicht selbst reporten!"));
                } else if (target.hasPermission("system.report.bypass")) {
                    player.sendMessage(new TextComponent(Data.prefixReport + "§cDu kannst diesen Spieler nicht reporten"));
                } else {

                    Date today = Calendar.getInstance().getTime();
                    DateFormat tm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    TimeZone timezone = TimeZone.getTimeZone("Europe/Berlin");
                    tm.setTimeZone(timezone);
                    String Time = tm.format(today);

                    if (reason.equalsIgnoreCase("Hacking") || reason.equalsIgnoreCase("Werbung") || reason.equalsIgnoreCase("Chatverhalten") ||
                            reason.equalsIgnoreCase("Skin/Name") || reason.equalsIgnoreCase("Teaming") || reason.equalsIgnoreCase("Spamming") ||
                            reason.equalsIgnoreCase("Bugusing")) {

                        MySQL.createReport(target, player, reason, "open", Time);

                        TextComponent klicks = new TextComponent();
                        klicks.setText(" §8➦ §a§lKLICKE HIER");
                        klicks.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportjump " + target.getName()));

                        for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
                            if (all.hasPermission("system.staff.report")) {
                                if (MySQL.getReport(all.getUniqueId().toString()) == 1) {
                                    all.sendMessage(new TextComponent("§8§m-------------§r §4§lREPORT §8§M-------------"));
                                    all.sendMessage(new TextComponent(""));
                                    all.sendMessage(new TextComponent(" §8➦ §b§lReportet§8: " + Data.getPlayerPrefix(target)));
                                    all.sendMessage(new TextComponent(" §8➦ §b§lReporter§8: " + Data.getPlayerPrefix(player)));
                                    all.sendMessage(new TextComponent(" §8➦ §b§lGrund§8: §5§l" + reason));
                                    all.sendMessage(klicks);
                                    all.sendMessage(new TextComponent(""));
                                    all.sendMessage(new TextComponent("§8§m-------------§r §4§lREPORT §8§M-------------"));
                                }
                            }
                        }

                        player.sendMessage(new TextComponent(Data.prefixReport + "§aDu hast " + Data.getPlayerPrefix(target) + " §afür §e" + reason + " §aerfolgreich reportet. Vielen Dank für deine Aufmerksamkeit!"));
                    } else {

                        player.sendMessage(new TextComponent("§6§lReport Information"));
                        player.sendMessage(new TextComponent(""));
                        player.sendMessage(new TextComponent("§7» §4Hacking"));
                        player.sendMessage(new TextComponent("§7» §4Werbung"));
                        player.sendMessage(new TextComponent("§7» §4Chatverhalten"));
                        player.sendMessage(new TextComponent("§7» §4Skin/Name"));
                        player.sendMessage(new TextComponent("§7» §4Teaming"));
                        player.sendMessage(new TextComponent("§7» §4Spamming"));
                        player.sendMessage(new TextComponent("§7» §4Bugusing"));
                        player.sendMessage(new TextComponent(""));
                        player.sendMessage(new TextComponent(Data.prefixReport + "§7Benutze §8× " + ChatColor.YELLOW + "/report <Spieler> <Grund>"));
                    }
                }
            }
        } else {
            commandSender.sendMessage(new TextComponent(Data.noPlayer));
        }
    }
}
