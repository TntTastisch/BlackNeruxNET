package de.TntTastisch.BlackNeruxNetworks.listeners;

import de.TntTastisch.BlackNeruxNetworks.system.Data;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onCommand(ChatEvent event){
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if(!player.hasPermission("system.commands.bypass")){

            if(event.getMessage().startsWith("/luckperms") || event.getMessage().startsWith("/lp") ||
                    event.getMessage().startsWith("/luckperms:luckperms") || event.getMessage().startsWith("/perm") ||
                    event.getMessage().startsWith("/perms") || event.getMessage().startsWith("/permission") ||
                    event.getMessage().startsWith("/permissions") || event.getMessage().startsWith("/luckperms:lp") ||
                    event.getMessage().startsWith("/luckperms:luckperms") || event.getMessage().startsWith("/luckperms:perm") ||
                    event.getMessage().startsWith("/luckperms:perms") || event.getMessage().startsWith("/luckperms:permission") ||
                    event.getMessage().startsWith("/luckperms:permissions") || event.getMessage().startsWith("/cloud") ||
                    event.getMessage().startsWith("/cloudnet:resource") || event.getMessage().startsWith("/cloudserver") ||
                    event.getMessage().startsWith("/cloudnetapi:cloudserver") || event.getMessage().startsWith("/resource") ||
                    event.getMessage().startsWith("/cs") || event.getMessage().startsWith("/nte")  || event.getMessage().startsWith("/nametagedit") ||
                    event.getMessage().startsWith("/nametagedit:nte") || event.getMessage().startsWith("/nametagedit:ne") || event.getMessage().startsWith("/nte") ||
                    event.getMessage().startsWith("/professionalbans") || event.getMessage().startsWith("/version") || event.getMessage().startsWith("/ver") ||
                    event.getMessage().startsWith("/about") || event.getMessage().startsWith("/me") || event.getMessage().startsWith("/say") ||
                    event.getMessage().startsWith("/plugins") || event.getMessage().startsWith("/pl")){

                event.setCancelled(true);
                player.sendMessage(new TextComponent(Data.prefix + "§cDieser Befehl ist dem System nicht bekannt!"));

            }
        }

        if(event.getMessage().startsWith("/help") || event.getMessage().startsWith("/?") ||
                event.getMessage().startsWith("/bukkit:help") || event.getMessage().startsWith("/bukkit:?")) {

            event.setCancelled(true);

            player.sendMessage(new TextComponent("§8§m+----------§r §4§lBLACKNERUX NETWORKS§r §8§m----------+§r"));
            player.sendMessage(new TextComponent("§8» §e/lobby §7Kehre zur Lobby zurück"));
            player.sendMessage(new TextComponent("§8» §e/party §7Spiele zusammen mit deinen Freunden"));
            player.sendMessage(new TextComponent("§8» §e/friend §7Öffne dein Freundes Menu"));
            player.sendMessage(new TextComponent("§8» §e/clan §7Öffne das Clan Menu"));
            player.sendMessage(new TextComponent("§8» §e/report §7Melde einen Spieler"));
            player.sendMessage(new TextComponent("§8» §e/link §7Verifiziere dich im TeamSpeak"));
            player.sendMessage(new TextComponent("§8» §e/unlink §7Unverifiziere dich im TeamSpeak"));
            player.sendMessage(new TextComponent("§7Weitere Informationen findest du bei unserem §aForum §8×§b https://www.blacknerux.net/"));
            player.sendMessage(new TextComponent("§7Informationen zu §6den Rängen §7& §6mehr §8×§a https://shop.blacknerux.net/"));
            player.sendMessage(new TextComponent("§7TeamSpeakIP §8× §3BlackNerux.net"));
            player.sendMessage(new TextComponent("§7Folge dem Server auf §5Instagram §8× §c@blackneruxnetworks"));
            player.sendMessage(new TextComponent("§7Folge dem Server auf §bTwitter §8× §c@blacknerux"));
            player.sendMessage(new TextComponent("§8§m+----------§r §4§lBLACKNERUX NETWORKS§r §8§m----------+§r"));

        }

    }
}
