package de.TntTastisch.BlackNeruxNetworks;

import de.TntTastisch.BlackNeruxNetworks.commands.*;
import de.TntTastisch.BlackNeruxNetworks.listeners.BlockedMessagesListener;
import de.TntTastisch.BlackNeruxNetworks.listeners.ChatListener;
import de.TntTastisch.BlackNeruxNetworks.listeners.JoinQuitListener;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.info.ProxyInfo;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ProxySystem extends Plugin implements Listener {

    public static MySQL mySQl;
    public static Configuration configuration;
    public static Map<String, String> iplist = new HashMap<String, String>();;
    private static Plugin plugin;
    public static ScheduledTask updateTime;
    public static ScheduledTask proxyrestart;


    @Override
    public void onEnable() {
        createFiles();
        createMySQLConnection();
        autoRestart();
        plugin = this;

        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();

        pluginManager.registerListener(this, new JoinQuitListener());
        pluginManager.registerListener(this, new ChatListener());
        pluginManager.registerListener(this, new BlockedMessagesListener());

        pluginManager.registerCommand(this, new Broadcast_CMD());
        pluginManager.registerCommand(this, new FullNetworkRestart_CMD());
        pluginManager.registerCommand(this, new Shutdown_CMD());
        pluginManager.registerCommand(this, new JoinME_CMD());
        pluginManager.registerCommand(this, new OnlineTime_CMD());
        pluginManager.registerCommand(this, new Alt_CMD());
        pluginManager.registerCommand(this, new TeamChat_CMD());
        pluginManager.registerCommand(this, new Report_CMD());
        pluginManager.registerCommand(this, new ReportJump_CMD());
        pluginManager.registerCommand(this, new JumpTo_CMD());
        pluginManager.registerCommand(this, new Ping_CMD());

        updateTime = BungeeCord.getInstance().getScheduler().schedule(this, new Runnable() {
            public void run() {
                for (final ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
                    MySQL.updateTime(all);
                }
            }
        }, 0L, 1L, TimeUnit.SECONDS);

        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §a" + getDescription().getName() + " wurde erfolgreich aktiviert."));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §a" + getDescription().getName() + " Version §b" + getDescription().getVersion()));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §a" + getDescription().getName() + " Autor TntTastisch"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]"));
    }

    @Override
    public void onDisable() {
        mySQl.disconnect();
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §c" + getDescription().getName() + " wurde erfolgreich deaktiviert."));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §c" + getDescription().getName() + " Version §b" + getDescription().getVersion()));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §c" + getDescription().getName() + " Autor TntTastisch"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]"));
    }

    public static Plugin getInstance() {
        return plugin;
    }

    private void createFiles() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File data = new File(getDataFolder().getPath() + "/data");

        if(!data.exists()){
            data.mkdir();
        }


        File file = new File(getDataFolder().getPath(), "config.yml");

        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {

            e.printStackTrace();
        }

        if (!configuration.contains("MySQL.Host")) {
            configuration.set("MySQL.Host", "localhost");
        }

        if (!configuration.contains("MySQL.Port")) {
            configuration.set("MySQL.Port", "3306");
        }

        if (!configuration.contains("MySQL.Database")) {
            configuration.set("MySQL.Database", "proxysystem");
        }

        if (!configuration.contains("MySQL.User")) {
            configuration.set("MySQL.User", "root");
        }

        if (!configuration.contains("MySQL.Password")) {
            configuration.set("MySQL.Password", " ");
        }

        if (!configuration.contains("AutoRestart.enable")) {
            configuration.set("AutoRestart.enable", true);
        }

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createMySQLConnection() {
        String host = configuration.getString("MySQL.Host");
        String port = configuration.getString("MySQL.Port");
        String database = configuration.getString("MySQL.Database");
        String user = configuration.getString("MySQL.User");
        String password = configuration.getString("MySQL.Password");

        try {
            (ProxySystem.mySQl = new MySQL(host, port, database, user, password))
                    .update("CREATE TABLE IF NOT EXISTS system_players(name varchar(20), uuid varchar(120), ip varchar(100), report varchar(30), teamchat varchar(30));");

        } catch (Exception e) {
        }
    }

    public static String GetIP(final String player) {
        String ip = null;
        if (iplist.containsKey(player)) {
            ip = iplist.get(player);
        }
        return ip;
    }

    public void autoRestart() {
        if (configuration.getBoolean("AutoRestart.enable") == true) {
            proxyrestart = BungeeCord.getInstance().getScheduler().schedule(this, new Runnable() {
                public void run() {
                    Date today = Calendar.getInstance().getTime();
                    DateFormat tm = new SimpleDateFormat("HH:mm:ss");
                    TimeZone timezone = TimeZone.getTimeZone("Europe/Berlin");
                    tm.setTimeZone(timezone);
                    String Time = tm.format(today);

                    if (Time.startsWith("04:45:00")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l15 Minuten" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:50:00")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l10 Minuten" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:55:00")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l5 Minuten" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:00")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l60 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:15")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l45 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:30")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l30 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:45")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l15 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:50")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l10 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:55")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l5 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:56")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l4 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:57")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l3 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:58")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l2 Sekunden" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("04:59:59")) {
                        BungeeCord.getInstance().broadcast(new TextComponent(
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                        "\n" +
                                        "\n  §a§lDas Netzwerk wird automatisch neugestartet..." +
                                        "\n     §6§lVerbleibende Zeit §8× §4§l1 Sekunde" +
                                        "\n\n" +
                                        "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+"
                        ));
                    } else if (Time.startsWith("05:00:00")) {
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
                }
            }, 1, 1, TimeUnit.SECONDS);
        }
    }
}
