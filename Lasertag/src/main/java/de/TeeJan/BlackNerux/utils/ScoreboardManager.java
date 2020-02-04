package de.TeeJan.BlackNerux.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class ScoreboardManager {

    public static void setScoreboard(Player player){
        org.bukkit.scoreboard.ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§4§LBLACKNERUX.NET");

        if(GameState.getGamestate() == GameState.LOBBY) {


            objective.getScore("§f§k§d§a§d§d").setScore(10);
            objective.getScore("§8➥ §lSpieler").setScore(9);
            objective.getScore("§7➟ §a" + Bukkit.getOnlinePlayers().size()).setScore(8);
            objective.getScore("§f§k§d§a§d§d§9§l").setScore(7);
            objective.getScore("§8➥ §lMap").setScore(6);
            objective.getScore("§7➟ §e§e" + player.getWorld().getName()).setScore(5);
            objective.getScore("§f§k§d§a§d§d§9§l").setScore(4);
            objective.getScore("§8➥ §lCoins").setScore(3);
            objective.getScore("§7➟ §e0").setScore(2);
            objective.getScore("§a§d§l§5§6§7§8§8§7§9").setScore(1);

            player.setScoreboard(scoreboard);
        }
    }

}
