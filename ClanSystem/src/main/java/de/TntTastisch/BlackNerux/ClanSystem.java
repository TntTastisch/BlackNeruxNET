package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.commands.ClanChat_CMD;
import de.TntTastisch.BlackNerux.commands.Clan_CMD;
import de.TntTastisch.BlackNerux.listener.JoinQuitListener;
import de.TntTastisch.BlackNerux.systems.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ClanSystem extends Plugin implements Listener {

    public static Plugin plugin;
    public Configuration configuration;
    public static MySQL mySQL;

    @Override
    public void onEnable() {
        createFiles();
        createMySQLConnection();
        plugin = this;

        BungeeCord.getInstance().getPluginManager().registerListener(this, new JoinQuitListener());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new Clan_CMD());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new ClanChat_CMD());

        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §a" + getDescription().getName() + " wurde erfolgreich aktiviert!"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §a" + getDescription().getName() + " Version §5" + getDescription().getVersion()));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §a" + getDescription().getName() + " Autor TntTastisch"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]"));

    }

    @Override
    public void onDisable() {
        mySQL.disconnect();
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §c" + getDescription().getName() + " wurde erfolgreich deaktiviert!"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §c" + getDescription().getName() + " Version §5" + getDescription().getVersion()));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]====[ §c" + getDescription().getName() + " Autor TntTastisch"));
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]"));
    }

    public void createFiles(){

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File config = new File(getDataFolder().getPath(), "config.yml");

        try {
            if(!config.exists()) {
                config.createNewFile();
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
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
            configuration.set("MySQL.Database", "clan");
        }

        if (!configuration.contains("MySQL.User")) {
            configuration.set("MySQL.User", "root");
        }

        if (!configuration.contains("MySQL.Password")) {
            configuration.set("MySQL.Password", " ");
        }

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMySQLConnection(){
        String host = configuration.getString("MySQL.Host");
        String port = configuration.getString("MySQL.Port");
        String database = configuration.getString("MySQL.Database");
        String user = configuration.getString("MySQL.User");
        String password = configuration.getString("MySQL.Password");

        (ClanSystem.mySQL = new MySQL(host,port,database,user,password)).update("CREATE TABLE IF NOT EXISTS clanPlayers(UUID varchar(64), Clan varchar(120), ClanRank varchar(64), invites varchar(64))");
    }

    public static Plugin getInstance() {
        return plugin;
    }
}
