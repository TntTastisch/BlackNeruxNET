package de.TntTastisch.BlackNerux.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderdragonListener implements Listener {

    @EventHandler
    public void onEnderdragon(PlayerInteractEvent event){

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.DRAGON_EGG){
                event.setCancelled(true);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE){
                event.setCancelled(true);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.FURNACE || event.getClickedBlock().getType() == Material.BURNING_FURNACE){
                event.setCancelled(true);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.CHEST){
                event.setCancelled(true);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.WORKBENCH){
                event.setCancelled(true);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.NOTE_BLOCK){
                event.setCancelled(true);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.JUKEBOX){
                event.setCancelled(true);
            }
        }
    }
}
