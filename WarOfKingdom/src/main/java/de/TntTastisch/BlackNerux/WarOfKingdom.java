package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.system.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WarOfKingdom extends JavaPlugin implements Listener {

    public static Plugin plugin;
    public static MySQL mySQL;
    public FileConfiguration configuration = this.getConfig();

    @Override
    public void onEnable(){
        createFiles();
        createMySQLConnection();



        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §aDas Plugin " + getDescription().getName() + " wurde aktiviert");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §aDas Plugin " + getDescription().getName() + " Autor §bTntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §aDas Plugin " + getDescription().getName() + " Version " + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    @Override
    public void onDisable() {
        mySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §cDas Plugin " + getDescription().getName() + " wurde deaktiviert");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §cDas Plugin " + getDescription().getName() + " Autor §bTntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §cDas Plugin " + getDescription().getName() + " Version " + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public void createFiles(){

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "warofkingdom");
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

        (WarOfKingdom.mySQL = new MySQL(host, port, database, user, password)).update("");

    }
}
