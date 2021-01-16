package md.takkapi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Low implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("low")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command is player only!");
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("plugin.low")) {
				player.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
				return true;
			}
			if (args.length == 0) {
				// /low
				TextComponent message = new TextComponent("Heal Yourself");
				message.setColor(ChatColor.GREEN);
				message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/low heal"));
				message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click heal yourself").color(ChatColor.GREEN).italic(true).bold(true).create()));
				player.spigot().sendMessage(message);
				
				return true;
			}
			// /low heal
			if (args[0].equalsIgnoreCase("heal")) {
				player.setHealth(20.0);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + player.getName());
				return true;
			}
			player.sendMessage(ChatColor.RED + "Usage: /low");
			return true;
		}
		
		return false;
	}

}
