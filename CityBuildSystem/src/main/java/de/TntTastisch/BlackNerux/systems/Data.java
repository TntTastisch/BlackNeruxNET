package de.TntTastisch.BlackNerux.systems;

import org.bukkit.entity.Player;

public class Data {

    public static String prefix = "§8» §e§lCITYBUILD §7▏ §7";
    public static String prefixSQL = "§8[§cCityBuildSQL§8] §7";
    public static String noPerms = "§cYou do not have permission to execute this command!";
    public static String noPlayer = prefix + "§cYou musst be a player!";

    public static String getPlayerPrefix(Player player){
        if(player.hasPermission("prefix.owner")){
            return "§4Owner §8▏ §4" + player.getName();
        } else if(player.hasPermission("prefix.admin")){
            return "§cAdministrator §8▏ §c" + player.getName();
        } else if(player.hasPermission("prefix.srmod")){
            return "§2SrModerator §8▏ §2" + player.getName();
        } else if(player.hasPermission("prefix.dev")){
            return "§bDeveloper §8▏ §b" + player.getName();
        } else if(player.hasPermission("prefix.mod")){
            return "§2Moderator §8▏ §2" + player.getName();
        } else if(player.hasPermission("prefix.sup")){
            return "§aSupporter §8▏ §a" + player.getName();
        } else if(player.hasPermission("prefix.builder")){
            return "§9Builder §8▏ §9" + player.getName();
        } else if(player.hasPermission("prefix.designer")){
            return "§eDesigner §8▏ §3" + player.getName();
        } else if(player.hasPermission("prefix.freund")){
            return "§dFreund §8▏ §d" + player.getName();
        } else if(player.hasPermission("prefix.twitch")){
            return "§5Twitch  §8▏ §5" + player.getName();
        } else if(player.hasPermission("prefix.youtuber")){
            return "§fYTber §8▏ §f" + player.getName();
        } else if(player.hasPermission("prefix.platinplus")){
            return "§7Platin§a+§7 §8▏ §7" + player.getName();
        } else if(player.hasPermission("prefix.lapis")){
            return "§9Lapis §7§8▏ §1" + player.getName();
        } else if(player.hasPermission("prefix.platin")){
            return "§7Platin §7§8▏ §7" + player.getName();
        } else if(player.hasPermission("prefix.obsidian")){
            return "§0Obsidian §7§8▏ §§0" + player.getName();
        } else if(player.hasPermission("prefix.titan")){
            return "§1Titan §7§8▏ §1" + player.getName();
        } else if(player.hasPermission("prefix.diamond")){
            return "§3Diamond §7§8▏ §3" + player.getName();
        } else if(player.hasPermission("prefix.emerald")){
            return "§aEmerald §7§8▏ §a" + player.getName();
        } else if(player.hasPermission("prefix.bronze")){
            return "§6Bronze §7§8▏ §6" + player.getName();
        } else if(player.hasPermission("prefix.spieler")){
            return "§8Spieler §8▏ §8" + player.getName();
        }
        return null;
    }
}
