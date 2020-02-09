package de.TntTastisch.BlackNeruxNetworks.listeners;

import com.google.gson.JsonObject;
import de.TntTastisch.BlackNeruxNetworks.ProxySystem;
import de.TntTastisch.BlackNeruxNetworks.system.Data;
import de.TntTastisch.BlackNeruxNetworks.system.MySQL;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.labymod.serverapi.LabyModAPI;
import net.labymod.serverapi.bungee.LabyModPlugin;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import sun.awt.image.SurfaceManager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;

import java.nio.charset.Charset;
import java.util.UUID;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event){
        ProxiedPlayer player = event.getPlayer();

        MySQL.createPlayer(player);


        if(MySQL.getIp(player.getUniqueId().toString()) != player.getAddress().getAddress().getHostAddress()) {
            MySQL.setIp(player.getUniqueId().toString(), player.getAddress().getAddress().getHostAddress());
        }

        for(int i = 0; i != 100; i++){
            player.sendMessage(new TextComponent(" "));
        }
        if(player.hasPermission("system.staff.teamchat")){
            if(MySQL.getTeamChat(player.getUniqueId().toString()) == 0){

                player.sendMessage(new TextComponent(Data.prefixTeamChat + "§7Du bist §causgeloggt§7."));

            } else if(MySQL.getTeamChat(player.getUniqueId().toString()) == 1){

                player.sendMessage(new TextComponent(Data.prefixTeamChat + "§7Du bist §aeingeloggt§7."));

            }
        }

        if(player.hasPermission("system.staff.report")){
            if(MySQL.getReport(player.getUniqueId().toString()) == 0){

                player.sendMessage(new TextComponent(Data.prefixReport + "§7Du bist §causgeloggt§7."));

            } else if(MySQL.getReport(player.getUniqueId().toString()) == 1){

                player.sendMessage(new TextComponent(Data.prefixReport + "§7Du bist §aeingeloggt§7."));

            }
        }

        sendCurrentPlayingGamemode(player, true, "§4BlackNerux §8× §e" + player.getServer().getInfo().getName());
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event){
        ProxiedPlayer player = event.getPlayer();
    }

    public static void sendCurrentPlayingGamemode(ProxiedPlayer player, boolean visible, String gamemodeName ) {
        JsonObject object = new JsonObject();
        object.addProperty( "show_gamemode", visible ); // Gamemode visible for everyone
        object.addProperty( "gamemode_name", gamemodeName ); // Name of the current playing gamemode

        // Send to LabyMod using the API
        LabyModPlugin.getInstance().sendServerMessage(player, "server_gamemode", object );
    }

    @EventHandler
    public void onSwitch(ServerSwitchEvent event){
        ProxiedPlayer player = event.getPlayer();
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

        sendCurrentPlayingGamemode(player, true, "§4BlackNeruxNET §8× §e" + cloudPlayer.getServer());

    }


}
