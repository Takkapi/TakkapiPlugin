package md.takkapi.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import md.takkapi.plugin.Main;

public class TakkapiP implements CommandExecutor {

	static Main plugin;
	public TakkapiP(Main main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("Takkapip")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("takkapi.takkapip")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTakkapiPlugin made by &6&lTakkapi"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aVersion 1.0"));
				} else {
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to execute this command");
				}
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Subscribe to Takkapi on YouTube");
				return true;
			}
			
		}
		
		return false;
	}
	
}
