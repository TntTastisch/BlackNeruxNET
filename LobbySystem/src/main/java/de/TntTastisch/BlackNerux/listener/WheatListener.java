package de.TntTastisch.BlackNerux.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class WheatListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        if(event.getAction() == Action.PHYSICAL){
            Block block = event.getClickedBlock();

            if(block == null)
                return;

            int blockType = block.getTypeId();

            if(blockType == Material.SOIL.getId()) {

                event.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
                event.setCancelled(true);

                block.setTypeId(blockType);
                block.setData(block.getData());
            }
        }
    }
}
