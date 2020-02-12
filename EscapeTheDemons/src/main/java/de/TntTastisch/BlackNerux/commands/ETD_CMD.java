package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.EscapeTheDemons;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ETD_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(player.hasPermission("system.gamemode.etd.admin")){

                if(strings.length == 0){

                    player.sendMessage("§8§m----------§r §4§lBLACKNERUX NETWORKS §8§m----------");
                    player.sendMessage("§e/etd createGame <Name>");
                    player.sendMessage("§e/etd deleteGame <Name>");
                    player.sendMessage("§e/etd setLobby <Name>");
                    player.sendMessage("§e/etd setVisitorSpawn <Name>");
                    player.sendMessage("§e/etd setDemonSpawn <Name>");
                    player.sendMessage("§e/etd setPoliceSpawn <Name>");
                    player.sendMessage("§8§m----------§r §4§lBLACKNERUX NETWORKS §8§m----------");

                } else if(strings.length == 2){

                    if(strings[0].equalsIgnoreCase("createGame")){
                        String gameName = strings[1];

                        if(gameName.equalsIgnoreCase(null) || gameName.equalsIgnoreCase(" ") || gameName.equalsIgnoreCase("")){
                            player.sendMessage(Data.prefix + "§cDu musst ein Spielname angeben!");
                        } else {
                            File directory = new File("plugins/EscapeTheDemons/games/" + gameName);

                            if (directory.exists()) {
                                player.sendMessage(Data.prefix + "§cDiese Map existiert bereits!");
                            } else if (!directory.exists()) {
                                directory.mkdir();
                                player.sendMessage(Data.prefix + "§7Du hast die Map §e" + gameName + " §aerfolgreich §7erstellt.");

                            }
                        }
                    }

                    if(strings[0].equalsIgnoreCase("deleteGame")){
                        String gameName = strings[1];

                        if(gameName.equalsIgnoreCase(null) || gameName.equalsIgnoreCase(" ") || gameName.equalsIgnoreCase("")){
                            player.sendMessage(Data.prefix + "§cDu musst ein Spielname angeben!");
                        } else {
                            File directory = new File("plugins/EscapeTheDemons/games/" + gameName);

                            if (directory.exists()) {
                                directory.delete();
                                player.sendMessage(Data.prefix + "§7Du hast die Map §e" + gameName + " §aerfolgreich §7gelöscht.");

                            } else if (!directory.exists()) {
                                player.sendMessage(Data.prefix + "§cDiese Map existiert nicht!");
                            }
                        }
                    }

                    if(strings[0].equalsIgnoreCase("setLobby")){
                        String gameName = strings[1];

                        if(gameName.equalsIgnoreCase(null) || gameName.equalsIgnoreCase(" ") || gameName.equalsIgnoreCase("")){
                            player.sendMessage(Data.prefix + "§cDu musst ein Spielname angeben!");
                        } else {
                            File directory = new File("plugins/EscapeTheDemons/games/" + gameName);

                            if(!directory.exists()){
                                player.sendMessage(Data.prefix + "§cDiese Map existiert nicht!");
                            } else {
                                File file = new File("plugins/EscapeTheDemons/games/" + gameName);
                                YamlConfiguration fileCFG = YamlConfiguration.loadConfiguration(file);

                                if(!file.exists()){
                                    try {
                                        file.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Location location = player.getLocation();

                                fileCFG.set("Game.Lobby.X", location.getX());
                                fileCFG.set("Game.Lobby.Y", location.getY());
                                fileCFG.set("Game.Lobby.Z", location.getZ());
                                fileCFG.set("Game.Lobby.Yaw", location.getYaw());
                                fileCFG.set("Game.Lobby.Pitch", location.getPitch());
                                fileCFG.set("Game.Lobby.World", location.getWorld().getName());

                                try {
                                    fileCFG.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                player.sendMessage(Data.prefix + "§7Du hast die Startlobby für §e" + gameName + " §aerfolgreich §7gesetzt.");
                            }
                        }
                    }

                    if(strings[0].equalsIgnoreCase("setDemonSpawn")){
                        String gameName = strings[1];

                        if(gameName.equalsIgnoreCase(null) || gameName.equalsIgnoreCase(" ") || gameName.equalsIgnoreCase("")){
                            player.sendMessage(Data.prefix + "§cDu musst ein Spielname angeben!");
                        } else {
                            File directory = new File("plugins/EscapeTheDemons/games/" + gameName);

                            if(!directory.exists()){
                                player.sendMessage(Data.prefix + "§cDiese Map existiert nicht!");
                            } else {
                                File file = new File("plugins/EscapeTheDemons/games/" + gameName + "/gameLocations.yml");
                                YamlConfiguration fileCFG = YamlConfiguration.loadConfiguration(file);

                                if(!file.exists()){
                                    try {
                                        file.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Location location = player.getLocation();

                                fileCFG.set("Spawns.Demon.X", location.getX());
                                fileCFG.set("Spawns.Demon.Y", location.getY());
                                fileCFG.set("Spawns.Demon.Z", location.getZ());
                                fileCFG.set("Spawns.Demon.Yaw", location.getYaw());
                                fileCFG.set("Spawns.Demon.Pitch", location.getPitch());
                                fileCFG.set("Spawns.Demon.World", location.getWorld().getName());

                                try {
                                    fileCFG.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                player.sendMessage(Data.prefix + "§7Du hast den Demon-Spawn für §e" + gameName + " §aerfolgreich §7gesetzt.");
                            }
                        }
                    }

                    if(strings[0].equalsIgnoreCase("setPoliceSpawn")){
                        String gameName = strings[1];

                        if(gameName.equalsIgnoreCase(null) || gameName.equalsIgnoreCase(" ") || gameName.equalsIgnoreCase("")){
                            player.sendMessage(Data.prefix + "§cDu musst ein Spielname angeben!");
                        } else {
                            File directory = new File("plugins/EscapeTheDemons/games/" + gameName);

                            if(!directory.exists()){
                                player.sendMessage(Data.prefix + "§cDiese Map existiert nicht!");
                            } else {
                                File file = new File("plugins/EscapeTheDemons/games/" + gameName + "/gameLocations.yml");
                                YamlConfiguration fileCFG = YamlConfiguration.loadConfiguration(file);

                                if(!file.exists()){
                                    try {
                                        file.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Location location = player.getLocation();

                                fileCFG.set("Spawns.Police.X", location.getX());
                                fileCFG.set("Spawns.Police.Y", location.getY());
                                fileCFG.set("Spawns.Police.Z", location.getZ());
                                fileCFG.set("Spawns.Police.Yaw", location.getYaw());
                                fileCFG.set("Spawns.Police.Pitch", location.getPitch());
                                fileCFG.set("Spawns.Police.World", location.getWorld().getName());

                                try {
                                    fileCFG.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                player.sendMessage(Data.prefix + "§7Du hast den Polizei-Spawn für §e" + gameName + " §aerfolgreich §7gesetzt.");
                            }
                        }
                    }

                    if(strings[0].equalsIgnoreCase("setVisitorSpawn")){
                        String gameName = strings[1];

                        if(gameName.equalsIgnoreCase(null) || gameName.equalsIgnoreCase(" ") || gameName.equalsIgnoreCase("")){
                            player.sendMessage(Data.prefix + "§cDu musst ein Spielname angeben!");
                        } else {
                            File directory = new File("plugins/EscapeTheDemons/games/" + gameName);

                            if(!directory.exists()){
                                player.sendMessage(Data.prefix + "§cDiese Map existiert nicht!");
                            } else {
                                File file = new File("plugins/EscapeTheDemons/games/" + gameName + "/gameLocations.yml");
                                YamlConfiguration fileCFG = YamlConfiguration.loadConfiguration(file);

                                if(!file.exists()){
                                    try {
                                        file.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                Location location = player.getLocation();

                                fileCFG.set("Spawns.Visitor.X", location.getX());
                                fileCFG.set("Spawns.Visitor.Y", location.getY());
                                fileCFG.set("Spawns.Visitor.Z", location.getZ());
                                fileCFG.set("Spawns.Visitor.Yaw", location.getYaw());
                                fileCFG.set("Spawns.Visitor.Pitch", location.getPitch());
                                fileCFG.set("Spawns.Visitor.World", location.getWorld().getName());

                                try {
                                    fileCFG.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                player.sendMessage(Data.prefix + "§7Du hast den Besucher-Spawn für §e" + gameName + " §aerfolgreich §7gesetzt.");
                            }
                        }
                    }
                }
            } else {
                player.sendMessage(Data.noPerms);
            }
        } else {
            commandSender.sendMessage(Data.noPlayer);
        }

        return false;
    }
}
