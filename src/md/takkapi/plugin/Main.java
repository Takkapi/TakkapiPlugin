package md.takkapi.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import md.takkapi.plugin.commands.Feed;
import md.takkapi.plugin.commands.Heal;
import md.takkapi.plugin.commands.TakkapiP;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		registerCmd();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerCmd() {
		this.getCommand("heal").setExecutor(new Heal(this));
		this.getCommand("feed").setExecutor(new Feed(this));
		this.getCommand("takkapip").setExecutor(new TakkapiP(this));
	}
	
}
