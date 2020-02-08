package de.TntTastisch.BlackNerux.utils;

import de.TntTastisch.BlackNerux.listener.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scoreboard {


    public static void setLobbyScoreboard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("lobbyphase", "dummy");
        objective.setDisplayName("§4§lBLACKNERUX.NET");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§a§l§b§4§5").setScore(15);
        objective.getScore("§8➥ §f§lKarte").setScore(14);
        objective.getScore("§7➟ §e§l" + JoinQuitListener.playedGame).setScore(13);
        objective.getScore("§a§4§5§7§l§c").setScore(12);
        objective.getScore("§8➥ §f§lSpieler").setScore(11);
        objective.getScore("§7➟ §a" + Bukkit.getOnlinePlayers().size()).setScore(10);
        objective.getScore("").setScore(9);
        objective.getScore("").setScore(8);
        objective.getScore("").setScore(7);
        objective.getScore("").setScore(6);
        objective.getScore("").setScore(5);
        objective.getScore("").setScore(4);
        objective.getScore("").setScore(3);
        objective.getScore("").setScore(2);
        objective.getScore("").setScore(1);
        objective.getScore("").setScore(0);

        player.setScoreboard(scoreboard);
    }


}
