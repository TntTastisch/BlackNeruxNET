package de.TeeJan.BlackNerux.listeners;

import de.TeeJan.BlackNerux.LaserTag;
import de.TeeJan.BlackNerux.Teams.TeamChat;
import de.TeeJan.BlackNerux.Teams.TeamInteract;
import de.TeeJan.BlackNerux.Teams.TeamSelect;
import de.TeeJan.BlackNerux.Teams.Teams;
import de.TeeJan.BlackNerux.cmd.SpawnConfig;
import de.TeeJan.BlackNerux.systems.Data;
import de.TeeJan.BlackNerux.utils.GameState;
import de.TeeJan.BlackNerux.utils.PlayerInventories;
import de.TeeJan.BlackNerux.utils.ScoreboardManager;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;
import sun.management.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTable;

import javax.xml.xpath.XPath;
import java.util.ArrayList;

import static de.TeeJan.BlackNerux.Teams.Teams.cfg;

public class JoinQuitListener implements Listener {


    protected static int cd;
    public static int COUNTDOWN;
    public static int MAX_PLAYERS;
    public static ArrayList<Player> ingames;

    static {
        COUNTDOWN = 61;
        MAX_PLAYERS = 1;
        ingames = new ArrayList<Player>();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(null);


        ScoreboardManager.setScoreboard(player);


        for (Player all : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.setScoreboard(all);
        }

        if (GameState.getGamestate() == GameState.LOBBY) {
            Bukkit.broadcastMessage(Data.prefix + "§6" + TeamChat.getPlayerPrefix(player) + " §7hat das Spiel §abetreten§7.");
            ingames.add(player);

            player.setGameMode(GameMode.SURVIVAL);
            player.setHealth(20);
            player.setFoodLevel(20);
            SpawnConfig.teleportPlayerToLocation(player, LaserTag.fileCFG, "spawn");
            player.setLevel(COUNTDOWN);
            player.getInventory().clear();
            PlayerInventories.setStartItems(player);

            if (Bukkit.getOnlinePlayers().size() <= MAX_PLAYERS) {

                cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(LaserTag.getPlugin(LaserTag.class), new Runnable() {
                    public void run() {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.setLevel(COUNTDOWN);
                        }
                        if (COUNTDOWN == 60 || COUNTDOWN == 45 || COUNTDOWN == 30 || COUNTDOWN == 15 || COUNTDOWN == 5 || COUNTDOWN == 4 || COUNTDOWN == 3 || COUNTDOWN == 2) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.NOTE_BASS, 10, 10);
                                de.TntTastisch.BlackNerux.api.TitleAPI.sendFullTitle(all, 2, 15, 2, "§e" + COUNTDOWN, "");
                            }

                        } else if (COUNTDOWN == 10) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.NOTE_BASS, 15, 10);
                                de.TntTastisch.BlackNerux.api.TitleAPI.sendFullTitle(all, 2, 10, 2, "§e" + COUNTDOWN, "§4Voted Map");
                            }
                        } else if (COUNTDOWN == 1) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.NOTE_BASS, 15, 10);
                                de.TntTastisch.BlackNerux.api.TitleAPI.sendFullTitle(all, 2, 10, 2, "§e" + COUNTDOWN, "");
                            }
                        } else if (COUNTDOWN == 0) {
                            Bukkit.getScheduler().cancelTask(cd);
                            COUNTDOWN = 10;
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.setLevel(0);
                            }
                        }
                        COUNTDOWN--;
                    }
                }, 1, 20L * 1);


            } else {
                Bukkit.getScheduler().cancelTask(cd);
                COUNTDOWN = 61;
                Bukkit.broadcastMessage(Data.prefix + "§cWarten auf Spieler...");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        event.setQuitMessage(null);
        ScoreboardManager.setScoreboard(player);

        for (Player all : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.setScoreboard(all);
        }

        if (GameState.getGamestate() == GameState.LOBBY) {
            ingames.remove(player);
            Teams.removequit(player);
            Bukkit.broadcastMessage(Data.prefix + "§6" + TeamChat.getPlayerPrefix(player) + " §7hat das Spiel §cverlassen§7.");

        }

        if (Bukkit.getOnlinePlayers().size() != MAX_PLAYERS) {
            Bukkit.getScheduler().cancelTask(cd);
            COUNTDOWN = 61;
            for(Player all : Bukkit.getOnlinePlayers()){
                all.setLevel(60);
            }

            Bukkit.broadcastMessage(Data.prefix + "§cWarten auf Spielet" +  "...");

        }


    }
}