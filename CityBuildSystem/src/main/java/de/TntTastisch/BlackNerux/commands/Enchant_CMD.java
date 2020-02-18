package de.TntTastisch.BlackNerux.commands;

import com.google.gson.internal.$Gson$Preconditions;
import de.TntTastisch.BlackNerux.api.ItemAPI;
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
                if(strings.length < 2){
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/enchant <Enchantment> <Level>");
                    player.sendMessage(Data.prefix + "§7Hilfe §8×§6 http://wiki.mc-ess.net/wiki/Enchantments");
                } else if(strings.length == 2) {

                    if (player.getItemInHand().getType() != Material.AIR) {

                        Enchantment enchantment = Enchantment.getByName(strings[0].toUpperCase());
                        int level = 1;
                        try {
                            level = Integer.parseInt(strings[1]);
                        } catch (NumberFormatException e) {
                            player.sendMessage(Data.prefix + "§c" + strings[1] + " ist keine Zahl!");
                        }

                        try {
                            ItemStack itemStack = player.getItemInHand();
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemStack.addUnsafeEnchantment(enchantment, level);
                            itemStack.setItemMeta(itemMeta);

                            player.sendMessage(Data.prefix + "§7Du hast das Item §6" + player.getItemInHand().getType()
                                    + " §7mit dem Enchantment §6" + enchantment.getName() + " §7Level " + level + " §7verzaubtert.");

                        } catch (IllegalArgumentException e) {
                            player.sendMessage(Data.prefix + "§cDieses Enchantment existiert nicht!");
                        }
                    } else {
                        player.sendMessage(Data.prefix + "§cDieser Spieler muss ein Item in der Hand haben!");
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
