package com.allen.silo.ransack.character;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Sprite;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.utils.Constants;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public abstract class BaseCharacter {
	public static Logger logger = Logger.getLogger(BaseCharacter.class.getName());
		
	protected MapLocation mapLocation;
	protected Sprite sprite;
	protected CollisionPoint point;
	protected String name;
	protected Direction direction;
	protected static Queue<String> mailbox;
	
	List<Texture> spriteImages;
	Texture currentSpriteImage;
	
	static{
		mailbox = new LinkedList<String>();
	}
	
	BaseCharacter(MapLocation l, BasicMap m){
		
	}
	
	BaseCharacter(MapLocation l, String name, BasicMap m){
		point = new CollisionPoint(l.getLocX()*Constants.BLOCKSIZE, l.getLocY()*Constants.BLOCKSIZE);
		setLocation(l);
		m.setCellOccupied(l, name);
		this.name = name;
	}
	
	public MapLocation getNewLocation(int key, MapLocation oldL){
		int newX = oldL.getLocX(), newY = oldL.getLocY();
		switch(key){
		case Input.Keys.LEFT:
			this.direction = Direction.WEST;
			newX = oldL.getLocX()-1;
			break;
		case Input.Keys.RIGHT:
			this.direction = Direction.EAST;
			newX = oldL.getLocX()+1;
			break;
		case Input.Keys.UP:
			this.direction = Direction.NORTH;
			newY = oldL.getLocY()-1;
			break;
		case Input.Keys.DOWN:
			this.direction = Direction.SOUTH;
			newY = oldL.getLocY()+1;
			break;
		}
		return new MapLocation(newX, newY);
	}
	
	public String getName(){
		return this.name;
	}
	
	public MapLocation getLocation(){
		return mapLocation;
	}
	
	public void setLocation(MapLocation loc){
		this.mapLocation = loc;
	}
	
	/*
	 * The X (horizontal) location of the character in terms of the map
	 */
	public int getX() {
		return mapLocation.getLocX();
	}

	/*
	 * The Y (vertical) location of the character in terms of the map
	 */
	public int getY() {
		return mapLocation.getLocY();
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public CollisionPoint getPoint() {
		return point;
	}

	public void setPoint(CollisionPoint point) {
		this.point = point;
	}
	
	public List<MapLocation> getCardinals(){
		List<MapLocation> cardinals = new ArrayList<MapLocation>();
		cardinals.add(new MapLocation(this.getX(), this.getY()-1));
		cardinals.add(new MapLocation(this.getX(), this.getY()+1));
		cardinals.add(new MapLocation(this.getX()-1, this.getY()));
		cardinals.add(new MapLocation(this.getX()+1, this.getY()));
		return cardinals;
	}
}
