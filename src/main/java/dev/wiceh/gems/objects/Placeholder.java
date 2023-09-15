package dev.wiceh.gems.objects;

import dev.wiceh.gems.Gems;
import dev.wiceh.gems.utils.Utils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholder extends PlaceholderExpansion {

    private final Gems plugin;

    public Placeholder(Gems plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "gems";
    }

    @Override
    public @NotNull String getAuthor() {
        return "wIceh";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if(params.equalsIgnoreCase("amount")) {
            return Utils.withLargeIntegers(plugin.dataConfig.getInt(player.getUniqueId().toString()));
        }
        return null;
    }
}
