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
	protected int stepIndex;
	
		
	public PlayableCharacter(Location l, String name, boolean isNPC, BasicMap m){
		super(l, name, m);
		this.isNPC = isNPC;
		this.stepIndex = 0;
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
	
	public void takeStep(){
		this.stepIndex = this.stepIndex + Constants.WALKING_LIST.get(this.stepIndex);
		this.stepIndex = (this.stepIndex + 1)%4;
		this.currentSpriteImage = spriteImages.get(this.stepIndex);
	}
	
	/*
	 * 
	 *def takeStep(self):
        self.imgIdx = self.imgIdx + const.walkingList[self.stepIdx]
        self.stepIdx = ( self.stepIdx + 1 ) % 4
        self.image = self.images[self.imgIdx]
	 */
	
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
		if (currentMap.isMovable(newL)){
			String isCellOccupied = currentMap.isTileOccupied(newL);
			if( isCellOccupied != null){
				logger.log(Level.INFO, "Tile occupied by: " + isCellOccupied);
				return;
			}
			currentMap.setGameTileOccupied(newL, this.name);
			currentMap.clearTileProperty(this.getLocation());
			moveQueue.add(newL);			
		}
	}
}
