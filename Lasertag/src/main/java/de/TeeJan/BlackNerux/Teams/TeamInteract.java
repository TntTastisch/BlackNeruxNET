package de.TeeJan.BlackNerux.Teams;

import de.TeeJan.BlackNerux.LaserTag;
import de.TeeJan.BlackNerux.systems.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

import org.bukkit.event.EventHandler;


import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class TeamInteract implements Listener
{
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getName().equalsIgnoreCase("§7Wähle ein Team")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cRot §7(" + Teams.rot.size() + "/" + getMax("Rot") + ")")) {
                p.closeInventory();
                if (Teams.rot.size() != getMax("Rot")) {
                    Teams.blau.remove(p);
                    Teams.rot.remove(p);
                    Teams.gelb.remove(p);
                    Teams.grün.remove(p);
                    Teams.rot.add(p);
                    p.sendMessage(String.valueOf(Data.prefix) + "Du hast das §cRote §7Team betreten!");
                }
                else {
                    p.sendMessage(String.valueOf(Data.prefix) + "§cDieses Team ist voll");
                }
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§9Blau §7(" + Teams.blau.size() + "/" + getMax("Blau") + ")")) {
               
                p.closeInventory();
                if (Teams.blau.size() != getMax("Blau")) {
                    Teams.blau.remove(p);
                    Teams.rot.remove(p);
                    Teams.gelb.remove(p);
                    Teams.grün.remove(p);
                    Teams.blau.add(p);
                    p.sendMessage(String.valueOf(Data.prefix) + "Du hast das §3Blaue §7Team betreten!");
                }
                else {
                    p.sendMessage(String.valueOf(Data.prefix) + "Das §3Blaue §7Team ist voll :(");
                }
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§aGrün §7(" + Teams.grün.size() + "/" + getMax("Gruen") + ")")) {
                p.closeInventory();
                if (Teams.grün.size() != getMax("Gruen")) {
                    Teams.blau.remove(p);
                    Teams.rot.remove(p);
                    Teams.gelb.remove(p);
                    Teams.grün.remove(p);
                    Teams.grün.add(p);
                    p.sendMessage(String.valueOf(Data.prefix) + "Du hast das §aGrüne §7Team betreten!");
                }
                else {
                    p.sendMessage(String.valueOf(Data.prefix) + "Das §aGrüne §7Team ist voll :(");
                }
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§eGelb §7(" + Teams.gelb.size() + "/" + getMax("Gelb") + ")")) {
                p.closeInventory();
                if (Teams.gelb.size() != getMax("Gelb")) {
                    Teams.blau.remove(p);
                    Teams.rot.remove(p);
                    Teams.gelb.remove(p);
                    Teams.grün.remove(p);
                    Teams.gelb.add(p);
                    p.sendMessage(String.valueOf(Data.prefix) + "Du hast das §6Gelbe §7Team betreten!");
                }
                else {
                    p.sendMessage(String.valueOf(Data.prefix) + "Das §6Gelbe §7Team ist voll :(");
                }
            }

        }
    }

    public static int getMax(final String color) {
        int i = 0;
        final File f = new File(LaserTag.getPlugin(LaserTag.class).getDataFolder().getPath(), "Teams.yml");
        final FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(f);

        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        i = cfg.getInt(color + ".Size");
        return i;
    }

    public static int getCompleteMax() {
        int i = 0;
        i = getMax("Blau") + getMax("Gelb") + getMax("Rot") + getMax("Gruen");
        return i;
    }
}
