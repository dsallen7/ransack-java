package com.allen.silo.ransack.character;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
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
	
	public void move(){
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
			Location newL = moveQueue.remove();
			this.setLocation(newL);
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
	
	public Location getRandomLocation(){
		Location newL = null;
		int moveOrNot = RandomUtility.randRange(0, 100);
		if (moveOrNot == 5){
			int moveDirection = Constants.MOVE_KEYS.get(RandomUtility.randRange(0, 4));
			newL = getNewLocation(moveDirection, this.getLocation());
		}
		return newL;
	}
	
	public void enqueueMove(Location newL){
		moveQueue.add(newL);
	}
}
