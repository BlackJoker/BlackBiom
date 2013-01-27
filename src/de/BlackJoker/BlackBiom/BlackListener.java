package de.BlackJoker.BlackBiom;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class BlackListener implements Listener, CommandExecutor{
	private BlackBiom plugin = null;
	private Map<String,Block> pos1Blocks = new HashMap<String,Block>();
	private Map<String,Block> pos2Blocks = new HashMap<String,Block>();
	
	public BlackListener(BlackBiom tplugin){
		plugin = tplugin;
		plugin.getServer().getPluginManager().registerEvents(this,plugin);
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerQuit(PlayerQuitEvent event){
		this.removePlayerfromMap(event.getPlayer().getName());
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerKicked(PlayerKickEvent event){
		this.removePlayerfromMap(event.getPlayer().getName());
	}

	private void removePlayerfromMap(String name){
		pos1Blocks.remove(name);
	}
	
	//TODO: start running on command . 
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getPlayer() instanceof Player){
			if(!event.getPlayer().hasPermission("BlackBiom.changebiome"))
				return;
			if(event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().getItemInHand().getType() == Material.APPLE){
				BlackFunc.sendMessageToPlayer(event.getPlayer(), "You selected as first Position(X:"+event.getClickedBlock().getX()+"Y:"+event.getClickedBlock().getY()+"Z:"+event.getClickedBlock().getZ()+",World:"+event.getClickedBlock().getWorld().getName()+") in Biome" + event.getClickedBlock().getBiome().toString());
				pos1Blocks.put(event.getPlayer().getName(), event.getClickedBlock());
				event.setCancelled(true);
			}
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().getItemInHand().getType() == Material.APPLE){
				BlackFunc.sendMessageToPlayer(event.getPlayer(), "You selected as second Position(X:"+event.getClickedBlock().getX()+"Y:"+event.getClickedBlock().getY()+"Z:"+event.getClickedBlock().getZ()+",World:"+event.getClickedBlock().getWorld().getName()+") in Biome" + event.getClickedBlock().getBiome().toString());
				pos2Blocks.put(event.getPlayer().getName(), event.getClickedBlock());
				event.setCancelled(true);
			}
		} 
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(sender instanceof Player  ){
			Player p = (Player)sender;
			if(commandLabel.equalsIgnoreCase("changebiome")){
				if(!p.hasPermission("BlackBiom.changebiome"))
					return false;
				if(args.length == 1){
					
					if(pos1Blocks.get(p.getName()) != null && pos2Blocks.get(p.getName()) != null){
	
						try {
							if(BlackFunc.isValidBiome(args[0])){
								BlackArea area = new BlackArea(plugin,pos1Blocks.get(p.getName()),pos2Blocks.get(p.getName()),p);
								area.changeBiome(args[0]);	
								pos1Blocks.put(p.getName(), null);
								pos2Blocks.put(p.getName(), null);
							}else{
								BlackFunc.sendFailMessageToPlayer(p, "Wrong Biomename.");
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}	else{
						if(pos1Blocks.get(p.getName()) == null){
							BlackFunc.sendFailMessageToPlayer(p, "You didn't select a first Position");
						}else{
							BlackFunc.sendFailMessageToPlayer(p, "You didn't select a second Position");
						}
					}
				}
				else{
					BlackFunc.sendFailMessageToPlayer(p, "Wrong Parameter!");
				}
			}
			if(commandLabel.equalsIgnoreCase("biomes")){
				if(!p.hasPermission("BlackBiom.Biomes"))
					return false;
				if(args.length == 0){
					BlackFunc.sendMessageToPlayer(p, "BEACH, DESERT, DESERT_HILLS, EXTREME_HILLS, FOREST, " +
							"FOREST_HILLS, FROZEN_OCEAN, FROZEN_RIVER, ICE_MOUNTAINS, ICE_PLAINS, HELL, " +
							"JUNGLE, JUNGLE_HILLS, MUSHROOM_ISLAND, MUSHROOM_SHORE, OCEAN, PLAINS, RIVER, " +
							"SKY, SMALL_MOUNTAINS, SWAMPLAND, TAIGA, TAIGA_HILLS");
				}else{
					BlackFunc.sendFailMessageToPlayer(p, "Too much Parameter, you don't need any!");
				}
			}	
		}else{
			sender.sendMessage("You are not allowed to do this!");
		}
		
		return true;	
	}
	
}
