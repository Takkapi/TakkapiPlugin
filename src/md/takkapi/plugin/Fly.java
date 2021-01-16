package md.takkapi.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Fly implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("status")) {
			if(sender instanceof Player) {
				// player
				Player player = (Player) sender;
				if (player.hasPermission("plugin.status")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Plugin is running on version &a1.0!"));
					return true;
				}
				player.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
			} else {
				//console
				sender.sendMessage("Plugin is running on version 1.0!");
				return true;
			}
		}
		
		if (label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("lch")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command is player only!");
				return true;
			}
			Player player = (Player) sender;
			
			if(player.hasPermission("plugin.launch")) {
				if(args.length == 0) {
					//launch
					player.sendMessage(ChatColor.LIGHT_PURPLE + ""+ ChatColor.BOLD + "Whooosh!");
					player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
					return true;
				}
				
				// launch <number>
				if (isNum(args[0])) {
					player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Whooosh!");
					player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
				} else {
					player.sendMessage(ChatColor.RED + "Usage: /launch <number-value>");
				}
				return true;
			} else {
				player.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
			}
			
			
		} 
		
		return false;
		
	}
	
	public boolean isNum(String num) {
		try {
			Integer.parseInt(num);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}

}
