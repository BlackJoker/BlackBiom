package de.BlackJoker.BlackBiom;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class BlackBiom extends JavaPlugin {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {
		log.info("[BlackBiom] had been enabled!");
		BlackListener listener = new BlackListener(this);
		this.getCommand("changebiome").setExecutor(listener);
		this.getCommand("biomes").setExecutor(listener);
		
	}
	
	@Override
	public void onDisable() {
		log.info("[BlackBiom] had been disabled!");
		
	}
	
	
	
}
