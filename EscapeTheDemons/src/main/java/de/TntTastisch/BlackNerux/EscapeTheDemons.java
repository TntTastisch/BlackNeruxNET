package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.commands.ETD_CMD;
import de.TntTastisch.BlackNerux.commands.Start_CMD;
import de.TntTastisch.BlackNerux.listener.*;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class EscapeTheDemons extends JavaPlugin implements Listener {

    public static MySQL mySQL;
    public FileConfiguration configuration = this.getConfig();
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        createFiles();
        createMySQLConnection();
        JoinQuitListener.randomMap();
        GameState.setGameState(GameState.LOBBY);

        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new DamageListener(), this);
        manager.registerEvents(new JoinQuitListener(), this);
        manager.registerEvents(new BuildListener(), this);
        manager.registerEvents(new LobbyInteract(), this);
        manager.registerEvents(new SpectatorCompassListener(), this);

        this.getCommand("etd").setExecutor(new ETD_CMD());
        // this.getCommand("forcemap").setExecutor(new ForceMap_CMD());
        this.getCommand("start").setExecutor(new Start_CMD());

        Bukkit.getConsoleSender().sendMessage("§f[]=============[ §4§l" + getDescription().getName() + " §f]=============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin " + getDescription().getName() + " wurde erfolgreich aktiviert.");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §Das Plugin "+ getDescription().getName() + " ist in der Version §B" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin " + getDescription().getName() + " wurde von TntTastisch programmiert");
        Bukkit.getConsoleSender().sendMessage("§f[]=============[ §4§l" + getDescription().getName() + " §f]=============[]");

    }

    @Override
    public void onDisable() {
        mySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§f[]=============[ §4§l" + getDescription().getName() + " §f]=============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin " + getDescription().getName() + " wurde erfolgreich deaktiviert.");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §Das Plugin "+ getDescription().getName() + " ist in der Version §B" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin " + getDescription().getName() + " wurde von TntTastisch programmiert");
        Bukkit.getConsoleSender().sendMessage("§f[]=============[ §4§l" + getDescription().getName() + " §f]=============[]");
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public void createFiles(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File gameDir = new File(getDataFolder().getPath() + "/games");

        if(!gameDir.exists()){
            gameDir.mkdir();
        }



        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "escapethedemons");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");
        configuration.options().copyDefaults(true);
        saveConfig();
    }

    public void createMySQLConnection(){
        String host = configuration.getString("MySQL.Host");
        String port = configuration.getString("MySQL.Port");
        String database = configuration.getString("MySQL.Database");
        String user = configuration.getString("MySQL.User");
        String password = configuration.getString("MySQL.Password");

        (EscapeTheDemons.mySQL = new MySQL(host,port,database,user,password)).update("CREATE TABLE IF NOT EXISTS EscapeTheDemons(UUID varchar(64), Played varchar(120), Lost varchar(120), Points varchar(120), DemonPass varchar(64), PolicePass varchar(64))");

    }

}
