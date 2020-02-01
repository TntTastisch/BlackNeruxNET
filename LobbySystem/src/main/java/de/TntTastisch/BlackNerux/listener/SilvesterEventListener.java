package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.defaults.SpreadPlayersCommand;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import javax.swing.*;

public class SilvesterEventListener implements Listener {

    public int i = 0;

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §3Feuerwerk Kracher")) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {

                player.getInventory().setItem(3, new ItemAPI(Material.FIREWORK_CHARGE).setDisplayname("§8➦ §cBitte warten...").create());
                player.updateInventory();

                final Firework f = (Firework)player.getPlayer().getWorld().spawn(event.getClickedBlock().getLocation(), (Class)Firework.class);
                final FireworkMeta fm = f.getFireworkMeta();
                if(i == 0) {
                    fm.addEffect(FireworkEffect.builder().flicker(true).trail(true)
                            .with(FireworkEffect.Type.BALL_LARGE).withColor(Color.YELLOW).withColor(Color.ORANGE).withColor(Color.SILVER).withColor(Color.RED).withColor(Color.GREEN)
                            .withColor(Color.AQUA).withColor(Color.BLUE).build());

                    i = 1;
                } else if(i == 1){
                    fm.addEffect(FireworkEffect.builder().flicker(true).trail(true)
                            .with(FireworkEffect.Type.CREEPER).withColor(Color.YELLOW).withColor(Color.ORANGE).withColor(Color.SILVER).withColor(Color.RED).withColor(Color.GREEN)
                            .withColor(Color.AQUA).withColor(Color.BLUE).build());
                    i = 2;
                } else if(i == 2){
                    fm.addEffect(FireworkEffect.builder().flicker(true).trail(true)
                            .with(FireworkEffect.Type.BURST).withColor(Color.YELLOW).withColor(Color.ORANGE).withColor(Color.SILVER).withColor(Color.RED).withColor(Color.GREEN)
                            .withColor(Color.AQUA).withColor(Color.BLUE).build());
                    i = 3;
                } else if(i == 3){
                    fm.addEffect(FireworkEffect.builder().flicker(true).trail(true)
                            .with(FireworkEffect.Type.BALL).withColor(Color.YELLOW).withColor(Color.ORANGE).withColor(Color.SILVER).withColor(Color.RED).withColor(Color.GREEN)
                            .withColor(Color.AQUA).withColor(Color.BLUE).build());
                    i = 4;
                } else if(i == 4){
                    fm.addEffect(FireworkEffect.builder().flicker(true).trail(true)
                            .with(FireworkEffect.Type.STAR).withColor(Color.YELLOW).withColor(Color.ORANGE).withColor(Color.SILVER).withColor(Color.RED).withColor(Color.GREEN)
                            .withColor(Color.AQUA).withColor(Color.BLUE).build());

                    i = 0;
                }
                fm.setPower(3);
                f.setFireworkMeta(fm);


                Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                    public void run() {
                        player.getInventory().setItem(3, new ItemAPI(Material.FIREWORK).setDisplayname("§8➦ §3Feuerwerk Kracher").create());
                        player.updateInventory();
                    }
                }, 20 * 3);


            } else if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR){
                player.sendMessage(Data.prefix + "§cDu musst die Rakete auf dem Boden zünden!");
            }
        }
    }
}
