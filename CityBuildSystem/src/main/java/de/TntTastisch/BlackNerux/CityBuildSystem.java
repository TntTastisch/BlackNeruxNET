package de.TntTastisch.BlackNerux;

import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.commands.*;
import de.TntTastisch.BlackNerux.listeners.*;
import de.TntTastisch.BlackNerux.systems.MySQL;
import de.TntTastisch.BlackNerux.utils.LocationManager;
import de.TntTastisch.BlackNerux.utils.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CityBuildSystem extends JavaPlugin implements Listener {

    public static CityBuildSystem plugin;
    public static MySQL mySQL;
    public FileConfiguration configuration = this.getConfig();

    @Override
    public void onEnable() {
        plugin = this;
        createFiles();
        connectToSQL();
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new ColouredSignListener(), this);
        manager.registerEvents(new JoinQuitListener(), this);
        manager.registerEvents(new ColouredAnvilListener(), this);
        manager.registerEvents(new DeathListener(), this);
        manager.registerEvents(new SocialSpyListener(), this);
        manager.registerEvents(new JobsListener(), this);

        ScoreboardManager.updateScoreboard();

        // Team Features
        this.getCommand("gamemode").setExecutor(new GameMode_CMD());
        this.getCommand("vanish").setExecutor(new Vanish_CMD());
        this.getCommand("fly").setExecutor(new Fly_CMD());
        this.getCommand("sign").setExecutor(new Sign_CMD());
        this.getCommand("invsee").setExecutor(new Invsee_CMD());
        // Features
        this.getCommand("feed").setExecutor(new Feed_CMD());
        this.getCommand("jobs").setExecutor(new Job_CMD());
        this.getCommand("heal").setExecutor(new Heal_CMD());
        this.getCommand("enderchest").setExecutor(new Enderchest_CMD());
        // Location
        this.getCommand("setspawn").setExecutor(new SetSpawn_CMD());
        this.getCommand("spawn").setExecutor(new Spawn_CMD());
        // Teleport
        this.getCommand("teleport").setExecutor(new Teleport_CMD());
        this.getCommand("tpall").setExecutor(new Tpall_CMD());
        this.getCommand("tphere").setExecutor(new Tphere_CMD());
        // Home
        this.getCommand("sethome").setExecutor(new SetHome_CMD());
        this.getCommand("delhome").setExecutor(new DelHome_CMD());
        this.getCommand("home").setExecutor(new Home_CMD());
        // TPA
        this.getCommand("tpa").setExecutor(new Tpa_CMD());
        this.getCommand("tpatoggle").setExecutor(new TpaToggle_CMD());
        this.getCommand("tpahere").setExecutor(new Tpahere_CMD());
        this.getCommand("tpaall").setExecutor(new Tpaall_CMD());
        this.getCommand("annehmen").setExecutor(new Annehmen_CMD());
        this.getCommand("ablehnen").setExecutor(new Ablehnen_CMD());
        // MSG
        this.getCommand("socialspy").setExecutor(new SocialSpy_CMD());
        this.getCommand("msgtoggle").setExecutor(new MsgToggle_CMD());
        this.getCommand("msg").setExecutor(new Msg_CMD());
        this.getCommand("r").setExecutor(new R_CMD());
        // TIME UNIT
        this.getCommand("day").setExecutor(new Day_CMD());
        this.getCommand("night").setExecutor(new Night_CMD());
        this.getCommand("ptime").setExecutor(new PlayerTime_CMD());
        // MONEY SYSTEM
        this.getCommand("money").setExecutor(new Money_CMD());
        this.getCommand("pay").setExecutor(new Pay_CMD());

        // CRAFTING RECIPES
        nonnenhausSchuh();
        baumeisterHacke();
        tntskuechenmesser();
        SwordToGoldNugget();


        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin wurde erfolgreich aktiviert.");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDer Plugin Autor ist §bTntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §aDas Plugin ist in der Version §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    @Override
    public void onDisable() {
        CityBuildSystem.mySQL.disconnect();
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §cDas Plugin wurde erfolgreich deaktiviert.");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §cDer Plugin Autor ist §bTntTastisch");
        Bukkit.getConsoleSender().sendMessage("§f[]===[ §cDas Plugin ist in der Version §5" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    public static CityBuildSystem getInstance() {
        return plugin;
    }

    public void createFiles(){

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File data = new File("plugins/CityBuildSystem/data");

        if(!data.exists()){
            data.mkdir();
        }

        if(!LocationManager.locations.exists()){
            try {
                LocationManager.locations.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(!Home_CMD.homes.exists()){
            try {
                Home_CMD.homes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        configuration.addDefault("MySQL.Host", "localhost");
        configuration.addDefault("MySQL.Port", "3306");
        configuration.addDefault("MySQL.Database", "citybuild");
        configuration.addDefault("MySQL.User", "root");
        configuration.addDefault("MySQL.Password", " ");
        configuration.options().copyDefaults(true);
        saveConfig();
    }

    public void connectToSQL(){
        String host = configuration.getString("MySQL.Host");
        String port = configuration.getString("MySQL.Port");
        String database = configuration.getString("MySQL.Database");
        String user = configuration.getString("MySQL.User");
        String password = configuration.getString("MySQL.Password");

        (CityBuildSystem.mySQL = new MySQL(host,port,database,user,password))
                .update("CREATE TABLE IF NOT EXISTS players(UUID varchar(120), Name varchar(64), MsgEnable varchar(10), " +
                        "SocialSpyEnable varchar(10), TpaEnable varchar(10), VanishEnable varchar(10), FlyEnabled varchar(10), Job varchar(64), money varchar(120));");


    }


    public void nonnenhausSchuh(){
        List<String> nonnenhausschuhlist = new ArrayList<>();
        nonnenhausschuhlist.add(" ");
        nonnenhausschuhlist.add("§b§lSpüre den Hausschuh §4§lGOTTES§b§l!!!");
        nonnenhausschuhlist.add(" ");

        ItemStack hausSchuh = new ItemStack(Material.LEATHER);
        ItemMeta hausSchuhm = hausSchuh.getItemMeta();
        hausSchuhm.setDisplayName("§b§lNonnen Hausschuh");
        hausSchuhm.addEnchant(Enchantment.DAMAGE_ALL, 15 ,true);
        hausSchuhm.addEnchant(Enchantment.KNOCKBACK, 10 ,true);
        hausSchuhm.addEnchant(Enchantment.LOOT_BONUS_MOBS, -1 ,true);
        hausSchuhm.setLore(nonnenhausschuhlist);
        hausSchuhm.spigot().setUnbreakable(true);
        hausSchuh.setItemMeta(hausSchuhm);

        ShapedRecipe shapelessRecipe = new ShapedRecipe(hausSchuh);
        shapelessRecipe.shape(new String[] {
                "ABC", "DEF", "QRS"
        });

        shapelessRecipe.setIngredient('B' ,Material.LEATHER);
        shapelessRecipe.setIngredient('D' ,Material.LEATHER);
        shapelessRecipe.setIngredient('E' ,Material.BEACON);
        shapelessRecipe.setIngredient('F' ,Material.LEATHER);
        shapelessRecipe.setIngredient('R' ,Material.LEATHER);

        this.getServer().addRecipe(shapelessRecipe);

    }

    public void SwordToGoldNugget(){

        ItemStack goldnugget = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta goldnuggetm = goldnugget.getItemMeta();
        goldnugget.setAmount(32);
        goldnugget.setItemMeta(goldnuggetm);


        ShapedRecipe shapelessRecipe = new ShapedRecipe(goldnugget);
        shapelessRecipe.shape("AAA","AAA","AAA");

        shapelessRecipe.setIngredient('A', Material.GOLD_SWORD);


        Bukkit.addRecipe(shapelessRecipe);
    }

    public void baumeisterHacke(){
        List<String> baumeisterhackelist = new ArrayList<>();

        baumeisterhackelist.add(" ");
        baumeisterhackelist.add("§a§lDie hast du nur, wenn du ein wahrer §2§lBauarbeiter §a§lbist!");
        baumeisterhackelist.add(" ");

        ItemStack baumeisterHacke = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta baumeisterHackem = baumeisterHacke.getItemMeta();
        baumeisterHackem.setDisplayName("§2§lEine wahre Baumeisterspitzhacke");
        baumeisterHackem.addEnchant(Enchantment.DIG_SPEED, 10 ,true);
        baumeisterHackem.addEnchant(Enchantment.DURABILITY, 5 ,true);
        baumeisterHackem.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5 ,true);
        baumeisterHackem.setLore(baumeisterhackelist);
        baumeisterHackem.spigot().setUnbreakable(true);
        baumeisterHacke.setItemMeta(baumeisterHackem);

        ShapedRecipe shapelessRecipe = new ShapedRecipe(baumeisterHacke);
        shapelessRecipe.shape(new String[] {
                "ABC", "DEF", "QRS"
        });

        shapelessRecipe.setIngredient('A' ,Material.BEACON);
        shapelessRecipe.setIngredient('B' ,Material.DRAGON_EGG);
        shapelessRecipe.setIngredient('C' ,Material.BEACON);
        shapelessRecipe.setIngredient('E' ,Material.DIAMOND_BLOCK);
        shapelessRecipe.setIngredient('R' ,Material.DIAMOND_BLOCK);

        this.getServer().addRecipe(shapelessRecipe);

    }

    public void tntskuechenmesser(){
        List<String> tntskuechenmesserlist = new ArrayList<>();

        tntskuechenmesserlist.add(" ");
        tntskuechenmesserlist.add("§b§lNun kannst du den §4§lSchweinen §b§lauf den §e§lKopf §b§lhauen!");
        tntskuechenmesserlist.add(" ");

        ItemStack tntskuechenmesser = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta tntskuechenmesserm = tntskuechenmesser.getItemMeta();

        tntskuechenmesserm.setDisplayName("§4§lTntTastisch's §c§lKüchenmesser");
        tntskuechenmesserm.addEnchant(Enchantment.DAMAGE_ALL, 10 ,true);
        tntskuechenmesserm.addEnchant(Enchantment.DURABILITY, 5 ,true);
        tntskuechenmesserm.addEnchant(Enchantment.FIRE_ASPECT, 3 ,true);
        tntskuechenmesserm.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3 ,true);

        tntskuechenmesserm.setLore(tntskuechenmesserlist);
        tntskuechenmesserm.spigot().setUnbreakable(true);
        tntskuechenmesser.setItemMeta(tntskuechenmesserm);

        ShapedRecipe shapelessRecipe = new ShapedRecipe(tntskuechenmesser);
        shapelessRecipe.shape(new String[] {
                "ABC", "DEF", "QRS"
        });

        shapelessRecipe.setIngredient('B' ,Material.BEACON);
        shapelessRecipe.setIngredient('D' ,Material.DIAMOND_BLOCK);
        shapelessRecipe.setIngredient('E' ,Material.BEACON);
        shapelessRecipe.setIngredient('F' ,Material.DIAMOND_BLOCK);
        shapelessRecipe.setIngredient('R' ,Material.DRAGON_EGG);

        this.getServer().addRecipe(shapelessRecipe);

    }
}
