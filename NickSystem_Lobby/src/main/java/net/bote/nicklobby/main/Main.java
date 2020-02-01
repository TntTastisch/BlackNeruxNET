package net.bote.nicklobby.main;

import org.bukkit.plugin.java.*;
import net.bote.nicklobby.mysql.*;
import org.bukkit.*;
import net.bote.nicklobby.events.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.configuration.file.*;
import java.io.*;

public class Main extends JavaPlugin
{
    public static MySQL mysql;
    private static Main instance;
    
    public void onEnable() {
        this.createMySQLConfig();
        this.ConnctMySQL();
        Main.instance = this;
        Bukkit.getPluginManager().registerEvents((Listener)new JoinListener(), (Plugin)this);
        final File file = new File("plugins//NickSystem", "config.yml");
        if (!file.exists()) {
            new YamlConfiguration();
            final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            cfg.set("Slot", (Object)4);
            cfg.set("Name.On", (Object)"&5Autonick &7> &aAn");
            cfg.set("Name.Off", (Object)"&5Autonick &7> &cAus");
            cfg.set("Prefix", (Object)"&7[&5NickSystem&7] ");
            try {
                cfg.save(file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void onDisable() {
    }
    
    public void createMySQLConfig() {
        final File f = new File("plugins//NickSystem", "mysql.yml");
        new YamlConfiguration();
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
        if (!f.exists()) {
            cfg.set("MySQL.ip", (Object)"localhost");
            cfg.set("MySQL.database", (Object)"database");
            cfg.set("MySQL.name", (Object)"root");
            cfg.set("MySQL.password", (Object)"password");
            try {
                cfg.save(f);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void ConnctMySQL() {
        final File f = new File("plugins//NickSystem", "mysql.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
        final String ip = cfg.getString("MySQL.ip");
        final String database = cfg.getString("MySQL.database");
        final String name = cfg.getString("MySQL.name");
        final String passwort = cfg.getString("MySQL.password");
        try {
            (Main.mysql = new MySQL(ip, database, name, passwort)).update("CREATE TABLE IF NOT EXISTS NickSystem(UUID varchar(64), STATE varchar(16));");
        }
        catch (Exception error) {
            Bukkit.getConsoleSender().sendMessage("§c>> MySQL konnte nicht verbunden werden");
        }
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
}
