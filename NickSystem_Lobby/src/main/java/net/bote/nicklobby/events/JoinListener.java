package net.bote.nicklobby.events;

import java.io.*;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.*;
import net.md_5.bungee.api.*;
import org.bukkit.*;
import net.bote.nicklobby.main.*;
import org.bukkit.entity.*;
import net.bote.nicklobby.mysql.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class JoinListener implements Listener
{
    File file;
    YamlConfiguration cfg;
    
    public JoinListener() {
        this.file = new File("plugins//NickSystem", "config.yml");
        new YamlConfiguration();
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.getInventory().contains(this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Name.On"))))) {
            p.getInventory().remove(this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Name.On"))));
        }
        else if (p.getInventory().contains(this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Name.Off"))))) {
            p.getInventory().remove(this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Name.Off"))));
        }
        if (p.hasPermission("nicksystem.autonick")) {
            Bukkit.getScheduler().runTaskLater((Plugin)Main.getInstance(), (Runnable)new Runnable() {
               // @Override
                public void run() {
                    if (MySQL.contains(p.getUniqueId().toString())) {
                        p.getInventory().setItem(JoinListener.this.cfg.getInt("Slot"), JoinListener.this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', JoinListener.this.cfg.getString("Name.On"))));
                    }
                    else {
                        p.getInventory().setItem(JoinListener.this.cfg.getInt("Slot"), JoinListener.this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', JoinListener.this.cfg.getString("Name.Off"))));
                    }
                }
            }, 5L);
        }
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        if (p.hasPermission("system.nick") && e.getMaterial() == Material.NAME_TAG) {
            if (MySQL.contains(p.getUniqueId().toString())) {
                p.getInventory().setItem(this.cfg.getInt("Slot"), this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Name.Off"))));
                MySQL.removePlayer(p.getUniqueId().toString());
                p.sendMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Prefix"))) + "§7Du hast den §f§lAutoNick §cdeaktiviert§7.");
            } else {
                p.getInventory().setItem(this.cfg.getInt("Slot"), this.createItem(Material.NAME_TAG, ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Name.On"))));
                MySQL.addPlayer(p.getUniqueId().toString());
                p.sendMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', this.cfg.getString("Prefix"))) + "§7Du hast den §f§lAutoNick §aaktiviert§7.");
            }
        }
    }
    
    public ItemStack createItem(final Material mat, final String dis) {
        final ItemStack i = new ItemStack(mat);
        final ItemMeta m = i.getItemMeta();
        m.setDisplayName(dis);
        i.setItemMeta(m);
        return i;
    }
}
