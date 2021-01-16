package md.takkapi.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Boots implements Listener, CommandExecutor {

	static Main plugin;
	public Boots(Main instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("boots")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command is player only!");
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("plugin.boots")) {
				player.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
				return true;
			}
			if(player.getInventory().firstEmpty() == -1) {
				// Inventory full
				Location loc = player.getLocation();
				World world = player.getWorld();
				
				world.dropItemNaturally(loc, getItem());
				player.sendMessage(ChatColor.RED + "Your inventory is full! Item has been droped near you");
				return true;
			}
			player.getInventory().addItem(getItem());
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bHere is a gift for you!&e&nBunny &r&eBoots &r&b!"));
			return true;
			
		}
		return false;
	}
	public ItemStack getItem() {
		
		// boots
		ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta meta = boots.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Boots of Leaping");
		List<String> lore = new ArrayList<String>() ;
		lore.add("");
		lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Boing!");
		meta.setLore(lore);
		
		meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		boots.setItemMeta(meta);
		
		return boots;
	}
	
	@EventHandler
	public void onJump(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getBoots() != null) 
			if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping"))
				if (player.getInventory().getBoots().getItemMeta().hasLore())
					if (event.getFrom().getY() < event.getTo().getY() &&
							player.getLocation().subtract(0 , 1 , 0).getBlock().getType() != Material.AIR) {
						player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
						
					}
		
		
		
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(event.getCause() == DamageCause.FALL) {
				if (player.getInventory().getBoots() != null) 
					if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping"))
						if (player.getInventory().getBoots().getItemMeta().hasLore()) {
							event.setCancelled(true);
						}
			}
		}
	}
	
}
