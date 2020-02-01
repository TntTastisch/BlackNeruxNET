package de.TntTastisch.BlackNerux;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.command.*;

public class Main extends JavaPlugin implements Listener
{
    public static String pr;
    
    static {
        Main.pr = "§8» §b§lCOINS §7▏ §7";
    }
    
    public void onEnable() {
        FileManager.loadConfiguration();
        MySQL.connect();
        CoinsAPI.createTable();
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("coins").setExecutor((CommandExecutor)new Coins());

    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!CoinsAPI.isRegistered(p)) {
            CoinsAPI.register(p);
        }
    }
}
