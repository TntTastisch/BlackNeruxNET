package de.TntTastisch.BlackNerux.utils;

import de.TntTastisch.AronixDE.OnlineInstance;
import de.TntTastisch.BlackNerux.CoinsAPI;
import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.systems.Data;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardManager {

    public static void setScoreboard(Player player){
        final Scoreboard sb = new net.minecraft.server.v1_8_R3.Scoreboard();
        final ScoreboardObjective obj = sb.registerObjective("lbavdf", IScoreboardCriteria.b);
        Integer onlinetime = OnlineInstance.getHours(player);
        Integer coins = CoinsAPI.getCoins(player);

        obj.setDisplayName("§4§lBLACKNERUX.NET");
        final PacketPlayOutScoreboardObjective add = new PacketPlayOutScoreboardObjective(obj, 0);
        final PacketPlayOutScoreboardDisplayObjective adDisplay = new PacketPlayOutScoreboardDisplayObjective(1, obj);
        final ScoreboardScore s1 = new ScoreboardScore(sb, obj, "§a§3§4§a");
        final ScoreboardScore s2 = new ScoreboardScore(sb, obj, "§8➥ §f§lDein Rang");
        final ScoreboardScore s3 = new ScoreboardScore(sb, obj, "§7➟ §7" + Data.getRank(player));
        final ScoreboardScore s4 = new ScoreboardScore(sb, obj, "§r§1§5");
        final ScoreboardScore s5 = new ScoreboardScore(sb, obj, "§8➥ §f§lCoins");
        final ScoreboardScore s6 = new ScoreboardScore(sb, obj, "§7➟ §e" + coins);
        final ScoreboardScore s10 = new ScoreboardScore(sb, obj, "§r§1§5§b§L");
        final ScoreboardScore s11 = new ScoreboardScore(sb, obj, "§8➥ §f§lClan");
        final ScoreboardScore s12 = new ScoreboardScore(sb, obj, "§7➟ §cKein Clan");
        final ScoreboardScore s13 = new ScoreboardScore(sb, obj, "§r§1§6§9");
        final ScoreboardScore s14 = new ScoreboardScore(sb, obj, "§8➥ §f§lOnlinezeit");
        final ScoreboardScore s15 = new ScoreboardScore(sb, obj, "§7➟ §b" + onlinetime +"§3h");
        final ScoreboardScore s16 = new ScoreboardScore(sb, obj, "§r§1§6§9§a");

        s1.setScore(12);
        s2.setScore(11);
        s3.setScore(10);
        s4.setScore(9);
        s5.setScore(8);
        s6.setScore(7);
        s10.setScore(6);
        s11.setScore(5);
        s12.setScore(4);
        s13.setScore(3);
        s14.setScore(2);
        s15.setScore(1);
        s16.setScore(0);


        final PacketPlayOutScoreboardObjective remove = new PacketPlayOutScoreboardObjective(obj, 1);
        final PacketPlayOutScoreboardScore p2 = new PacketPlayOutScoreboardScore(s1);
        final PacketPlayOutScoreboardScore p3 = new PacketPlayOutScoreboardScore(s2);
        final PacketPlayOutScoreboardScore p4 = new PacketPlayOutScoreboardScore(s3);
        final PacketPlayOutScoreboardScore p5 = new PacketPlayOutScoreboardScore(s4);
        final PacketPlayOutScoreboardScore p6 = new PacketPlayOutScoreboardScore(s5);
        final PacketPlayOutScoreboardScore p7 = new PacketPlayOutScoreboardScore(s6);
        final PacketPlayOutScoreboardScore p11 = new PacketPlayOutScoreboardScore(s10);
        final PacketPlayOutScoreboardScore p12 = new PacketPlayOutScoreboardScore(s11);
        final PacketPlayOutScoreboardScore p13 = new PacketPlayOutScoreboardScore(s12);
        final PacketPlayOutScoreboardScore p14 = new PacketPlayOutScoreboardScore(s13);
        final PacketPlayOutScoreboardScore p15 = new PacketPlayOutScoreboardScore(s14);
        final PacketPlayOutScoreboardScore p16 = new PacketPlayOutScoreboardScore(s15);
        final PacketPlayOutScoreboardScore p17 = new PacketPlayOutScoreboardScore(s16);

        sendPacket(player, (Packet<?>) add);
        sendPacket(player, (Packet<?>) adDisplay);
        sendPacket(player, (Packet<?>) p2);
        sendPacket(player, (Packet<?>) p3);
        sendPacket(player, (Packet<?>) p4);
        sendPacket(player, (Packet<?>) p5);
        sendPacket(player, (Packet<?>) p7);
        sendPacket(player, (Packet<?>) p11);
        sendPacket(player, (Packet<?>) p12);
        sendPacket(player, (Packet<?>) p13);
        sendPacket(player, (Packet<?>) p14);
        sendPacket(player, (Packet<?>) p15);
        sendPacket(player, (Packet<?>) p16);
        sendPacket(player, (Packet<?>) p17);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    sendPacket(all, (Packet<?>) p6);
                }
            }
        },1,1);

    }

    public static void sendPacket(final Player p, final Packet<?> packet) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}
