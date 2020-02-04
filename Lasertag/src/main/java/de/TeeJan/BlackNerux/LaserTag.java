package de.TeeJan.BlackNerux;

import de.TeeJan.BlackNerux.Teams.TeamChat;
import de.TeeJan.BlackNerux.Teams.TeamInteract;
import de.TeeJan.BlackNerux.Teams.TeamSelect;
import de.TeeJan.BlackNerux.Teams.Teams;
import de.TeeJan.BlackNerux.cmd.SetSpawncmd;
import de.TeeJan.BlackNerux.listeners.*;
import de.TeeJan.BlackNerux.systems.MySQL;
import de.TeeJan.BlackNerux.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.io.IOException;


public class LaserTag extends JavaPlugin implements Listener {

    public FileConfiguration configuration = this.getConfig();
    public static File file = new File("plugins/LaserTag/locations.yml");
    public static YamlConfiguration fileCFG = YamlConfiguration.loadConfiguration(file);
    public static MySQL mySQL;


    @Override
    public void onEnable() {

        createFiles();
        PluginManager manager = Bukkit.getPluginManager();

        GameState.setGamestate(GameState.LOBBY);

        manager.registerEvents(new JoinQuitListener(), this);
        manager.registerEvents(new TeamChat(), this);
        manager.registerEvents(new TeamSelect(), this);
        manager.registerEvents(new TeamInteract(), this);
        manager.registerEvents(new NoBuildListener(), this);
        manager.registerEvents(new InventoryClickListener(), this);
        manager.registerEvents(new SettingsListener(), this);
        manager.registerEvents(new NoDamageListener(), this);

        getCommand("setspawn").setExecutor(new SetSpawncmd());



        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §aDas Plugin wurde erfolgreich aktiviert!");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §aDer Pluginautor ist TeeJan");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §aDie Pluginversion ist die §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §cDas Plugin wurde erfolgreich deaktiviert!");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §cDer Pluginautor ist TeeJan");
        Bukkit.getConsoleSender().sendMessage("§f[]=====[ §cDie Pluginversion ist die §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]============[ §4§L" + getDescription().getName() + " §f]============[]");
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
        configuration.addDefault("MySQL.Database", "lasertag");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");

        configuration.options().copyDefaults(true);
        saveConfig();

    }

}
