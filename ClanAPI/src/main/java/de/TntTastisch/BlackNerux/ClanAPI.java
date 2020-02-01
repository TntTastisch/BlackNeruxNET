package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.system.ClanSQL;
import de.TntTastisch.BlackNerux.system.PlaceholderLib;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ClanAPI extends JavaPlugin {

    public FileConfiguration configuration = this.getConfig();
    public static ClanSQL mySQL;

    @Override
    public void onEnable() {
        createFiles();
        ClanSQL.connect();
        final Plugin placeHolderAPI;

        placeHolderAPI = this.getServer().getPluginManager().getPlugin("PlaceholderAPI");
        if (placeHolderAPI != null && placeHolderAPI.getDescription() != null) {
            new PlaceholderLib().hook();
        }
    }

    @Override
    public void onDisable(){
        ClanSQL.disconnect();
    }

    public void createFiles(){

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "clan");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");
        configuration.options().copyDefaults(true);
        saveConfig();
    }
}
