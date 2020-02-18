package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import net.minecraft.server.v1_8_R3.ItemSaddle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Give_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.command.give")){

                if(strings.length < 3){
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/give <Spieler> <Item> <Anzahl> §7oder §e/i <Spieler> <Item> <Anzahl> ");
                } else if(strings.length == 3) {
                    Player target = Bukkit.getPlayer(strings[0]);
                    ItemStack material = new ItemStack(Material.matchMaterial(strings[1]));
                    int anzahl;

                    if (target != null) {
                        try {
                            anzahl = Integer.parseInt(strings[2]);

                            ItemMeta itemMeta = material.getItemMeta();
                            material.setAmount(anzahl);
                            material.setItemMeta(itemMeta);

                            target.getInventory().addItem(material);

                        } catch (NumberFormatException e){
                            player.sendMessage(Data.prefix + "§c" + strings[2] + " ist keine Zahl!");
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
