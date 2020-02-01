package de.TntTastisch.BlackNeruxNetworks.commands;

import de.TntTastisch.BlackNeruxNetworks.ProxySystem;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.info.ProxyInfo;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class FullNetworkRestart_CMD extends Command {
    public FullNetworkRestart_CMD() {
        super("fullnetworkrestart", "system.admin.restart.network", "fullrestart", "networkrestart");
    }

    public static ScheduledTask networkrestart;
    public static int network = 11;

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender.hasPermission("system.admin.restart.network")){

            networkrestart = BungeeCord.getInstance().getScheduler().schedule(ProxySystem.getInstance(), new Runnable() {
                public void run() {
                   if (network == 10 || network == 5 || network == 4 || network == 3 || network == 2) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird manuell neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l" + network + " Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if(network == 1){
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird manuell neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l1 Sekunde" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (network == 0) {
                        BungeeCord.getInstance().getScheduler().cancel(ProxySystem.getInstance());

                        CloudAPI.getInstance().sendCloudCommand("reload ALL");
                        CloudAPI.getInstance().sendCloudCommand("reload CONFIG");
                        CloudAPI.getInstance().sendCloudCommand("reload WRAPPER");


                       BungeeCord.getInstance().broadcast(new TextComponent(
                               "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                       "\n" +
                                       "\n  §a§lDas Netzwerk startet jetzt neu!" +
                                       "\n\n" +
                                       "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                       ));

                        BungeeCord.getInstance().getScheduler().schedule(ProxySystem.getInstance(), new Runnable() {
                            public void run() {
                                for (ServerInfo cloudServer : CloudAPI.getInstance().getServers()) {
                                    String servers = cloudServer.getServiceId().getServerId();

                                    CloudAPI.getInstance().sendCloudCommand("shutdown SERVER " + servers);
                                }

                                for (ProxyInfo cloudServer : CloudAPI.getInstance().getProxys()) {
                                    String servers = cloudServer.getServiceId().getServerId();

                                    CloudAPI.getInstance().sendCloudCommand("shutdown PROXY " + servers);
                                }
                            }
                        }, 1, TimeUnit.SECONDS);

                    }
                   network--;
                }
            }, 1, 1, TimeUnit.SECONDS);



        } else {
            commandSender.sendMessage(new TextComponent(Data.noPerms));
        }
    }
}
