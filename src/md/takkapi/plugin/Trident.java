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
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Trident implements Listener, CommandExecutor{
	
	static Main plugin;
	public Trident(Main instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("trident")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command is player only!");
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("plugin.trident")) {
				player.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
				return true;
			}
			if(player.getInventory().firstEmpty() == -1) {
				// Inventory full
				Location loc = player.getLocation();
				World world = player.getWorld();
				
				world.dropItemNaturally(loc, getItem1());
				player.sendMessage(ChatColor.RED + "Your inventory is full! Item has been droped near you");
				return true;
			}
			player.getInventory().addItem(getItem1());
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bHere is a gift for you! A super &e&nTrident &r&b!"));
			return true;
			
		}
		
		return false;
	}
	
	
	public ItemStack getItem1() {
		
		// trident
		ItemStack trident = new ItemStack(Material.TRIDENT);
		ItemMeta meta = trident.getItemMeta();
					
		meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Ancient Trident");
		List<String> lore1 = new ArrayList<String>();
		lore1.add("");
		lore1.add(ChatColor.translateAlternateColorCodes('&', "&7(Right Click) &a&oSpawn minions"));
		lore1.add(ChatColor.translateAlternateColorCodes('&', "&7(Left Click) &a&oShoot explosives"));
		meta.setLore(lore1);
					
		meta.addEnchant(Enchantment.LOYALTY, 10, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					
		trident.setItemMeta(meta);
		
		return trident;
		
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.TRIDENT))
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				Player player = (Player) event.getPlayer();
				//Right click
				if(event.getAction() == Action.RIGHT_CLICK_AIR) {
					if (!plugin.list.contains(player.getName()))
						plugin.list.add(player.getName());
					return;
				}
				
				// Left Click
				if (event.getAction() == Action.LEFT_CLICK_AIR) {
					player.launchProjectile(Fireball.class);
				}
			}
		if(plugin.list.contains(event.getPlayer().getName())) {
			plugin.list.remove(event.getPlayer().getName());
		}
			
		
	}
	
	@EventHandler()
	public void onLand(ProjectileHitEvent event) {
		if (event.getEntityType() == EntityType.TRIDENT) {
			if (event.getEntity().getShooter() instanceof Player) {
				Player player = (Player) event.getEntity().getShooter();
				if (plugin.list.contains(player.getName())) {
					// spawn zombie
					Location loc = event.getEntity().getLocation();
					loc.setY(loc.getY() + 1);
					
					for (int i = 1; i < 4; i++) {
						loc.getWorld().spawnEntity(loc, EntityType.DROWNED);
						loc.setX(loc.getX() + i);
					}
				}
			}
		}
	}
	
}
