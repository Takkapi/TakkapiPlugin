package md.takkapi.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import md.takkapi.plugin.Main;

public class Feed implements CommandExecutor{

	static Main plugin;
	public Feed(Main main) {
		plugin = main;
	}
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("feed")) {
			if (sender.hasPermission("takkapi.feed")) {
				// /heal
				// /heal <player-name>
				if (args.length == 0) {
					if (sender instanceof Player) {
						//heal
						Player player = (Player) sender;
						player.setFoodLevel(plugin.getConfig().getInt("feed"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("feed-message")));
						return true;
					}
					sender.sendMessage(ChatColor.DARK_RED + "Ussage: /feed <player_name>");
					return true;
				}
				if (args.length >= 1) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target != null) {
						target.setFoodLevel(plugin.getConfig().getInt("feed"));
						target.sendMessage(ChatColor.GREEN + "You have been feeded!");
						sender.sendMessage(ChatColor.GREEN + "You feeded " + target.getName());
						return true;
					}
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("feed-message")));
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission to execute this command");
			}
		}
		
		return false;
		
	}
	
}
