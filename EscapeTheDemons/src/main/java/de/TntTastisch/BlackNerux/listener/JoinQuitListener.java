package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.EscapeTheDemons;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.api.TitleAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.GameState;
import de.TntTastisch.BlackNerux.utils.Scoreboard;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.service.ServiceId;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Score;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinQuitListener implements Listener {

    public static int playerSchedule;
    public static int LobbyTimer;
    public static int playerCount = 2;
    public static int timer = 60;
    public static String playedGame;
    public static int endTimer = 10;

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        final Player player = event.getPlayer();

        if(GameState.getGameState() == GameState.LOBBY){
            player.setGameMode(GameMode.SURVIVAL);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setLevel(timer);

            Data.ingame.add(player);
            for(Player all : Bukkit.getOnlinePlayers()){
                Scoreboard.setLobbyScoreboard(all);
            }
            Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §6" + Data.getPlayerPrefix(player) + " §7hat das Spiel §abetreten§7.");

            player.getInventory().clear();

            player.getInventory().setItem(4, new ItemAPI(Material.CHEST).setDisplayname("§8➦ §4Einstellungen").create());
            player.getInventory().setItem(8, ItemAPI.SkullBuilder("§8➦ §cVerlassen", "MHF_ArrowRight"));

            File file = new File("plugins/EscapeTheDemons/games/" + playedGame + "/gameLocations.yml");
            YamlConfiguration fileCFG = YamlConfiguration.loadConfiguration(file);

            Location location = player.getLocation();
            location.setX(fileCFG.getDouble("Game.Lobby.X"));
            location.setY(fileCFG.getDouble("Game.Lobby.Y"));
            location.setZ(fileCFG.getDouble("Game.Lobby.Z"));
            location.setYaw((float)fileCFG.getDouble("Game.Lobby.Yaw"));
            location.setPitch((float) fileCFG.getDouble("Game.Lobby.Pitch"));
            location.setWorld(Bukkit.getWorld(fileCFG.getString("Game.Lobby.World")));

            player.teleport(location);


            if(Bukkit.getOnlinePlayers().size() >= 2){

                LobbyTimer = Bukkit.getScheduler().scheduleSyncRepeatingTask(EscapeTheDemons.getPlugin(), new Runnable() {
                    public void run() {

                        if(timer == 60 || timer == 45 || timer == 30 || timer == 15){

                            TitleAPI.sendFullTitle(player, 5, 15, 5, "§e" + timer, "");
                            for(Player all : Bukkit.getOnlinePlayers()){
                                all.playSound(player.getLocation(), Sound.LEVEL_UP, 120, 120);
                            }

                        } else if(timer == 10){

                            TitleAPI.sendFullTitle(player, 5, 15, 5, "§e" + timer, "§c" + playedGame);
                            for(Player all : Bukkit.getOnlinePlayers()){
                                all.playSound(player.getLocation(), Sound.NOTE_PLING, 120, 120);
                            }

                        } else if(timer == 5 || timer == 4 || timer == 3 || timer == 2){
                            TitleAPI.sendFullTitle(player, 5, 15, 5, "§e" + timer, "");
                            for(Player all : Bukkit.getOnlinePlayers()){
                                all.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 120, 120);
                            }
                        } else if(timer == 1){
                            TitleAPI.sendFullTitle(player, 5, 15, 5, "§e" + timer, "");
                            for(Player all : Bukkit.getOnlinePlayers()){
                                all.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 120, 120);
                            }
                        } else if(timer == 0){
                            GameState.setGameState(GameState.INGAME);


                        }

                        for(Player all : Bukkit.getOnlinePlayers()){
                            all.setLevel(timer);
                        }
                        timer--;
                    }
                },1, 20L);

            }



        } else if(GameState.getGameState() == GameState.INGAME){
            Bukkit.getScheduler().cancelTask(playerSchedule);
            Bukkit.getScheduler().cancelTask(LobbyTimer);
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().clear();
            player.setLevel(0);
            player.setHealth(20);
            player.setFoodLevel(20);


        } else if(GameState.getGameState() == GameState.END){

        }

    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event){
        event.setQuitMessage(null);
        final Player player = event.getPlayer();

        if(GameState.getGameState() == GameState.LOBBY){
            Data.ingame.remove(player);
            Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §6" + Data.getPlayerPrefix(player) + " §7hat das Spiel §cverlassen§7.");

            Bukkit.getScheduler().runTaskLater(EscapeTheDemons.getPlugin(), new Runnable() {
                public void run() {
                    for(Player all : Bukkit.getOnlinePlayers()){
                        Scoreboard.setLobbyScoreboard(all);
                    }
                }
            }, 20L);

            if(Bukkit.getOnlinePlayers().size() <= playerCount){
                Bukkit.getScheduler().cancelTask(LobbyTimer);
                Bukkit.broadcastMessage(Data.prefix + "§cDer Countdown wurde abgebrochen, weil zu wenig Spieler in der Lobby sind!");
            }


        } else if(GameState.getGameState() == GameState.INGAME){

            if(Data.ingame.contains(player)){
                Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §6" + Data.getPlayerPrefix(player) + " §7hat das Spiel §cverlassen§7.");
            }

        } else if(GameState.getGameState() == GameState.END){
            player.getInventory().clear();
            player.getInventory().setItem(8, ItemAPI.SkullBuilder("§8➦ §cVerlassen", "MHF_ArrowRight"));

            Bukkit.getScheduler().scheduleSyncRepeatingTask(EscapeTheDemons.getPlugin(), new Runnable() {
                public void run() {

                    if(endTimer == 10 || endTimer == 5 || endTimer == 4 || endTimer == 3 || endTimer == 2){

                        Bukkit.broadcastMessage(Data.prefix + "§cDer Server wird in " + endTimer + " Sekunden neugestartet.");

                    } else if(endTimer == 1){

                        Bukkit.broadcastMessage(Data.prefix + "§cDer Server wird in " + endTimer + " Sekunden neugestartet.");

                    } else if(endTimer == 0){
                        for(Player all : Bukkit.getOnlinePlayers()){
                            all.kickPlayer("§cDer Server startet nun neu...");
                        }

                        Bukkit.shutdown();

                    }


                    for(Player all : Bukkit.getOnlinePlayers()){
                        all.setLevel(endTimer);
                    }
                    endTimer--;
                }
            }, 1, 20L);
        }

    }

    public static void randomMap(){
        File files = new File("plugins/EscapeTheDemons/games");

        ArrayList<String> list = new ArrayList<String>();

        for(File file : files.listFiles()){
            list.add(String.valueOf(file));
        }

        int i = (int) ((Math.random()) * list.size());
        playedGame = list.get(i);
    }

}
