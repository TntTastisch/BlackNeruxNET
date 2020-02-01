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
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Score;

public class JoinQuitListener implements Listener {

    public static int playerSchedule;
    public static int LobbyTimer;
    public static int playerCount = 2;
    public static int timer = 60;

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

            player.getInventory().setItem(0, new ItemAPI(Material.BED).setDisplayname("§8➦ §7Wähle ein Team").create());

            player.getInventory().setItem(3, new ItemAPI(Material.DIAMOND).setDisplayname("§8➦ §aSpiel starten").create());
            player.getInventory().setItem(4, new ItemAPI(Material.EMPTY_MAP).setDisplayname("§8➦ §eMap Voting").create());
            player.getInventory().setItem(5, new ItemAPI(Material.TORCH).setDisplayname("§8➦ §aSpiel starten").create());

            player.getInventory().setItem(8, ItemAPI.SkullBuilder("§8➦ §cVerlassen", "MHF_ArrowLeft"));




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
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        Player player = event.getPlayer();

        if(GameState.getGameState() == GameState.LOBBY){
            Data.ingame.remove(player);
            Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §6" + Data.getPlayerPrefix(player) + " §7hat das Spiel §cverlassen§7.");

            for(Player all : Bukkit.getOnlinePlayers()){
                Scoreboard.setLobbyScoreboard(all);
            }

        } else if(GameState.getGameState() == GameState.INGAME){

            if(Data.ingame.contains(player)){
                Bukkit.broadcastMessage(Data.prefix + "§7Der Spieler §6" + Data.getPlayerPrefix(player) + " §7hat das Spiel §cverlassen§7.");
            }

        } else if(GameState.getGameState() == GameState.END){

        }

    }
}
