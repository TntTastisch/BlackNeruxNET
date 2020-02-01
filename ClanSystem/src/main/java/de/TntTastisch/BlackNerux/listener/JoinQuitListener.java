package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.systems.MySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event){
        ProxiedPlayer player = event.getPlayer();

        MySQL.createPlayer(player.getUniqueId().toString());
    }
}
