package de.TeeJan.BlackNerux.utils;

import de.TeeJan.BlackNerux.systems.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class Scoreboard {

    public static void setScoreboard(Player player) {
        org.bukkit.scoreboard.ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§4§LBLACKNERUX.NET");

        objective.getScore("§f§k§d§a§d§d§d§8").setScore(14);
        objective.getScore("§8➥ §f§lSpieler").setScore(13);
        objective.getScore("§7➟ §d§e" + Bukkit.getOnlinePlayers().size()).setScore(12);
        objective.getScore("§f§k§d§a§d§d").setScore(11);
        objective.getScore("§8➥ §f§lKarte").setScore(10);
        objective.getScore("§7➟ §e§e" + player.getWorld().getName()).setScore(9);
        objective.getScore("§a§d§l§5§6§7§8§8§7§9").setScore(8);
        objective.getScore("§8➥ §f§lTode").setScore(7);
        objective.getScore("§7➟ §9§e" + MySQL.getDeath(player.getUniqueId().toString())).setScore(6);
        objective.getScore("§a§d§l§5§6§7§8§8§7§9§f§9§l").setScore(5);
        objective.getScore("§8➥ §f§lKills").setScore(4);
        objective.getScore("§7➟ §a§e" + MySQL.getKills(player.getUniqueId().toString())).setScore(3);
        objective.getScore("§a§d§l§5§6§7§8§8§7§9§a§4§l").setScore(2);
        objective.getScore("§8➥ §f§lCoins").setScore(1);
        objective.getScore("§7➟ §f§e" + MySQL.getCoins(player.getUniqueId().toString())).setScore(0);

        player.setScoreboard(scoreboard);
    }
}

