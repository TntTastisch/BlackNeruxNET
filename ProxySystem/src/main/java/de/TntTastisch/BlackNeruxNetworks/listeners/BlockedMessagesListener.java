package de.TntTastisch.BlackNeruxNetworks.listeners;

import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import de.dytanic.cloudnet.api.CloudAPI;
import de.tutorialwork.professionalbans.utils.BanManager;
import de.tutorialwork.professionalbans.utils.IPManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BlockedMessagesListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent event){
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        String message = event.getMessage().trim();

        if(event.getMessage().startsWith("/mute")){
            event.setCancelled(true);
            player.sendMessage(new TextComponent(Data.prefix + "§cDieser Befehl ist dem System nicht bekannt!"));
        }

        List<String> filtersWithBan = new ArrayList<String>();
        filtersWithBan.add("卐");
        filtersWithBan.add("卍");
        filtersWithBan.add("ᛋᛋ");

        for(final String blocked : filtersWithBan){

            if(message.contains(blocked)){
                Date today = Calendar.getInstance().getTime();
                DateFormat tm = new SimpleDateFormat("HH:mm:ss");
                TimeZone timezone = TimeZone.getTimeZone("Europe/Berlin");
                tm.setTimeZone(timezone);
                String Time = tm.format(today);


                event.setCancelled(true);
                MySQL.createBlockedLogs(player.getUniqueId().toString(), player.getAddress().getAddress().getHostAddress(),
                        CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId()).getServer(), message, Time);

                IPManager.ban(player.getAddress().getAddress().getHostAddress(), 9, "KONSOLE");
                BanManager.ban(player.getUniqueId().toString(), 9, "KONSOLE", 100, false);
                player.disconnect("§7» §4§lBLACKNERUX NETWORKS §7«\n§c§lDu wurdest §4§lPERMANENT §c§lvom Netzwerk gebannt!\n\n§c§lGrund §8➟ §4§lRassismus | Rassistisches Verhalten\n\n§aDu kannst keinen Entbannungsantrag unter \n§ehttps://forum.blacknerux.net §astellen.");
            }

        }


    }
}
