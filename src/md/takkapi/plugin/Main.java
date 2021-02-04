package md.takkapi.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	// Takkapi Plugin
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		//takkapip
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
		
		//20
		
		return false;
		
	}
	
}
