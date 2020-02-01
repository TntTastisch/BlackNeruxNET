package de.TntTastisch.BlackNerux.listener;

import de.TntTastisch.BlackNerux.LobbySystem;
import de.TntTastisch.BlackNerux.api.ItemAPI;
import de.TntTastisch.BlackNerux.systems.Data;
import de.TntTastisch.BlackNerux.utils.PlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import java.util.HashMap;

public class EnderpearlListener implements Listener {

    private HashMap<Player, EnderPearl> enderPearls = new HashMap<Player, EnderPearl>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        final Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➦ §5§lEnderperle")){
                event.setCancelled(true);

                player.getInventory().setItem(3, new ItemAPI(Material.FIREWORK_CHARGE).setDisplayname("§7➦ §cGadget wird neugeladen...").create());
                player.updateInventory();

                EnderPearl enderPearl = player.launchProjectile(EnderPearl.class);
                enderPearl.setPassenger(player);
                enderPearls.put(player, enderPearl);

                Bukkit.getScheduler().runTaskLater(LobbySystem.getPlugin(LobbySystem.class), new Runnable() {
                    public void run() {
                        player.getInventory().setItem(3, new ItemAPI(Material.ENDER_PEARL).setDisplayname("§8➦ §5§lEnderperle").create());
                        player.updateInventory();
                    }
                }, 20 * 3);

            }
        }


    }

    @EventHandler
    public void onVehicle(VehicleExitEvent event){
        if(event.getExited() instanceof  Player){
            if(enderPearls.containsKey(event.getExited())){
                enderPearls.get(event.getExited()).remove();
            }
        }
    }
}
