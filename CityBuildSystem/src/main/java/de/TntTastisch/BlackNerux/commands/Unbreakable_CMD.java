package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Unbreakable_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.unbreakable")){
                ItemStack itemStack = player.getItemInHand();

                if(itemStack.getType() != Material.AIR){
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.spigot().setUnbreakable(true);
                    itemStack.setItemMeta(itemMeta);
                    player.getInventory().setItemInHand(itemStack);

                    player.sendMessage(Data.prefix + "§7Du hast das Item §6" + itemStack.getType() + "§7 wurde §cunzerstörbar §7gemacht!");
                } else {
                    player.sendMessage(Data.prefix + "§cDu musst ein Item in der Hand halten!");
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
