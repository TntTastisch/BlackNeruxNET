package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Money_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){
                player.sendMessage(Data.prefix + "§7Dein Kontostand beträgt §8× §e" + MySQL.getMoney(player.getUniqueId().toString()) + "$§7.");
            } else if(strings.length == 1){
                Player target = Bukkit.getPlayer(strings[0]);

                if(target != null){
                    if(target != player){
                        player.sendMessage(Data.prefix + "§7Der Kontostand von §6" + Data.getPlayerPrefix(target) + " §7beträgt §8× §e" + MySQL.getMoney(target.getUniqueId().toString()) + "$§7.");
                    } else {
                        player.sendMessage(Data.prefix + "§7Dein Kontostand beträgt §8× §e" + MySQL.getMoney(player.getUniqueId().toString()) + "$§7.");
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                }
            } else if(strings.length == 3){
                if(player.hasPermission("citybuild.money.administrator")){

                    if(strings[0].equalsIgnoreCase("set")){
                        Player target = Bukkit.getPlayer(strings[1]);
                        Integer money = Integer.parseInt(strings[2]);

                        if(target != null){

                            player.sendMessage(Data.prefix + "§7Der Kontostand von §6"+ Data.getPlayerPrefix(target) + " §7wurde auf §e" + money + "$ §7gesetzt.");
                            target.sendMessage(Data.prefix + "§7Dein Kontostand wurde von §6" + Data.getPlayerPrefix(player) + " §7neu gesetzt. §8(§e" + money + "$§8)");

                            MySQL.setMoney(target.getUniqueId().toString(), money);

                        } else {
                            player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                        }

                    } else if(strings[0].equalsIgnoreCase("add")){
                        Player target = Bukkit.getPlayer(strings[1]);
                        Integer money = Integer.parseInt(strings[2]);

                        if(target != null){

                            int targetBalance = MySQL.getMoney(target.getUniqueId().toString()) + money;

                            player.sendMessage(Data.prefix + "§7Der Kontostand von §6"+ Data.getPlayerPrefix(target) + " §7wurde §e" + money + "$ §7gut geschrieben.");
                            target.sendMessage(Data.prefix + "§7Dein Kontostand wurden von §6" + Data.getPlayerPrefix(player) + " §7auf §e" + targetBalance + "$ §7gesetzt. ");

                            MySQL.addMoney(target.getUniqueId().toString(), money);

                        } else {
                            player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                        }

                    } else if(strings[0].equalsIgnoreCase("remove")){
                        Player target = Bukkit.getPlayer(strings[1]);
                        Integer money = Integer.parseInt(strings[2]);

                        if(target != null){

                            int targetBalance = MySQL.getMoney(target.getUniqueId().toString()) - money;

                            player.sendMessage(Data.prefix + "§7Der Kontostand von §6"+ Data.getPlayerPrefix(target) + " §7wurde §e" + money + "$ §7enfernt.");
                            target.sendMessage(Data.prefix + "§7Dein Kontostand wurden von §6" + Data.getPlayerPrefix(player) + " §7auf §e" + targetBalance + "$ §7gesetzt. ");

                            MySQL.removeMoney(target.getUniqueId().toString(), money);

                        } else {
                            player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                        }

                    }

                } else {
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/money oder /money <Spieler>");
                }
            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
