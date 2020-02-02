package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.CityBuildSystem;
import de.TntTastisch.BlackNerux.api.effects.TpaEffect;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Annehmen_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(strings.length == 0){
                final Player target = Tpa_CMD.tpa.get(player);

                if(Tpa_CMD.tpa.containsKey(player)) {
                    if (Tpa_CMD.tpa.containsKey(target)) {
                        TpaEffect.teleportationStart(target);
                        player.sendMessage(Data.prefix + "§7Du hast die TPA-Anfrage von §6" + Data.getPlayerPrefix(target) + " §aangenommen§7. §7Du wirst in §c3 Sekunden §ateleportiert§7!");
                        target.sendMessage(Data.prefix + Data.getPlayerPrefix(player) + " §7hat deine TPA-Anfrage §aangenommen§7.");

                        Bukkit.getScheduler().runTaskLater(CityBuildSystem.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                target.teleport(player);
                                TpaEffect.run.get(target).cancel();
                                TpaEffect.run.remove(target);

                                Tpa_CMD.tpa.remove(player, target);
                                Tpa_CMD.tpa.remove(target, player);
                            }
                        }, 20L * 3);
                    }
                } else {
                    player.sendMessage(Data.prefix + "§cDu hast keine TPA-Anfrage erhalten!");
                }
            } else if (strings.length >= 1) {
                player.sendMessage(Data.prefix + "§7Benutze §8× §e/annehmen");
            }

        } else {
            commandSender.sendMessage(Data.noPlayer);
        }
        return false;
    }
}
