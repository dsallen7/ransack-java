package com.allen.silo.ransack.character;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.graphics.Sprite;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.character.attributes.ScreenLocation;
import com.allen.silo.ransack.character.attributes.Script;
import com.allen.silo.ransack.handlers.EventMessageDataImpl;
import com.allen.silo.ransack.handlers.EventType;
import com.allen.silo.ransack.main.Ransack;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.FileUtility;
import com.allen.silo.ransack.utils.ImageUtility;
import com.allen.silo.ransack.utils.MathUtility;
import com.allen.silo.ransack.utils.RandomUtility;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public abstract class PlayableCharacter extends BaseCharacter {
	public static Logger logger = Logger.getLogger(PlayableCharacter.class.getName());
	
	private final boolean isNPC;
	protected Queue<MapLocation> moveQueue;
	protected int stepIndex;
	protected Script script;
	protected int currentHP;
	protected int maxHP;
	protected int currentMP;
	protected int maxMP;
	
	protected boolean isMoving;
	
	protected MapLocation oldLocation;
	protected List<ScreenLocation> interList;
	
	protected int walkCounter = 0;
	protected ScreenLocation screenLocation;
	
	
	public PlayableCharacter(MapLocation l, Script script, boolean isNPC, BasicMap m){
		super(l, script.getCharacterName(), m);
		Pixmap spriteSheet = new Pixmap(FileUtility.getCharSheet(script.getFileName()));
		spriteImages = ImageUtility.loadSpriteImages(spriteSheet);
		currentSpriteImage = spriteImages.get(2);
		direction = Direction.SOUTH;
		setSprite(new Sprite(currentSpriteImage));
		this.isNPC = isNPC;
		this.stepIndex = 0;
		moveQueue = new LinkedList<MapLocation>();
		this.script = script;
		screenLocation = MathUtility.mapLocationToScreenLocation(this.mapLocation);
	}
	
	public Texture getFace(){
		return spriteImages.get(16);
	}
	
	public void update(PlayableMap m){
		if (!isMoving){
			if(isNPC()){
				MapLocation newL = null;
				newL = getRandomLocation();
				processMove(newL, m);
			}else{
				//((Player) pc).getClock().tick();
			}
			move(true);
		}else{
			takeStep();
		}
		point.preUpdate();
		point.set(screenLocation.getLocX(), screenLocation.getLocY());
	}
	/*
	 * Takes a raw move from player key press or NPC random generator
	 * and determines is it is valid, if so, add to queue
	 */
	public void processMove(MapLocation newL, PlayableMap currentMap){
		if (isMoving())
			return;
		if (currentMap.isMovable(newL)){
			String isCellOccupied = currentMap.isTileOccupied(newL);
			if( isCellOccupied != null){
				// inter-playable interaction
				// only player can initiate interaction - for now
				if (getName().equals("player")){
					//Ransack.eventMessageBus.broadcast(EventType.OPENDIALOGUE.toString(), new EventMessageDataImpl(0, pc.getName(), isCellOccupied, playables.get(isCellOccupied).getDefaultMessage()));
					logger.log(Level.INFO, "from " + getName() + " to: " + isCellOccupied);
				}else{
					logger.log(Level.INFO, "NPC messaging player");
				}
				return;
			}
			currentMap.setCellOccupied(newL, getName());
			currentMap.clearCellOccupied(getLocation());	
			enqueueMove(newL);
		}
	}
	
	/*
	 * Execute a pre-validated move
	 * Set up the walking queue which will
	 * end with the character in the correct new location
	 */
	public void move(boolean intramap){
		updateDirection();
		if(moveQueue.peek()!=null){
			MapLocation newL = moveQueue.remove();
			this.interList = MathUtility.getInterpolatedLocations(this.getLocation(), newL, this.direction);
			walkCounter = 10;
			isMoving = true;
		}
	}
	
	/*
	 * Execute one frame (3 pixels) of a pre-validated move
	 * 
	 */
	
	public void takeStep(){
		if (getWalkCounter() == 1){
			walkCounter--;
			ScreenLocation newL = interList.get(10-walkCounter);
			this.screenLocation = newL;
			/*
			point.preUpdate();
			point.set(newL.getLocX(), newL.getLocY());
			*/
			this.setLocation(MathUtility.screenLocationToMapLocation(newL));
			isMoving = false;
		}else if (getWalkCounter() > 1){
			walkCounter--;
			ScreenLocation newL = interList.get(10-walkCounter);
			this.screenLocation = newL;
			/*
			point.preUpdate();
			point.set(newL.getLocX(), newL.getLocY());
			*/
			if (walkCounter % 2 == 0){
				this.stepIndex = Constants.WALKING_MAP.get(this.stepIndex);
				this.sprite.setTexture(spriteImages.get(this.stepIndex));
			}
		}
	}
	
	/*
	 * Update direction after move or other event
	 */
	public void updateDirection(){
		switch(direction){
		case WEST:
			this.sprite.setTexture(spriteImages.get(4));
			this.stepIndex = 4;
			break;
		case EAST:
			this.sprite.setTexture(spriteImages.get(6));
			this.stepIndex = 6;
			break;
		case NORTH:
			this.sprite.setTexture(spriteImages.get(0));
			this.stepIndex = 0;
			break;
		case SOUTH:
			this.sprite.setTexture(spriteImages.get(2));
			this.stepIndex = 2;
			break;
		}
	}
	
	/*
	 * Change direction to face the given location
	 */
	public void face(MapLocation l){
		if (l.getLocX() < this.getLocation().getLocX()){
			this.direction = Direction.EAST;
		}
		if (l.getLocX() > this.getLocation().getLocX()){
			this.direction = Direction.WEST;
		}
		if (l.getLocY() < this.getLocation().getLocY()){
			this.direction = Direction.NORTH;
		}
		if (l.getLocY() > this.getLocation().getLocY()){
			this.direction = Direction.SOUTH;
		}
		updateDirection();
	}

	public boolean isNPC() {
		return isNPC;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	/*
	 * 
	 *def takeStep(self):
        self.imgIdx = self.imgIdx + const.walkingList[self.stepIdx]
        self.stepIdx = ( self.stepIdx + 1 ) % 4
        self.image = self.images[self.imgIdx]
	 */
	
	public MapLocation getRandomLocation(){
		MapLocation newL = null;
		int moveOrNot = RandomUtility.randRange(0, 100);
		if (moveOrNot == 5){
			int moveDirection = Constants.MOVE_KEYS.get(RandomUtility.randRange(0, 4));
			newL = getNewLocation(moveDirection, this.getLocation());
		}
		return newL;
	}
	
	public void enqueueMove(MapLocation newL){
		moveQueue.add(newL);
	}
	
	public int getWalkCounter(){
		return walkCounter;
	}
	
	@Override
	public void setLocation(MapLocation loc){
		this.mapLocation = loc;
		this.screenLocation = MathUtility.mapLocationToScreenLocation(loc);
	}
	
	public String getDefaultMessage(){
		return script.getDefaultMessage();
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrentMP() {
		return currentMP;
	}

	public void setCurrentMP(int currentMP) {
		this.currentMP = currentMP;
	}

	public int getMaxMP() {
		return maxMP;
	}

	public void setMaxMP(int maxMP) {
		this.maxMP = maxMP;
	}
	
	public void decrementHP(int amount){
		if (currentHP - amount > 0){
			currentHP -= amount;
		}
	}
	
	public void incrementHP(int amount){
		if (currentHP + amount < maxHP){
			currentHP += amount;
		}else{
			currentHP = maxHP;
		}
	}
	
	public void decrementMP(int amount){
		if (currentMP - amount > 0){
			currentMP -= amount;
		}
	}
	
	public void incrementMP(int amount){
		if (currentMP + amount < maxMP){
			currentMP += amount;
		}else{
			currentMP = maxMP;
		}
	}
}
