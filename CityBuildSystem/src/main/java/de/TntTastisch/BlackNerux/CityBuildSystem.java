package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.commands.GameMode_CMD;
import de.TntTastisch.BlackNerux.commands.Vanish_CMD;
import de.TntTastisch.BlackNerux.listeners.ColouredAnvilListener;
import de.TntTastisch.BlackNerux.listeners.ColouredSignListener;
import de.TntTastisch.BlackNerux.listeners.JoinQuitListener;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CityBuildSystem extends JavaPlugin implements Listener {

    public static CityBuildSystem plugin;
    public static MySQL mySQL;
    public FileConfiguration configuration = this.getConfig();

    @Override
    public void onEnable() {
        plugin = this;
        createFiles();
        connectToSQL();
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new ColouredSignListener(), this);
        manager.registerEvents(new JoinQuitListener(), this);
        manager.registerEvents(new ColouredAnvilListener(), this);

        this.getCommand("gamemode").setExecutor(new GameMode_CMD());
        this.getCommand("vanish").setExecutor(new Vanish_CMD());

        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin wurde erfolgreich aktiviert.");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDer Plugin Autor ist §bTntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin ist in der Version §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    @Override
    public void onDisable() {
        CityBuildSystem.mySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §cDas Plugin wurde erfolgreich deaktiviert.");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §cDer Plugin Autor ist §bTntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §cDas Plugin ist in der Version §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    public static CityBuildSystem getInstance() {
        return plugin;
    }

    public void createFiles(){

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }


        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "citybuild");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");
        configuration.options().copyDefaults(true);
        saveConfig();
    }

    public void connectToSQL(){
        String host = configuration.getString("MySQL.Host");
        String port = configuration.getString("MySQL.Port");
        String database = configuration.getString("MySQL.Database");
        String user = configuration.getString("MySQL.User");
        String password = configuration.getString("MySQL.Password");

        (CityBuildSystem.mySQL = new MySQL(host,port,database,user,password))
                .update("CREATE TABLE IF NOT EXISTS players(UUID varchar(120), Name varchar(64), MsgEnable varchar(10), " +
                        "SocialSpyEnable varchar(10), TpaEnable varchar(10), VanishEnable varchar(10), FlyEnabled varchar(10));");


    }
}
