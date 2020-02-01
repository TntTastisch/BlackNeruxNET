package de.TeeJan.BlackNerux;

import de.TeeJan.BlackNerux.commands.Coins_CMD;
import de.TeeJan.BlackNerux.commands.SetSpawn_CMD;
import de.TeeJan.BlackNerux.commands.Stats_CMD;
import de.TeeJan.BlackNerux.listeners.*;
import de.TeeJan.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class OneHit extends JavaPlugin implements Listener {


    public static File file = new File("plugins/OneHit", "locations.yml");
    public static YamlConfiguration filecfg = YamlConfiguration.loadConfiguration(file);
    public FileConfiguration configuration = this.getConfig();
    public static Plugin plugin;
    public static MySQL mySQL;

    public void onEnable() {
        plugin = this;
        createFiles();
        createConnection();
        PluginManager manager = Bukkit.getPluginManager();

        getCommand("setspawn").setExecutor(new SetSpawn_CMD());
        getCommand("stats").setExecutor(new Stats_CMD());
        getCommand("coins").setExecutor(new Coins_CMD());

        manager.registerEvents(new JoinQuitListener(), this);
        manager.registerEvents(new NoDamageListener(), this);
        manager.registerEvents(new DeathListener(), this);
        manager.registerEvents(new InventoryClickListener(), this);
        manager.registerEvents(new WeatherListener(), this);
        manager.registerEvents(new NoBuildListener(), this);
        manager.registerEvents(new GadgetListener(), this);

        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §aDas Plugin wurde erfolgreich aktiviert!");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §aDer Pluginautor ist TeeJan");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §aDie Pluginversion ist die §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
    }

    public void onDisable() {
        OneHit.mySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §cDas Plugin wurde erfolgreich deaktiviert!");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §cDer Pluginautor ist TeeJan");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §cDie Pluginversion ist die §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
    }

    public static Plugin getInstance() {
        return plugin;
    }

    public void createFiles(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "onehit");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");
        configuration.options().copyDefaults(true);
        saveConfig();
    }

    public void createConnection(){
        String host = configuration.getString("MySQL.Host");
        String port = configuration.getString("MySQL.Port");
        String database = configuration.getString("MySQL.Database");
        String user = configuration.getString("MySQL.User");
        String password = configuration.getString("MySQL.Password");

        (OneHit.mySQL = new MySQL(host,port,database,user,password))
                .update("CREATE TABLE IF NOT EXISTS onehit(UUID varchar(120), Ranking varchar(64), Kills varchar(64), Deaths varchar(64), Coins varchar(64));");
    }
}