package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/pay <Spieler> <Betrag>");
            } else if(strings.length != 2){
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/pay <Spieler> <Betrag>");
            } else if(strings.length == 2){
                Player target = Bukkit.getPlayer(strings[0]);
                int money = Integer.parseInt(strings[1]);

                if(target != null){
                    if(target != player){

                        player.sendMessage(Data.prefix + "§7Du hast dem Spieler §6" + Data.getPlayerPrefix(target) + " §e" + money + "$ §7geschickt.");
                        target.sendMessage(Data.prefix + "§7Du hast von dem Spieler §6" + Data.getPlayerPrefix(player) + " §e" + money + "$ §7geschickt bekommen.");

                        MySQL.removeMoney(player.getUniqueId().toString(), money);
                        MySQL.addMoney(target.getUniqueId().toString(), money);

                    } else {
                        player.sendMessage(Data.prefix + "§cDu kanns kein Geld an dich selber senden!");
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
