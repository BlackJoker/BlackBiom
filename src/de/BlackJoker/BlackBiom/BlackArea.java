package de.BlackJoker.BlackBiom;

import java.util.HashSet;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;



public class BlackArea{
	
	@SuppressWarnings("unused")
	private BlackBiom plugin = null;
	private Block pos1 = null;
	private Block pos2 = null;	
	private int maxheight;  
	private int highx;
	private int highz;
	private int lowx;
	private int lowz;
	private Player p;
	
	
	public BlackArea(final BlackBiom tplugin,Block tpos1, Block tpos2, Player tp)  throws Exception{
		pos1 = tpos1;
		pos2 = tpos2;
		p = tp;
		if (pos1.getWorld() != pos2.getWorld()){
			throw new Exception("Blocks are not in the same world");
		}
		
		maxheight = pos1.getWorld().getMaxHeight();

		if(pos1.getX() < pos2.getX()){
			lowx = pos1.getX();
			highx = pos2.getX();
		}
		else{
			lowx = pos2.getX();
			highx = pos1.getX();
		}
		
		if(pos1.getZ() < pos2.getZ()){
			lowz= pos1.getZ();
			highz = pos2.getZ();
		}
		else{
			lowz = pos2.getZ();
			highz = pos1.getZ();
		}
		
		
		if(tplugin != null){
			plugin = tplugin;
		}else{
			throw new Exception("Blackbiom is null in BlackArea");
		}
	}

	public void changeBiome(String newBiomeName) {
		HashSet<Chunk> chunksToReload = new HashSet<Chunk>();
		BlackFunc.sendSuccessMessageToPlayer(p, "Biome changing is started.");
		long startTime = System.nanoTime();
		Biome newBiome = BlackFunc.StringToBiome(newBiomeName);
		try{
		
			if(newBiome != null){
				World myWorld = pos1.getWorld();
				for(int x = lowx; x <= highx; x++){
					for(int z = lowz; z <= highz; z++){
						for(int y = 0; y < maxheight; y++){
							Block b = myWorld.getBlockAt(x, y, z);
							b.setBiome(newBiome);
							chunksToReload.add(b.getChunk());
						}
					}
				}
							
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			BlackFunc.sendFailMessageToPlayer(p, "Biome Changeing failed");
		}
		
		long endTime = System.nanoTime();
		long diffTime = endTime -startTime;
		double timeNeededInSeconds = diffTime/1000000000.0;
		BlackFunc.sendSuccessMessageToPlayer(p, "Biome change successfull in "+timeNeededInSeconds+" seconds.");
		for(Chunk chunk : chunksToReload){
			chunk.getWorld().refreshChunk(chunk.getX(),chunk.getZ());
		}
		BlackFunc.sendSuccessMessageToPlayer(p, "Biome Chunks are reloaded for all players");
	}
	public void change(Block block){
		
	}
}
