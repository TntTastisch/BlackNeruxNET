package de.TeeJan.BlackNerux.commands;

import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.OneHit;
import de.TeeJan.BlackNerux.utils.SpawnConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn_CMD implements CommandExecutor {

    public boolean onCommand(CommandSender Sender, Command cmd, String laber, String[] args) {

        Player p = (Player) Sender;

        if(Sender instanceof Player){
            if(p.hasPermission("onehit.comamnd.setspawn")){
                SpawnConfig.setLocation(p, OneHit.filecfg, OneHit.file, "Spawn");
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
