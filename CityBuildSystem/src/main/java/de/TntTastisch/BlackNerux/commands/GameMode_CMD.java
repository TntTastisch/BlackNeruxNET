package de.TntTastisch.BlackNerux.commands;

import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameMode_CMD implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("citybuild.command.gamemode")) {

                if (strings.length == 1) {

                    if(strings[0] == null){
                        player.sendMessage(Data.prefix + "§7Benutze §8× §e/gamemode <1,2,3 | survival,creative,adventure,spectator> <Spieler>");
                    }

                    if (strings[0].equalsIgnoreCase("0") || strings[0].equalsIgnoreCase("survival")) {
                        if (player.hasPermission("citybuild.command.gamemode.0") || player.hasPermission("citybuild.command.gamemode.survival") || player.hasPermission("citybuild.command.gamemode")) {

                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Überleben §7geändert.");

                        } else {
                            player.sendMessage(Data.noPerms);
                        }
                    } else if (strings[0].equalsIgnoreCase("1") || strings[0].equalsIgnoreCase("creative")) {
                        if (player.hasPermission("citybuild.command.gamemode.1") || player.hasPermission("citybuild.command.gamemode.creative") || player.hasPermission("citybuild.command.gamemode")) {

                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Kreativ §7geändert.");

                        } else {
                            player.sendMessage(Data.noPerms);
                        }
                    } else if (strings[0].equalsIgnoreCase("2") || strings[0].equalsIgnoreCase("adventure")) {
                        if (player.hasPermission("citybuild.command.gamemode.2") || player.hasPermission("citybuild.command.gamemode.adventure") || player.hasPermission("citybuild.command.gamemode")) {

                            player.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Abenteuer §7geändert.");

                        } else {
                            player.sendMessage(Data.noPerms);
                        }
                    } else if (strings[0].equalsIgnoreCase("3") || strings[0].equalsIgnoreCase("spectator")) {
                        if (player.hasPermission("citybuild.command.gamemode.3") || player.hasPermission("citybuild.command.gamemode.spectator") || player.hasPermission("citybuild.command.gamemode")) {

                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Zuschauer §7geändert.");

                        } else {
                            player.sendMessage(Data.noPerms);
                        }
                    }


                } else if (strings.length == 2) {

                    if(strings[0] == null){
                        player.sendMessage(Data.prefix + "§7Benutze §8× §e/gamemode <1,2,3 | survival,creative,adventure,spectator> <Spieler>");
                    }

                    if (strings[0].equalsIgnoreCase("0") || strings[0].equalsIgnoreCase("survival")) {
                        if (player.hasPermission("citybuild.command.gamemode.0.other") || player.hasPermission("citybuild.command.gamemode.survival.other") || player.hasPermission("citybuild.command.gamemode.other")) {

                            Player target = Bukkit.getPlayer(strings[1]);

                            if (target != null) {
                                if (target != player) {
                                    target.setGameMode(GameMode.SURVIVAL);
                                    player.sendMessage(Data.prefix + "§7Du hast den §cSpielmodus §7von " + Data.getPlayerPrefix(target) + " §7auf §6Überleben §7geändert.");
                                    target.sendMessage(Data.prefix + "§7Dein §cSpielmodus §7wurde von " + Data.getPlayerPrefix(player) + " §7auf §6Überleben §7geändert.");
                                } else {
                                    player.setGameMode(GameMode.SURVIVAL);
                                    player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Überleben §7geändert.");
                                }
                            } else {
                                player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                            }
                        } else {
                            player.sendMessage(Data.noPerms);
                        }
                    } else if (strings[0].equalsIgnoreCase("1") || strings[0].equalsIgnoreCase("creative")) {
                        if (player.hasPermission("citybuild.command.gamemode.1.other") || player.hasPermission("citybuild.command.gamemode.creative.other") || player.hasPermission("citybuild.command.gamemode.other")) {

                            Player target = Bukkit.getPlayer(strings[1]);

                            if (target != null) {
                                if (target != player) {
                                    target.setGameMode(GameMode.CREATIVE);
                                    player.sendMessage(Data.prefix + "§7Du hast den §cSpielmodus §7von " + Data.getPlayerPrefix(target) + " §7auf §6Kreativ §7geändert.");
                                    target.sendMessage(Data.prefix + "§7Dein §cSpielmodus §7wurde von " + Data.getPlayerPrefix(player) + " §7auf §6Kreativ §7geändert.");
                                } else {
                                    player.setGameMode(GameMode.CREATIVE);
                                    player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Kreativ §7geändert.");
                                }
                            } else {
                                player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                            }

                        } else {
                            player.sendMessage(Data.noPerms);
                        }
                    } else if (strings[0].equalsIgnoreCase("2") || strings[0].equalsIgnoreCase("adventure")) {
                        if (player.hasPermission("citybuild.command.gamemode.2.other") || player.hasPermission("citybuild.command.gamemode.adventure.other") || player.hasPermission("citybuild.command.gamemode.other")) {

                            Player target = Bukkit.getPlayer(strings[1]);

                            if (target != null) {
                                if (target != player) {
                                    target.setGameMode(GameMode.ADVENTURE);
                                    player.sendMessage(Data.prefix + "§7Du hast den §cSpielmodus §7von " + Data.getPlayerPrefix(target) + " §7auf §6Abenteuer §7geändert.");
                                    target.sendMessage(Data.prefix + "§7Dein §cSpielmodus §7wurde von " + Data.getPlayerPrefix(player) + " §7auf §6Abenteuer §7geändert.");
                                } else {
                                    player.setGameMode(GameMode.ADVENTURE);
                                    player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Abenteuer §7geändert.");
                                }
                            } else {
                                player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                            }

                        } else {
                            player.sendMessage(Data.noPerms);
                        }
                    } else if (strings[0].equalsIgnoreCase("3") || strings[0].equalsIgnoreCase("spectator")) {
                        if (player.hasPermission("citybuild.command.gamemode.3.other") || player.hasPermission("citybuild.command.gamemode.spectator.other") || player.hasPermission("citybuild.command.gamemode.other")) {

                            Player target = Bukkit.getPlayer(strings[1]);

                            if (target != null) {
                                if (target != player) {
                                    target.setGameMode(GameMode.SPECTATOR);
                                    player.sendMessage(Data.prefix + "§7Du hast den §cSpielmodus §7von " + Data.getPlayerPrefix(target) + " §7auf §6Zuschauer §7geändert.");
                                    target.sendMessage(Data.prefix + "§7Dein §cSpielmodus §7wurde von " + Data.getPlayerPrefix(player) + " §7auf §6Zuschauer §7geändert.");
                                } else {
                                    player.setGameMode(GameMode.SPECTATOR);
                                    player.sendMessage(Data.prefix + "§7Du hast dein §cSpielmodus §7auf §6Zuschauer §7geändert.");
                                }
                            } else {
                                player.sendMessage(Data.prefix + "§cDieser Spieler ist Offline!");
                            }


                        }
                    } else {
                        player.sendMessage(Data.noPerms);
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
