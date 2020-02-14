package de.TeeJan.BlackNerux.commands;

import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player  = (Player) commandSender;

            if(strings.length == 0){

                int kills = MySQL.getKills(player.getUniqueId().toString());
                int deaths = MySQL.getDeath(player.getUniqueId().toString());
                double kd = (double)kills / (double)deaths;

                player.sendMessage("§8§m---------§r §4§lBLACKNERUX NETWORKS §8§m---------§r");
                player.sendMessage(Data.prefix + "§7Dein Ranking §8× §cComming Soon");
                player.sendMessage(Data.prefix + "§7Deine Kills §8× §e" + kills);
                player.sendMessage(Data.prefix + "§7Deine Tode §8× §e" + deaths);
                player.sendMessage(Data.prefix + "§7Deine K/D §8× §e" + kd);
                player.sendMessage("§8§m---------§r §4§lBLACKNERUX NETWORKS §8§m---------§r");

            } else if(strings.length == 1){
                Player target = Bukkit.getPlayer(strings[0]);

                if(target != null){
                    if(target != player){
                        int kills = MySQL.getKills(target.getUniqueId().toString());
                        int deaths = MySQL.getDeath(target.getUniqueId().toString());
                        double kd = (double)kills / (double)deaths;

                        player.sendMessage("§8§m---------§r §4§lBLACKNERUX NETWORKS §8§m---------§r");
                        player.sendMessage(Data.prefix + "§6" + Data.getPlayerPrefix(target) + " §7Ranking §8× §cComming Soon");
                        player.sendMessage(Data.prefix + "§6" + Data.getPlayerPrefix(target) + " §7Kills §8× §e" + kills);
                        player.sendMessage(Data.prefix + "§6" + Data.getPlayerPrefix(target) + " §7Tode §8× §e" + deaths);
                        player.sendMessage(Data.prefix + "§6" + Data.getPlayerPrefix(target) + " §7K/D §8× §e" + kd);
                        player.sendMessage("§8§m---------§r §4§lBLACKNERUX NETWORKS §8§m---------§r");
                    } else {
                        int kills = MySQL.getKills(player.getUniqueId().toString());
                        int deaths = MySQL.getDeath(player.getUniqueId().toString());
                        double kd = (double)kills / (double)deaths;

                        player.sendMessage("§8§m---------§r §4§lBLACKNERUX NETWORKS §8§m---------§r");
                        player.sendMessage(Data.prefix + "§7Dein Ranking §8× §cComming Soon");
                        player.sendMessage(Data.prefix + "§7Deine Kills §8× §e" + kills);
                        player.sendMessage(Data.prefix + "§7Deine Tode §8× §e" + deaths);
                        player.sendMessage(Data.prefix + "§7Deine K/D §8× §e" + kd);
                        player.sendMessage("§8§m---------§r §4§lBLACKNERUX NETWORKS §8§m---------§r");
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
