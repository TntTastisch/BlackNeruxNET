package de.TntTastisch.BlackNeruxNetworks.listeners;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.LinkedList;
import java.util.List;

public class AntiTabListener implements Listener {

    public static final List<String> bypasstabcomplete = new LinkedList<String>();

    static {

    }

    @EventHandler(priority = 64)
    public void onTab(final TabCompleteEvent e) {
        if (e.getCursor().startsWith("/")) {
            final ProxiedPlayer player = BungeeCord.getInstance().getPlayer(e.getSender().toString());
            if (player == null) {
                return;
            }
            if (player.hasPermission("system.command.bypasstab")) {
                return;
            }
            if (this.isallowdToTab(e.getCursor())) {
                return;
            }
            e.setCancelled(true);
        }
    }

    private Boolean isallowdToTab(final String message) {
        for (final String messages : bypasstabcomplete) {
            if (message.startsWith(messages)) {
                return true;
            }
        }
        return false;
    }
}
