package de.TeeJan.BlackNerux.Teams;

import de.TeeJan.BlackNerux.LaserTag;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Iterator;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.OfflinePlayer;


import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class Teams
{
    public static ArrayList<Player> rot;
    public static ArrayList<Player> blau;
    public static ArrayList<Player> grün;
    public static ArrayList<Player> gelb;
    public static ArrayList<Player> dead;
    public static ArrayList<Player> alive;
    public static boolean respawnblau;
    public static boolean respawnrot;
    public static boolean respawngelb;
    public static boolean respawnorange;
    public static boolean respawnpink;
    public static boolean respawnschwarz;
    public static Scoreboard board;
    public static Team ghost;
    public static File f;
    public static FileConfiguration cfg;

    static {
        Teams.rot = new ArrayList<Player>();
        Teams.blau = new ArrayList<Player>();
        Teams.grün = new ArrayList<Player>();
        Teams.gelb = new ArrayList<Player>();

        Teams.dead = new ArrayList<Player>();
        Teams.alive = new ArrayList<Player>();
        Teams.respawnblau = true;
        Teams.respawnrot = true;
        Teams.respawngelb = true;
        Teams.respawnorange = true;
        Teams.respawnpink = true;
        Teams.respawnschwarz = true;
        Teams.f = new File(LaserTag.getPlugin(LaserTag.class).getDataFolder().getPath(), "gamemode.yml");
        Teams.cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(Teams.f);
    }

    public static void removeall(final Player p) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) LaserTag.getPlugin(LaserTag.class), (Runnable)new Runnable() {
            public void run() {
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    all.hidePlayer(p);
                }
                Teams.blau.remove(p);
                Teams.rot.remove(p);
                Teams.gelb.remove(p);
                Teams.grün.remove(p);
                Teams.dead.add(p);
                Teams.alive.remove(p);
                final String world = Teams.cfg.getString("BedWars.spectator.world");
                final double x = Teams.cfg.getDouble("BedWars.spectator.x");
                final double y = Teams.cfg.getDouble("BedWars.spectator.y");
                final double z = Teams.cfg.getDouble("BedWars.spectator.z");
                final double yaw = Teams.cfg.getDouble("BedWars.spectator.yaw");
                final double pitch = Teams.cfg.getDouble("BedWars.spectator.pitch");
                final Location loc = new Location(Bukkit.getWorld(world), x, y, z);
                loc.setPitch((float)pitch);
                loc.setYaw((float)yaw);
                p.teleport(loc.add(0.0, 5.0, 0.0));
                p.setGameMode(GameMode.ADVENTURE);
                p.getInventory().clear();
                final ItemStack Itema = new ItemStack(Material.COMPASS);
                final ItemMeta metaa = Itema.getItemMeta();
                metaa.setDisplayName("§8§ §9Spectator");
                Itema.setItemMeta(metaa);
                p.getInventory().setItem(0, Itema);
                final ItemStack Itema2 = new ItemStack(Material.NETHER_STAR);
                final ItemMeta metaa2 = Itema2.getItemMeta();
                metaa2.setDisplayName("§8§ §aZur Lobby");
                Itema2.setItemMeta(metaa2);
                p.getInventory().setItem(8, Itema2);
                p.updateInventory();
                p.setAllowFlight(true);
                p.setFlying(true);
                Teams.board = Bukkit.getScoreboardManager().getNewScoreboard();
                Teams.ghost = Teams.board.getTeam("team");
                if (Teams.ghost == null) {
                    Teams.ghost = Teams.board.registerNewTeam("team");
                }
                for (final Player players : Teams.dead) {
                    Teams.ghost.addPlayer((OfflinePlayer)players);
                    players.setScoreboard(Teams.board);
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            }
        }, 10L);
    }

    public static void removequit(final Player p) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)LaserTag.getPlugin(LaserTag.class), (Runnable)new Runnable() {
            public void run() {
                Teams.blau.remove(p);
                Teams.rot.remove(p);
                Teams.gelb.remove(p);
                Teams.grün.remove(p);
                Teams.alive.remove(p);
                Teams.dead.remove(p);
            }
        }, 10L);
    }

    public static int bettenSize() {
        final int size = 1;
        int i = 0;
        if (TeamInteract.getMax("Blau") != 0) {
            i += size;
        }
        if (TeamInteract.getMax("Rot") != 0) {
            i += size;
        }
        if (TeamInteract.getMax("Gelb") != 0) {
            i += size;
        }
        if (TeamInteract.getMax("Grün") != 0) {
            i += size;
        }

        return i;
    }
}
