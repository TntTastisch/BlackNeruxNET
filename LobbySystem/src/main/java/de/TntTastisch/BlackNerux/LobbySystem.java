package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.commands.Build_CMD;
import de.TntTastisch.BlackNerux.commands.Event_CMD;
import de.TntTastisch.BlackNerux.commands.Fly_CMD;
import de.TntTastisch.BlackNerux.commands.Set_CMD;
import de.TntTastisch.BlackNerux.listener.*;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.Events;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LobbySystem extends JavaPlugin implements Listener {

    public FileConfiguration configuration = this.getConfig();
    public static MySQL mySQL;


    @Override
    public void onEnable() {
        configuration();
        mysql();

        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new JoinQuitListener(), this);
        manager.registerEvents(new BuildListener(), this);
        manager.registerEvents(new DeathListener(), this);
        manager.registerEvents(new DamageListener(), this);
        manager.registerEvents(new WeatherListener(), this);
        manager.registerEvents(new EntityInteractListener(), this);
        manager.registerEvents(new WheatListener(), this);
        manager.registerEvents(new TeleportListener(), this);
        manager.registerEvents(new ItemFrameistener(), this);
        manager.registerEvents(new EnderdragonListener(), this);

        manager.registerEvents(new SilvesterEventListener(), this);
        manager.registerEvents(new EnderpearlListener(), this);
        manager.registerEvents(new EnterhakenListener(), this);

        manager.registerEvents(new FlyListener(), this);
        manager.registerEvents(new SilentlobbyListener(), this);
        manager.registerEvents(new PlayerhiderListener(), this);
        manager.registerEvents(new GadgetListener(), this);
        manager.registerEvents(new ShieldListener(), this);
        manager.registerEvents(new NavigatorListener(), this);
        manager.registerEvents(new DoubleJumpListener(), this);
        manager.registerEvents(new LobbySwitcherListener(), this);
        manager.registerEvents(new CloudControlPanelListener(), this);

        this.getCommand("set").setExecutor(new Set_CMD());
        this.getCommand("fly").setExecutor(new Fly_CMD());
        this.getCommand("build").setExecutor(new Build_CMD());
        this.getCommand("event").setExecutor(new Event_CMD());
        // this.getCommand("spawn").setExecutor(new Spawn_CMD());

        String EventType = configuration.getString("Events.type");

        if (EventType.equalsIgnoreCase("weihnachten")) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        World world = Bukkit.getWorld(all.getWorld().getName());

                        world.setTime(18000);
                        world.setGameRuleValue("doDaylightCycle", "false");
                    }
                }
            }, 1L, 20L*10);


            Events.startSnowParticles();
        }  else if (EventType.equalsIgnoreCase("silvester")) {

            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        World world = Bukkit.getWorld(all.getWorld().getName());
                        world.setTime(18000);
                        world.setGameRuleValue("doDaylightCycle", "false");
                    }

                    Date today = Calendar.getInstance().getTime();
                    DateFormat tm = new SimpleDateFormat("HH:mm:ss");
                    TimeZone timezone = TimeZone.getTimeZone("Europe/Berlin");
                    tm.setTimeZone(timezone);
                    String Time = tm.format(today);

                    if(Time.equalsIgnoreCase("23:50:00")){

                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n"
                                + "§7§lDas Event startet in §6§l10 Minuten§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");

                    } else if(Time.equalsIgnoreCase("23:55:00")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n"
                                + "§7§lDas Event startet in §6§l5 Minuten§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");

                    } else if(Time.equalsIgnoreCase("23:59:00")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n"
                                + "§7§lDas Event startet in §6§l60 Sekunden§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");

                    } else if(Time.equalsIgnoreCase("23:59:50")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n\n" + "§7§lDas Event startet in §6§l10 Sekunden§7§l...\n\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");

                    } else if(Time.equalsIgnoreCase("23:59:51")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n"
                                + "§7§lDas Event startet in §6§l9 Sekunden§7§l.." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:52")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n" + "§7§lDas Event startet in §6§l8 Sekunden§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:53")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n"
                                + "§7§lDas Event startet in §6§l7 Sekunden§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:54")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n" + "§7§lDas Event startet in §6§l6 Sekunden§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:55")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n" + "§7§lDas Event startet in §6§l5 Sekunden§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:56")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+" +
                                "\n" +
                                "\n" +
                                "\n"+ "§7§lDas Event startet in §6§l4 Sekunden§7§l...\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:57")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n" +
                                "§7§lDas Event startet in §6§l3 Sekunden§7§l..." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:58")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n" +
                                "§7§lDas Event startet in §6§l2 Sekunden§7§l..." +
                                "\n" +
                                "\n" +
                                "\n" +
                                "§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("23:59:59")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n" +
                                "" + "§7§lDas Event startet in §6§l1 Sekunden§7§l...." +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    } else if(Time.equalsIgnoreCase("00:00:00")){
                        Bukkit.broadcastMessage("§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+\n" +
                                "\n" +
                                "\n"
                                + "§a§lDann last die Raketen mal steigen!!!!!!" +
                                "\n" +
                                "\n" +
                                "\n§8§m+--------§r §4§lBLACKNERUX NETWORKS §8§m--------+");
                    }


                }
            }, 1L, 20L*1);
        }


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    World world = Bukkit.getWorld(all.getWorld().getName());

                    world.setThundering(false);
                    world.setStorm(false);

                    for (Entity entity : world.getEntities()) {

                        if (entity instanceof Pig || entity instanceof Cow || entity instanceof Enderman || entity instanceof Spider || entity instanceof Creeper || entity instanceof Zombie
                                || entity instanceof Sheep || entity instanceof Horse || entity instanceof Squid || entity instanceof Chicken || entity instanceof Witch || entity instanceof Snowman
                                || entity instanceof Rabbit) {
                            entity.remove();
                        }
                    }
                }
            }
        },1,1);


        Bukkit.getConsoleSender().sendMessage("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " wurde erfolgreich aktiviert!");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " Version §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " Autor TntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]");
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " wurde erfolgreich deaktiviert!");
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " Version §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]====[ §a" + getDescription().getName() + " Autor TntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]===============[ §4§l" + getDescription().getName() + " §f]===============[]");
    }

    public void mysql(){
        String host = configuration.getString("MySQL.Host");
        String port = configuration.getString("MySQL.Port");
        String database = configuration.getString("MySQL.Database");
        String user = configuration.getString("MySQL.User");
        String password = configuration.getString("MySQL.Password");

        try {
            (LobbySystem.mySQL = new MySQL(host,port,database,user,password)).update("CREATE TABLE IF NOT EXISTS lobby(UUID varchar(64), X varchar(64), Y varchar(64), Z varchar(64), " +
                    "Yaw varchar(64), Pitch varchar(64), World varchar(64), playerhider varchar(64), particle varchar(64), gadgets varchar(64), skull varchar(64), " +
                    "fly varchar(64), shield varchar(64));");
        } catch (Exception e){}

    }

    public void configuration(){

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        if(!LocationManager.locations.exists()){
            try {
                LocationManager.locations.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        configuration.addDefault("Events.type", "null");
        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "lobby");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");

        configuration.addDefault("Koepfe.1slot.player", "TntTastisch");
        configuration.addDefault("Koepfe.1slot.prefix", "§4§lOwner ▏ §4§lTntTastisch");

        configuration.addDefault("Koepfe.2slot.player", "Einfaxh");
        configuration.addDefault("Koepfe.2slot.prefix", "§4§lOwner ▏ §4§lEinfaxh");

        configuration.addDefault("Koepfe.3slot.player", "Verweigerung");
        configuration.addDefault("Koepfe.3slot.prefix", "§c§lAdmin ▏ §c§lVerweigerung");

        configuration.addDefault("Koepfe.4slot.player", "FPS_ZOCKER_");
        configuration.addDefault("Koepfe.4slot.prefix", "§c§lAdmin ▏ §c§lFPS_ZOCKER_");

        configuration.addDefault("Koepfe.5slot.player", "MiniNoopHD");
        configuration.addDefault("Koepfe.5slot.prefix", "§9§lBuilder ▏ §9§lMiniNoopHD");

        configuration.addDefault("Koepfe.6slot.player", "MHF_Question");
        configuration.addDefault("Koepfe.6slot.prefix", "§cTeam Mitglied...");

        configuration.addDefault("Koepfe.7slot.player", "MHF_Question");
        configuration.addDefault("Koepfe.7slot.prefix", "§cTeam Mitglied...");

        configuration.options().copyDefaults(true);
        configuration.options().header("#>> Bei den Events kannst du Oster, Halloween, Weihnachten und Silvester eingeben. Bei keinem Event einfach -null- eingeben!");
        configuration.options().copyHeader(true);
        saveConfig();
    }


}
