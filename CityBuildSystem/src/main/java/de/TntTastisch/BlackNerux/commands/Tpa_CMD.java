package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.soap.Text;
import java.awt.*;
import java.util.HashMap;

public class Tpa_CMD implements CommandExecutor {

    public static HashMap<Player, Player> tpa = new HashMap<>();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/tpa <Spieler>");
            } else if(strings.length == 1){
                Player target = Bukkit.getPlayer(strings[0]);

                if(target != null){
                    if(target != player){
                        if(MySQL.getTpaToggle(target.getUniqueId().toString()) == 1){
                            tpa.put(player, target);
                            tpa.put(target, player);

                            player.sendMessage(Data.prefix + "§7Du hast eine §cTPA-Anfrage §7an §6" + Data.getPlayerPrefix(target) + " §6gesendet.§7.");
                            target.sendMessage(Data.prefix + Data.getPlayerPrefix(player) + " §7möchte sich zu dir teleportieren.");
                            target.sendMessage(Data.prefix + "§7Benutze §a/annehmen§7, um anzunehmen oder §c/ablehnen§7, um abzulehnen.");


                        } else if(MySQL.getTpaToggle(target.getUniqueId().toString()) == 0){
                            player.sendMessage(Data.prefix + "§cDieser Spieler hat seine TPA-Anfragen deaktiviert.");
                        }
                    } else {
                        player.sendMessage(Data.prefix + "§cDu kannst dir nicht selbst eine TPA-Anfrage senden!");
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                }

            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
