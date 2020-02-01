package de.TntTastisch.BlackNerux.systems;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Data {

    public static String prefix = "§8» §6§lLOBBY §7▏ §7";
    public static String mysql = "§8➦ §6§lLOBBY-SQL §8× §7";
    public static String globalPrefix = "§8» §4§lBlackNerux.net §7▏ §7";
    public static String noPermission = "§cYou do not have permission to execute this command!";
    public static String noPlayer = prefix + "§cYou musst be a player!";


    public static ArrayList<Player> build = new ArrayList<Player>();


    // final CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
    public static void connectPlayer(CloudPlayer cloudPlayer, String server) {
        PlayerExecutorBridge executorBridge = PlayerExecutorBridge.INSTANCE;
        executorBridge.sendPlayer(cloudPlayer, server);
    }

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

    public static void setSubtitle( Player receiver, UUID subtitlePlayer, String value ) {
        // List of all subtitles
        JsonArray array = new JsonArray();

        // Add subtitle
        JsonObject subtitle = new JsonObject();
        subtitle.addProperty( "uuid", subtitlePlayer.toString() );

        // Optional: Size of the subtitle
        subtitle.addProperty( "size", 0.8d ); // Range is 0.8 - 1.6 (1.6 is Minecraft default)

        // no value = remove the subtitle
        if(value != null)
            subtitle.addProperty( "value", value );

        // You can set multible subtitles in one packet
        array.add(subtitle);

        // Send to LabyMod using the API
        LabyModPlugin.getInstance().sendServerMessage( receiver, "account_subtitle", array );
    }


    public static String getRank(Player player){
        if(player.hasPermission("prefix.owner")){
            return "§4Owner";
        } else if(player.hasPermission("prefix.admin")){
            return "§cAdministrator";
        } else if(player.hasPermission("prefix.srmod")){
            return "§2SrModerato";
        } else if(player.hasPermission("prefix.dev")){
            return "§bDeveloper";
        } else if(player.hasPermission("prefix.mod")){
            return "§2Moderator";
        } else if(player.hasPermission("prefix.sup")){
            return "§aSupporter";
        } else if(player.hasPermission("prefix.builder")){
            return "§9Builder";
        } else if(player.hasPermission("prefix.designer")){
            return "§eDesigner";
        } else if(player.hasPermission("prefix.freund")){
            return "§dFreund";
        } else if(player.hasPermission("prefix.twitch")){
            return "§5Twitch";
        } else if(player.hasPermission("prefix.youtuber")){
            return "§fYT";
        } else if(player.hasPermission("prefix.platinplus")){
            return "§7Platin§a+§7";
        } else if(player.hasPermission("prefix.lapis")){
            return "§9Lapis";
        } else if(player.hasPermission("prefix.platin")){
            return "§7Platin";
        } else if(player.hasPermission("prefix.obsidian")){
            return "§0Obsidian";
        } else if(player.hasPermission("prefix.titan")){
            return "§1Titan";
        } else if(player.hasPermission("prefix.diamond")){
            return "§3Diamond";
        } else if(player.hasPermission("prefix.emerald")){
            return "§aEmerald";
        } else if(player.hasPermission("prefix.bronze")){
            return "§6Bronze";
        } else if(player.hasPermission("prefix.spieler")){
            return "§8Spieler";
        }
        return null;
    }
}
