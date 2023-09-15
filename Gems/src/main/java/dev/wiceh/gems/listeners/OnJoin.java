package dev.wiceh.gems.listeners;

import dev.wiceh.gems.Gems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    private final Gems plugin;

    public OnJoin(Gems plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!plugin.dataConfig.contains(event.getPlayer().getUniqueId().toString())) {
            plugin.dataConfig.set(event.getPlayer().getUniqueId().toString(), 0);
        }
    }
}
