package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.SignManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sign_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("citybuild.premium.sign")){
                if (strings.length >= 1) {
                    if (player.getItemInHand().getType() != Material.AIR) {
                        if (player.getItemInHand().getAmount() == 1) {
                            final SignManager signManager = new SignManager(player.getItemInHand());
                            if (strings[0].equalsIgnoreCase("del")) {
                                if (signManager.isSigned()) {
                                    player.setItemInHand(signManager.unSign());
                                    player.sendMessage(Data.prefix + "§aDu hast erfolgreich die signatur entfernt.");
                                } else {
                                    player.sendMessage(Data.prefix + "§cDas Item wurde noch nicht signiert.");
                                }
                            } else {
                                final StringBuilder stringBuilder = new StringBuilder();
                                for (final String arg : strings) {
                                    stringBuilder.append(arg).append(" ");
                                }
                                if (!signManager.isSigned()) {
                                    player.setItemInHand(signManager.sign(player.getName(), stringBuilder.toString()));
                                    player.sendMessage(Data.prefix + "§aItem erfolgreich signiert.");
                                } else {
                                    player.sendMessage(Data.prefix + "§cDas Item wurde breits signiert.");
                                }
                            }
                        } else {
                            player.sendMessage(Data.prefix + "§cDu darfst nur ein Item gleichzeitig signieren.");
                        }
                    } else {
                        player.sendMessage(Data.prefix + "§cDu musst ein Item in der Hand halten.");
                    }

                } else {
                    player.sendMessage(Data.prefix + "§7Benutze §8× §e/sign <del / Nachricht>");
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
