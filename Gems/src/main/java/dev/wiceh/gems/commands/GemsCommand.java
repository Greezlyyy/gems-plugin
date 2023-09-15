package dev.wiceh.gems.commands;

import dev.wiceh.gems.Gems;
import dev.wiceh.gems.managers.GemsManager;
import dev.wiceh.gems.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GemsCommand implements CommandExecutor {

    public String prefix;
    private final GemsManager gemsManager;
    private final Gems plugin;

    public GemsCommand(GemsManager gemsManager, Gems plugin) {
        this.gemsManager = gemsManager;
        this.plugin = plugin;
        this.prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("gems.use")) {
                if(args.length == 3 && args[0].equalsIgnoreCase("set")) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    String amount = args[2];
                    if(plugin.dataConfig.contains(offlinePlayer.getUniqueId().toString())) {
                        if(Utils.isInteger(amount)) {
                            gemsManager.setGems(offlinePlayer, Integer.parseInt(amount));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gems-setted-message").replace("%prefix%", prefix).replace("%player%", offlinePlayer.getName()).replace("%amount%", Utils.withLargeIntegers(Integer.parseInt(amount)))));
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-amount-message").replace("%prefix%", prefix)));
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-never-joined-message").replace("%prefix%", prefix)));
                    }
                }else if(args.length == 3 && args[0].equalsIgnoreCase("add")) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    String amount = args[2];
                    if(plugin.dataConfig.contains(offlinePlayer.getUniqueId().toString())) {
                        if(Utils.isInteger(amount)) {
                            gemsManager.addGems(offlinePlayer, Integer.parseInt(amount));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gems-added-message").replace("%prefix%", prefix).replace("%amount%", Utils.withLargeIntegers(Integer.parseInt(amount))).replace("%player%", offlinePlayer.getName())));
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-amount-message").replace("%prefix%", prefix)));
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-never-joined-message").replace("%prefix%", prefix)));
                    }
                }else if(args.length == 1 && args[0].equalsIgnoreCase("see")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gems-see-message").replace("%prefix%", prefix).replace("%amount%", gemsManager.getGems(player))));
                }else if(args.length == 2 && args[0].equalsIgnoreCase("see")) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    if(plugin.dataConfig.contains(offlinePlayer.getUniqueId().toString())) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gems-see-others-message").replace("%prefix%", prefix).replace("%player%", offlinePlayer.getName()).replace("%amount%", gemsManager.getGems(offlinePlayer))));
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-never-joined-message").replace("%prefix%", prefix)));
                    }
                }else if(args.length == 2 && args[0].equalsIgnoreCase("reset")) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    if(plugin.dataConfig.contains(offlinePlayer.getUniqueId().toString())) {
                        gemsManager.resetGems(offlinePlayer);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "gems-resetted-message").replace("%prefix%", prefix).replace("%player%", offlinePlayer.getName()));
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-never-joined-message").replace("%prefix%", prefix)));
                    }
                }else if(args.length == 3 && args[0].equalsIgnoreCase("remove")) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    String amount = args[2];
                    if(plugin.dataConfig.contains(offlinePlayer.getUniqueId().toString())) {
                        if(Utils.isInteger(amount)) {
                            if(plugin.dataConfig.getInt(offlinePlayer.getUniqueId().toString()) >= Integer.parseInt(amount)) {
                                gemsManager.removeGems(offlinePlayer, Integer.parseInt(amount));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("gems-removed-message").replace("%prefix%", prefix).replace("%amount%", Utils.withLargeIntegers(Integer.parseInt(amount))).replace("%player%", offlinePlayer.getName())));
                            }else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("doesnt-have-much-amount-message")).replace("%prefix%", prefix));
                            }
                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-amount-message").replace("%prefix%", prefix)));
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player-never-joined-message").replace("%prefix%", prefix)));
                    }
                }else if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin-reloaded-message").replace("%prefix%", prefix)));
                }else {
                    player.sendMessage("§3Commands list §7(/gems)");
                    player.sendMessage("§8▪ §b/gems set <player> <amount>");
                    player.sendMessage("§8▪ §b/gems add <player> <amount>");
                    player.sendMessage("§8▪ §b/gems remove <player> <amount>");
                    player.sendMessage("§8▪ §b/gems see [player]");
                    player.sendMessage("§8▪ §b/gems reset <player>");
                    player.sendMessage("§8▪ §b/gems reload");
                }
            }else {
                player.sendMessage("§cYou don't have the permission to execute this command!");
            }
        }
        return true;
    }
}
