package de.TntTastisch.BlackNerux.utils;

import de.TntTastisch.AronixDE.OnlineInstance;
import de.TntTastisch.AronixDE.OnlineTimeAPI;
import de.TntTastisch.BlackNerux.CityBuildSystem;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.systems.MySQL;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class ScoreboardManager {


    public static void updateScoreboard(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CityBuildSystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    setScoreboard(all);
                }
            }
        }, 0L, 20 * 1);

    }

    public static void setScoreboard(Player player){
        final Scoreboard scoreboard = new Scoreboard();
        final ScoreboardObjective obj = scoreboard.registerObjective("citybuild", IScoreboardCriteria.b);
        obj.setDisplayName("§4§lBLACKNERUX.NET");
        final PacketPlayOutScoreboardObjective removepacket = new PacketPlayOutScoreboardObjective(obj, 1);
        final PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        final PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);

        sendPacket(player, (Packet<?>)removepacket);
        sendPacket(player, (Packet<?>)createpacket);
        sendPacket(player, (Packet<?>)display);


        int playerCount = Bukkit.getOnlinePlayers().size();

        for(Player all : Bukkit.getOnlinePlayers()){

            if(MySQL.getVanish(all.getUniqueId().toString()) == 1){
                playerCount--;
            }
        }

        final ScoreboardScore s0 = new ScoreboardScore(scoreboard, obj, "§d§a§d§f§4§5");
        s0.setScore(15);
        final PacketPlayOutScoreboardScore ps0 = new PacketPlayOutScoreboardScore(s0);
        sendPacket(player, (Packet<?>)ps0);

        final ScoreboardScore s1 = new ScoreboardScore(scoreboard, obj, "§8➥ §f§lDein Rang");
        s1.setScore(14);
        final PacketPlayOutScoreboardScore ps1 = new PacketPlayOutScoreboardScore(s1);
        sendPacket(player, (Packet<?>)ps1);

        final ScoreboardScore s2 = new ScoreboardScore(scoreboard, obj, "§7➟ §4" + Data.getRank(player));
        s2.setScore(13);
        final PacketPlayOutScoreboardScore ps2 = new PacketPlayOutScoreboardScore(s2);
        sendPacket(player, (Packet<?>)ps2);


        final ScoreboardScore s3 = new ScoreboardScore(scoreboard, obj, "§a§0§4§5§f§r");
        s3.setScore(12);
        final PacketPlayOutScoreboardScore ps3 = new PacketPlayOutScoreboardScore(s3);
        sendPacket(player, (Packet<?>)ps3);

        final ScoreboardScore s13 = new ScoreboardScore(scoreboard, obj, "§8➥ §f§lOnlinezeit");
        s13.setScore(11);
        final PacketPlayOutScoreboardScore ps13 = new PacketPlayOutScoreboardScore(s13);
        sendPacket(player, (Packet<?>)ps13);

        final ScoreboardScore s14 = new ScoreboardScore(scoreboard, obj, "§7➟ §b" + OnlineInstance.getHours(player) +"§3h");
        s14.setScore(10);
        final PacketPlayOutScoreboardScore ps14 = new PacketPlayOutScoreboardScore(s14);
        sendPacket(player, (Packet<?>)ps14);

        final ScoreboardScore s15 = new ScoreboardScore(scoreboard, obj, "§d§l§a§5§4§3§2§f§d§f§5§6§7§f§5");
        s15.setScore(9);
        final PacketPlayOutScoreboardScore ps15 = new PacketPlayOutScoreboardScore(s15);
        sendPacket(player, (Packet<?>)ps15);

        final ScoreboardScore s4 = new ScoreboardScore(scoreboard, obj, "§8➥ §f§lKontostand");
        s4.setScore(8);
        final PacketPlayOutScoreboardScore ps4 = new PacketPlayOutScoreboardScore(s4);
        sendPacket(player, (Packet<?>)ps4);

        final ScoreboardScore s5 = new ScoreboardScore(scoreboard, obj, "§7➟ §e" + MySQL.getMoney(player.getUniqueId().toString()) + "$");
        s5.setScore(7);
        final PacketPlayOutScoreboardScore ps5 = new PacketPlayOutScoreboardScore(s5);
        sendPacket(player, (Packet<?>)ps5);

        final ScoreboardScore s6 = new ScoreboardScore(scoreboard, obj, "§a§0§4§5§f§r§9§d§4");
        s6.setScore(6);
        final PacketPlayOutScoreboardScore ps6 = new PacketPlayOutScoreboardScore(s6);
        sendPacket(player, (Packet<?>)ps6);

        final ScoreboardScore s7 = new ScoreboardScore(scoreboard, obj, "§8➥ §f§lOnline");
        s7.setScore(5);
        final PacketPlayOutScoreboardScore ps7 = new PacketPlayOutScoreboardScore(s7);
        sendPacket(player, (Packet<?>)ps7);

        if (!player.hasPermission("citybuild.command.vanish.bypass") || !player.hasPermission("citybuild.command.vanish")) {
            final ScoreboardScore s8 = new ScoreboardScore(scoreboard, obj, "§7➟ §a" + playerCount + "§8/§c" + Bukkit.getMaxPlayers());
            s8.setScore(4);
            final PacketPlayOutScoreboardScore ps8 = new PacketPlayOutScoreboardScore(s8);
            sendPacket(player, (Packet<?>) ps8);
        } else {
            final ScoreboardScore s8 = new ScoreboardScore(scoreboard, obj, "§7➟ §a" + Bukkit.getOnlinePlayers().size() + "§8/§c" + Bukkit.getMaxPlayers());
            s8.setScore(4);
            final PacketPlayOutScoreboardScore ps8 = new PacketPlayOutScoreboardScore(s8);
            sendPacket(player, (Packet<?>) ps8);
        }

        final ScoreboardScore s9 = new ScoreboardScore(scoreboard, obj, "§d§l§a§5§4§3§2§f§d§f");
        s9.setScore(3);
        final PacketPlayOutScoreboardScore ps9 = new PacketPlayOutScoreboardScore(s9);
        sendPacket(player, (Packet<?>)ps9);

        final ScoreboardScore s10 = new ScoreboardScore(scoreboard, obj, "§8➥ §f§lJob");
        s10.setScore(2);
        final PacketPlayOutScoreboardScore ps10 = new PacketPlayOutScoreboardScore(s10);
        sendPacket(player, (Packet<?>)ps10);

        if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Arbeitslos")) {
            final ScoreboardScore s11 = new ScoreboardScore(scoreboard, obj, "§7➟ §cArbeitslos");
            s11.setScore(1);
            final PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
            sendPacket(player, (Packet<?>) ps11);
        } else if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Minenarbeiter")){
            final ScoreboardScore s11 = new ScoreboardScore(scoreboard, obj, "§7➟ §eMinenarbeiter");
            s11.setScore(1);
            final PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
            sendPacket(player, (Packet<?>) ps11);
        } else if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Metzger")){
            final ScoreboardScore s11 = new ScoreboardScore(scoreboard, obj, "§7➟ §4Metzger");
            s11.setScore(1);
            final PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
            sendPacket(player, (Packet<?>) ps11);
        } else if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Holzfäller")){
            final ScoreboardScore s11 = new ScoreboardScore(scoreboard, obj, "§7➟ §2Holzfäller");
            s11.setScore(1);
            final PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
            sendPacket(player, (Packet<?>) ps11);
        }

        final ScoreboardScore s12 = new ScoreboardScore(scoreboard, obj, "§d§l§a§5§4§3§2§f§d§f§5§6§7");
        s12.setScore(0);
        final PacketPlayOutScoreboardScore ps12 = new PacketPlayOutScoreboardScore(s12);
        sendPacket(player, (Packet<?>)ps12);



    }


    public static void sendPacket(final Player p, final Packet<?> packet) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
    }


    /*
            objective.getScore("§d§l§a§5§4§3§2").setScore(15);
        objective.getScore("§8➥ §f§lDein Rang").setScore(14);
        objective.getScore("§7➟ §4" + Data.getRank(player)).setScore(13);
        objective.getScore("§d§l§a§5§4§3§2§f").setScore(12);
        objective.getScore("§8➥ §f§lKontostand").setScore(11);
        objective.getScore("§7➟ §e" + PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance_fixed%") + "$").setScore(10);
        objective.getScore("§d§l§a§5§4§3§2§f§d").setScore(9);
        objective.getScore("§8➥ §f§lOnline").setScore(8);
        if (!player.hasPermission("citybuild.command.vanish.bypass") || !player.hasPermission("citybuild.command.vanish")) {
            objective.getScore("§7➟ §a" + playerCount + "§8/§c" + Bukkit.getMaxPlayers()).setScore(7);
        } else {
            objective.getScore("§7➟ §a" + Bukkit.getOnlinePlayers().size() + "§8/§c" + Bukkit.getMaxPlayers()).setScore(7);
        }
        objective.getScore("§d§l§a§5§4§3§2§f§d§f").setScore(6);
        objective.getScore("§8➥ §f§lJob").setScore(5);
        if(MySQL.getJob(player.getUniqueId().toString()).equalsIgnoreCase("Arbeitslos")) {
            objective.getScore("§7➟ §cArbeitslos").setScore(4);
        }
        objective.getScore("§d§l§a§5§4§3§2§f§d§f§5§6§7").setScore(3);
     */
}
