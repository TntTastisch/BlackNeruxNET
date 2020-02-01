package de.TntTastisch.BlackNerux.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.swing.text.html.parser.Entity;

public class ItemFrameistener implements Listener {

    @EventHandler
    public void ItemFrameDestory(HangingBreakByEntityEvent event) {

        if (event.getEntity() instanceof ItemFrame)
            event.setCancelled(true);
    }
}
