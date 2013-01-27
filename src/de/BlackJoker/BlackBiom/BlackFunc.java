package de.BlackJoker.BlackBiom;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public class BlackFunc {
	public static void sendMessageToPlayer(Player player, String msg){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "[BlackBiom]"+ChatColor.WHITE+msg);
	}
	public static void sendFailMessageToPlayer(Player player, String msg){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "[BlackBiom]"+ChatColor.RED+msg);
	}
	public static void sendSuccessMessageToPlayer(Player player, String msg){
		player.sendMessage(ChatColor.LIGHT_PURPLE + "[BlackBiom]"+ChatColor.GREEN+msg);
	}
	public static boolean isValidBiome(String string) {
		
		if("BEACH".equalsIgnoreCase(string) || 	
		"DESERT".equalsIgnoreCase(string) || 	
		"DESERT_HILLS".equalsIgnoreCase(string) || 	
		"EXTREME_HILLS".equalsIgnoreCase(string) ||	
		"FOREST".equalsIgnoreCase(string) || 	
		"FOREST_HILLS".equalsIgnoreCase(string) || 	
		"FROZEN_OCEAN".equalsIgnoreCase(string) || 	
		"FROZEN_RIVER".equalsIgnoreCase(string) || 	
		"ICE_MOUNTAINS".equalsIgnoreCase(string) || 
		"ICE_PLAINS".equalsIgnoreCase(string) || 	
		"HELL".equalsIgnoreCase(string) || 	
		"JUNGLE".equalsIgnoreCase(string) || 	
		"JUNGLE_HILLS".equalsIgnoreCase(string) || 	
		"MUSHROOM_ISLAND".equalsIgnoreCase(string) || 	
		"MUSHROOM_SHORE".equalsIgnoreCase(string) || 	
		"OCEAN".equalsIgnoreCase(string) || 	
		"PLAINS".equalsIgnoreCase(string) || 	
		"RIVER".equalsIgnoreCase(string) || 	
		"SKY".equalsIgnoreCase(string) || 	
		"SMALL_MOUNTAINS".equalsIgnoreCase(string) || 	
		"SWAMPLAND".equalsIgnoreCase(string) || 	
		"TAIGA".equalsIgnoreCase(string) ||
		"TAIGA_HILLS".equalsIgnoreCase(string)
		)
			return true;
		return false;
	}
	public static Biome StringToBiome(String newBiomeName) {
		Biome b = null;
		newBiomeName = newBiomeName.toUpperCase();
		switch(newBiomeName){
			case "BEACH":
				b = Biome.BEACH;
				break;
			case "DESERT":
				b = Biome.DESERT;
				break;
			case "DESERT_HILLS":
				b = Biome.DESERT_HILLS;
				break;
			case "EXTREME_HILLS":
				b = Biome.EXTREME_HILLS;
				break;
			case "FOREST":
				b = Biome.FOREST;
				break;
			case "FOREST_HILLS":
				b = Biome.FOREST_HILLS;
				break;
			case "FROZEN_OCEAN":
				b = Biome.FROZEN_OCEAN;
				break;
			case "FROZEN_RIVER":
				b = Biome.FROZEN_RIVER;
				break;
			case "ICE_MOUNTAINS":
				b = Biome.ICE_MOUNTAINS;
				break;
			case "ICE_PLAINS":
				b = Biome.ICE_PLAINS;
				break;
			case "HELL":
				b = Biome.HELL;
				break;
			case "JUNGLE":
				b = Biome.JUNGLE;
				break;
			case "JUNGLE_HILLS":
				b = Biome.JUNGLE_HILLS;
				break;
			case "MUSHROOM_ISLAND":
				b = Biome.MUSHROOM_ISLAND;
				break;
			case "MUSHROOM_SHORE":
				b = Biome.MUSHROOM_SHORE;
				break;
			case "OCEAN":
				b = Biome.OCEAN;
				break;
			case "PLAINS":
				b = Biome.PLAINS;
				break;
			case "RIVER":
				b = Biome.RIVER;
				break;
			case "SKY":
				b = Biome.SKY;
				break;
			case "SMALL_MOUNTAINS":
				b = Biome.SMALL_MOUNTAINS;
				break;
			case "SWAMPLAND":
				b = Biome.SWAMPLAND;
				break;
			case "TAIGA":
				b = Biome.TAIGA;
				break;
			case "TAIGA_HILLS":
				b = Biome.TAIGA_HILLS;
				break;
		}
		return b;
	}
}
