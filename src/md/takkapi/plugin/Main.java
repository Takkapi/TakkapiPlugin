package md.takkapi.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;


public class Main extends JavaPlugin implements Listener {
	
	public List<String> list = new ArrayList<String>();

	@Override
	public void onEnable() {
		this.getCommand("launch").setExecutor(new Fly());
		this.getCommand("low").setExecutor(new Low());
		this.getCommand("trident").setExecutor(new Trident(null));
		this.getCommand("boots").setExecutor(new Boots(null));
		this.getServer().getPluginManager().registerEvents(new Boots(this), this);
		this.getServer().getPluginManager().registerEvents(new Trident(this), this);
	}
	
	@Override
	public void onDisable() {
	}
	
	public Inventory inv;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("armor")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command is player only!");
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("plugin.armor")) {
				player.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
				return true;
			}
			// open GUI
		}
		
		return false;
	}
	
	public void createInv() {
		
		inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Armors Kits");
		
		ItemStack item = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta meta = item.getItemMeta();
		
		//Leather
		meta.setDisplayName(ChatColor.GRAY + "Default");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Click to select the kit!");
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		// Chain
		item.setType(Material.CHAINMAIL_HELMET);
		meta.setDisplayName(ChatColor.WHITE + "Chain");
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		//Gold
		item.setType(Material.GOLDEN_HELMET);
		meta.setDisplayName(ChatColor.GOLD + "Gold");
		item.setItemMeta(meta);
		inv.setItem(2, item);
		
		// Iron
		item.setType(Material.IRON_HELMET);
		meta.setDisplayName(ChatColor.WHITE + "Iron");
		item.setItemMeta(meta);
		inv.setItem(3, item);
		
		// Diamond
		item.setType(Material.DIAMOND_HELMET);
		meta.setDisplayName(ChatColor.AQUA + "Diamond");
		item.setItemMeta(meta);
		inv.setItem(4, item);
		
		// Netherite
		item.setType(Material.NETHERITE_HELMET);
		meta.setDisplayName(ChatColor.BLACK + "Netherite");
		item.setItemMeta(meta);
		inv.setItem(5, item);
		
		//Close button
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED + "Close");
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(8, item);
		
	}

	
}
