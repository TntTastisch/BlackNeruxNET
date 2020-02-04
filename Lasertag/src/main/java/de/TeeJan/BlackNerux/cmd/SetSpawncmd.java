package de.TeeJan.BlackNerux.cmd;

import de.TeeJan.BlackNerux.LaserTag;
import de.TeeJan.BlackNerux.systems.Data;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawncmd implements CommandExecutor {

    public boolean onCommand(CommandSender Sender, Command cmd, String laber, String[] args) {

        Player p = (Player) Sender;

        if(Sender instanceof Player){
            if(p.hasPermission("setspawn.lobby")){
            SpawnConfig.setLocation(p, LaserTag.fileCFG, LaserTag.file, "spawn");
            p.sendMessage(Data.prefix + "§7Du hast den §6Spawn §aerfolgreich §7gesetzt!");
         }else{
                p.sendMessage(Data.noPerms);

            }

        }else{
            Bukkit.getConsoleSender().sendMessage(Data.noPlayer);

        }

        return false;
    }
}
