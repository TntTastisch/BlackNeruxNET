package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.commands.*;
import de.TntTastisch.BlackNerux.listeners.*;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

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
        manager.registerEvents(new DeathListener(), this);
        manager.registerEvents(new SocialSpyListener(), this);

        // Team Features
        this.getCommand("gamemode").setExecutor(new GameMode_CMD());
        this.getCommand("vanish").setExecutor(new Vanish_CMD());
        this.getCommand("fly").setExecutor(new Fly_CMD());
        // Features
        this.getCommand("feed").setExecutor(new Feed_CMD());
        this.getCommand("heal").setExecutor(new Heal_CMD());
        // Location
        this.getCommand("setspawn").setExecutor(new SetSpawn_CMD());
        this.getCommand("spawn").setExecutor(new Spawn_CMD());
        // Teleport
        this.getCommand("teleport").setExecutor(new Teleport_CMD());
        this.getCommand("tpall").setExecutor(new Tpall_CMD());
        this.getCommand("tphere").setExecutor(new Tphere_CMD());
        // Warp
        this.getCommand("delwarp").setExecutor(new DelWarp_CMD());
        this.getCommand("setwarp").setExecutor(new SetWarp_CMD());
        this.getCommand("warp").setExecutor(new Warp_CMD());
        // Home
        this.getCommand("sethome").setExecutor(new SetHome_CMD());
        this.getCommand("delhome").setExecutor(new DelHome_CMD());
        this.getCommand("home").setExecutor(new Home_CMD());
        // TPA
        this.getCommand("tpa").setExecutor(new Tpa_CMD());
        this.getCommand("tpatoggle").setExecutor(new TpaToggle_CMD());
        this.getCommand("tpahere").setExecutor(new Tpahere_CMD());
        this.getCommand("tpaall").setExecutor(new Tpaall_CMD());
        this.getCommand("annehmen").setExecutor(new Annehmen_CMD());
        this.getCommand("ablehnen").setExecutor(new Ablehnen_CMD());
        // MSG
        this.getCommand("socialspy").setExecutor(new SocialSpy_CMD());
        this.getCommand("msgtoggle").setExecutor(new MsgToggle_CMD());
        this.getCommand("msg").setExecutor(new Msg_CMD());
        this.getCommand("r").setExecutor(new R_CMD());

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

        File data = new File("plugins/CityBuildSystem/data");

        if(!data.exists()){
            data.mkdir();
        }

        if(!LocationManager.locations.exists()){
            try {
                LocationManager.locations.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!Warp_CMD.warps.exists()){
            try {
                Warp_CMD.warps.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!Home_CMD.homes.exists()){
            try {
                Home_CMD.homes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
