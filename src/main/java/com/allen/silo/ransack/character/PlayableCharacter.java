package com.allen.silo.ransack.character;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.RandomUtility;

public abstract class PlayableCharacter extends BaseCharacter {
	public static Logger logger = Logger.getLogger(PlayableCharacter.class.getName());
	
	private final boolean isNPC;
	
	protected Queue<Location> moveQueue;
		
	public PlayableCharacter(Location l, String imageFileName, boolean isNPC, BasicMap m, String name){
		super(l, imageFileName, m, name);
		this.isNPC = isNPC;
		moveQueue = new LinkedList<Location>();
	}
	
	public void move(BasicMap m){
		//logger.log(Level.INFO, "Queue size: " + moveQueue.size());
		switch(direction){
		case EAST:
			this.sprite.setTexture(spriteImages.get(4));
			break;
		case WEST:
			this.sprite.setTexture(spriteImages.get(6));
			break;
		case NORTH:
			this.sprite.setTexture(spriteImages.get(0));
			break;
		case SOUTH:
			this.sprite.setTexture(spriteImages.get(2));
			break;
		}
		if(moveQueue.peek()!=null){
			Location oldL = moveQueue.remove();
			this.setLocation(oldL);
			point.preUpdate();
			point.set(this.getX()*Constants.BLOCKSIZE, this.getY()*Constants.BLOCKSIZE);
		}
	}

	public boolean isNPC() {
		return isNPC;
	}
	
	public void enqueueRandom(PlayableMap currentMap){
		int moveOrNot = RandomUtility.randRange(0, 100);
		if (moveOrNot == 5){
			List<Location> cardinals = getCardinals();
			int moveDirection = Constants.MOVE_KEYS.get(RandomUtility.randRange(0, 4));
			Location newL = getNewLocation(moveDirection, this.getLocation());
			enqueueMove(newL, currentMap);
		}
	}
	
	public void enqueueMove(Location newL, PlayableMap currentMap){
		String isTileOccupied = currentMap.isTileOccupied(newL);
		if( isTileOccupied != null){
			logger.log(Level.INFO, "Tile occupied by: " + isTileOccupied);
			return;
		}
		if (currentMap.isMovable(newL)){
			currentMap.setGameTileOccupied(newL, this.name);
			currentMap.clearTileProperty(this.getLocation());
			moveQueue.add(newL);			
		}
	}
}
