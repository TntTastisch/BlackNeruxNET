package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchant_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.enchant")){
                if(strings.length < 3){
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/enchant <Spieler> <Enchantment> <Level>");
                    player.sendMessage(Data.prefix + "§7Hilfe §8×§6 http://wiki.mc-ess.net/wiki/Enchantments");
                } else if(strings.length == 3) {
                    Player target = Bukkit.getPlayer(strings[0]);

                    if(target != null){
                        if(target != player){

                            ItemStack is = player.getItemInHand();

                            if (is.getType() != Material.AIR) {

                                Enchantment enchantment = Enchantment.getByName(strings[1].toUpperCase());
                                int level = 1;
                                try {
                                    level = Integer.parseInt(strings[2]);
                                } catch (NumberFormatException e) {
                                    player.sendMessage(Data.prefix + "§c" + strings[2] + " ist keine Zahl!");
                                }

                                try {
                                    ItemMeta im = is.getItemMeta();
                                    is.addUnsafeEnchantment(enchantment, level);
                                    is.setItemMeta(im);
                                    player.sendMessage(Data.prefix + "§7Du hast das Item §6" + is.getType() + " §7mit dem Enchantment §6" + enchantment.getName() + " §7Level " + level + " §7verzaubtert.");
                                    target.setItemInHand(is);
                                } catch (IllegalArgumentException e) {
                                    player.sendMessage(Data.prefix + "§cDieses Enchantment existiert nicht!");
                                }
                            } else {
                                player.sendMessage(Data.prefix + "§cDieser Spieler muss ein Item in der Hand haben!");
                            }

                        } else {
                            ItemStack is = player.getItemInHand();

                            if (is.getType() != Material.AIR) {

                                Enchantment enchantment = Enchantment.getByName(strings[1].toUpperCase());
                                int level = 1;
                                try {
                                    level = Integer.parseInt(strings[2]);
                                } catch (NumberFormatException e) {
                                    player.sendMessage(Data.prefix + "§c" + strings[2] + " ist keine Zahl!");
                                }

                                try {
                                    ItemMeta im = is.getItemMeta();
                                    is.addUnsafeEnchantment(enchantment, level);
                                    is.setItemMeta(im);
                                    player.sendMessage(Data.prefix + "§7Du hast das Item §6" + is.getType() + " §7mit dem Enchantment §6" + enchantment.getName() + " §7Level " + level + " §7verzaubtert.");
                                    player.setItemInHand(is);
                                } catch (IllegalArgumentException e) {
                                    player.sendMessage(Data.prefix + "§cDieses Enchantment existiert nicht!");
                                }
                            } else {
                                player.sendMessage(Data.prefix + "§cDu musst ein Item in der Hand halten!");
                            }
                        }
                    } else {
                        player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                    }
                }
            } else {
                player.sendMessage(Data.noPerms);
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
