package de.TntTastisch.BlackNerux.listeners;

import com.google.gson.internal.$Gson$Preconditions;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import javax.swing.*;

public class SocialSpyListener implements Listener {


    @EventHandler
    public void onProcess(PlayerCommandPreprocessEvent event){
        String cmd = event.getMessage();
        Player sender = event.getPlayer();

        for(Player all : Bukkit.getOnlinePlayers()) {
            if (MySQL.getSocialSpy(all.getUniqueId().toString()) == 1) {
                if(cmd.substring(1, 4).equals("msg") || cmd.substring(1, 4).equals("r")) {
                    all.sendMessage(Data.prefixNachrichten + Data.getPlayerPrefix(sender) + " §7»§b" + cmd.substring(4));

                }
            }
        }

    }
}
