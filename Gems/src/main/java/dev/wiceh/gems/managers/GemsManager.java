package dev.wiceh.gems.managers;

import dev.wiceh.gems.Gems;
import dev.wiceh.gems.utils.Utils;
import org.bukkit.OfflinePlayer;

public class GemsManager {

    private final Gems plugin;

    public GemsManager(Gems plugin) {
        this.plugin = plugin;
    }

    public String getGems(OfflinePlayer player) {
        return Utils.withLargeIntegers(plugin.dataConfig.getInt(player.getUniqueId().toString()));
    }

    public void setGems(OfflinePlayer player, int amount) {
        plugin.dataConfig.set(player.getUniqueId().toString(), amount);
        plugin.saveDataConfig();
    }

    public void addGems(OfflinePlayer player, int amount) {
        plugin.dataConfig.set(player.getUniqueId().toString(), plugin.dataConfig.getInt(player.getUniqueId().toString())+amount);
        plugin.saveDataConfig();
    }

    public void resetGems(OfflinePlayer player) {
        plugin.dataConfig.set(player.getUniqueId().toString(), 0);
        plugin.saveDataConfig();
    }

    public void removeGems(OfflinePlayer player, int amount) {
        plugin.dataConfig.set(player.getUniqueId().toString(), plugin.dataConfig.getInt(player.getUniqueId().toString())-amount);
        plugin.saveDataConfig();
    }
}
