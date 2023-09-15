package dev.wiceh.gems;

import dev.wiceh.gems.commands.GemsCommand;
import dev.wiceh.gems.listeners.OnJoin;
import dev.wiceh.gems.managers.GemsManager;
import dev.wiceh.gems.objects.Placeholder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Gems extends JavaPlugin {

    private File dataFile;
    public FileConfiguration dataConfig;

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");
        loadConfig();
        loadDataConfig();
        registerCommands();
        registerListeners();
        new Placeholder(this).register();
    }

    private void registerCommands() {
        getCommand("gems").setExecutor(new GemsCommand(new GemsManager(this), this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new OnJoin(this), this);
    }

    private void loadDataConfig() {
        getDataFolder().mkdirs();
        dataFile = new File(getDataFolder(), "data.yml");
        if(!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void saveDataConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
