package md.takkapi.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import md.takkapi.plugin.Main;

public class Heal implements CommandExecutor{

	static Main plugin;
	public Heal(Main main) {
		plugin = main;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("heal")) {
			if (sender.hasPermission("takkapi.heal")) {
				// /heal
				// /heal <player-name>
				if (args.length == 0) {
					if (sender instanceof Player) {
						//heal
						Player player = (Player) sender;
						player.setHealth(plugin.getConfig().getDouble("heal"));
						player.setFoodLevel(plugin.getConfig().getInt("feed"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("heal-message")));
						return true;
					}
					sender.sendMessage(ChatColor.DARK_RED + "Ussage: /heal <player_name>");
					return true;
				}
				if (args.length >= 1) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target != null) {
						target.setHealth(plugin.getConfig().getDouble("heal"));
						target.setFoodLevel(plugin.getConfig().getInt("feed"));
						target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("heal-message")));
						sender.sendMessage(ChatColor.GREEN + "You healed " + target.getName());
						return true;
					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("player.notFound")));
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission to execute this command");
			}
		}
		
		return false;
		
	}

}
