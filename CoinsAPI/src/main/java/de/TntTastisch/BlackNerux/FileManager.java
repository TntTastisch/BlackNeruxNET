package de.TntTastisch.BlackNerux;

import org.bukkit.configuration.file.FileConfiguration;

public class FileManager {

    public static FileConfiguration configuration = Main.getPlugin(Main.class).getConfig();

    public static void loadConfiguration(){

        if(!Main.getPlugin(Main.class).getDataFolder().exists()){
            Main.getPlugin(Main.class).getDataFolder().mkdir();
        }

        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", 3306);
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", "password");
        configuration.addDefault("MySQL.Database", "coinsapi");
        configuration.options().copyDefaults(true);
        Main.getPlugin(Main.class).saveConfig();
    }
}
