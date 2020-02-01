package de.TeeJan.BlackNerux.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import javax.swing.*;

public class WeatherListener implements Listener {

    @EventHandler
    public void onWeather(WeatherChangeEvent event){
        event.setCancelled(true);
    }
}
