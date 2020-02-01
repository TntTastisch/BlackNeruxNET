package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.systems.MySQL;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class F13 extends JavaPlugin implements Listener {

    public static MySQL mySQL;
    public FileConfiguration configuration = this.getConfig();

    @Override
    public void onEnable() {
        createFiles();
        createMySQLConnect();

        PluginManager manager = Bukkit.getPluginManager();




       Bukkit.getConsoleSender().sendMessage("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]");
       Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " wurde erfolgreich aktiviert.");
       Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " Version §b" + getDescription().getVersion());
       Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " Autor TntTastisch");
       Bukkit.getConsoleSender().sendMessage("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]");
    }

    @Override
    public void onDisable() {
        mySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §c" + getDescription().getName() + " wurde erfolgreich deaktiviert.");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §c" + getDescription().getName() + " Version §b" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §c" + getDescription().getName() + " Autor TntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]===========[ §4§l" + getDescription().getName() + " §f]===========[]");
    }

    public void createFiles(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }


        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "f13");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");
        configuration.options().copyDefaults(true);
        saveConfig();

    }

    public void createMySQLConnect() {

        String host = configuration.getString("MySQL.Host");
        String port ;
        String database;
        String user;
        String password ;

    }
}
