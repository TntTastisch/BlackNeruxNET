package de.TeeJan.BlackNerux.listeners;

        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.BlockBreakEvent;
        import org.bukkit.event.block.BlockPlaceEvent;
        import org.bukkit.event.player.PlayerDropItemEvent;
        import org.bukkit.event.player.PlayerPickupItemEvent;

public class NoBuildListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event){
        event.setCancelled(true);
    }
}
