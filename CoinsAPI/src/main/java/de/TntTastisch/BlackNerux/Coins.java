package de.TntTastisch.BlackNerux;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class Coins implements CommandExecutor
{
    public boolean onCommand(final CommandSender cs, final Command cmd, final String label, final String[] args) {
        if (!(cs instanceof Player)) {
            return false;
        }
        final Player p = (Player)cs;
        if (args.length == 0) {
            final int coins = CoinsAPI.getCoins(p);

            p.sendMessage(Main.pr + "§7Du hast §e" + coins + " Coins");
        }
        else if (args.length == 1) {
            if (p.hasPermission("system.coins.admin")) {
                if (args[0].equalsIgnoreCase("add")) {
                    p.sendMessage(Main.pr + "§7Benutze §8× §e/§ecoins add <Spieler> <Anzahl>");
                }
                else if (args[0].equalsIgnoreCase("remove")) {
                    p.sendMessage(Main.pr + "§7Benutze §8× §e/§ecoins remove <Spieler> <Anzahl>");
                }
                else {
                    final String name = args[0];
                    if (CoinsAPI.isRegistered(name)) {
                        final int coins2 = CoinsAPI.getCoins(name);
                        p.sendMessage(Main.pr + "§e" + name + "'s §7Coins§8: §a" + coins2);
                    }
                    else {
                        p.sendMessage(Main.pr + "§cDieser Spieler war noch nie auf dem Netzwerk!");
                    }
                }
            }
            else {
                p.sendMessage(Main.pr + "§cDazu hast du keine Rechte!");
            }
        }
        else if (args.length == 2) {
            if (p.hasPermission("system.coins.admin")) {
                if (args[0].equalsIgnoreCase("add")) {
                    p.sendMessage(Main.pr + "§7Benutze §8× §e/§ecoins add <Spieler> <Anzahl>");
                }
                else if (args[0].equalsIgnoreCase("remove")) {
                    p.sendMessage(Main.pr + "§7Benutze §8× §e/§ecoins remove <Spieler> <Anzahl>");
                }
                else {
                    p.sendMessage(Main.pr + "§7Benutze §8× §e/§ecoins <add|remove|set> <Spieler> <Anzahl>");
                }
            }
            else {
                p.sendMessage(Main.pr + "§cDazu hast du keine Rechte!");
            }
        }
        else if (args.length == 3 && p.hasPermission("system.coins.admin")) {
            if (args[0].equalsIgnoreCase("add")) {
                final String name = args[1];
                if (CoinsAPI.isRegistered(name)) {
                    final int coins2 = Integer.valueOf(args[2]);
                    CoinsAPI.addCoins(name, coins2);
                    p.sendMessage(Main.pr + "§7Du hast dem Spieler §e" + name + "§b " + coins2 + " §7Coins §ahinzugefügt§7.");
                }
                else {
                    p.sendMessage(Main.pr + "§cDazu hast du keine Rechte!");
                }
            }
            else if (args[0].equalsIgnoreCase("remove")) {
                if (p.hasPermission("system.coins.admin")) {
                    final String name = args[1];
                    if (CoinsAPI.isRegistered(name)) {
                        final int coins2 = Integer.valueOf(args[2]);
                        CoinsAPI.removeCoins(name, coins2);
                        p.sendMessage(Main.pr + "§7Du hast dem Spieler §e" + name + "§b " + coins2 + " §7Coins §cabgezogen§7.");
                    }
                    else {
                        p.sendMessage(Main.pr + "§cDazu hast du keine Rechte!");
                    }
                }
                else {
                    p.sendMessage(Main.pr + "§7Benutze §8× §e/§ecoins <add|remove|set> <Spieler> <Anzahl>");
                }
            }
        }
        return false;
    }
}
