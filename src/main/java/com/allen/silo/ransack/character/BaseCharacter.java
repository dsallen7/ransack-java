package com.allen.silo.ransack.character;

import java.util.ArrayList;
import java.util.List;

import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.ui.element.Image;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.FileUtility;
import com.allen.silo.ransack.utils.ImageUtility;

public abstract class BaseCharacter {
		
	protected Location loc;
	protected Sprite sprite;
	protected CollisionPoint point;
	protected String name;
	protected Direction direction;
	
	List<Texture> spriteImages;
	
	Texture currentSpriteImage;
	
	BaseCharacter(){}
	
	BaseCharacter(Location l, String imageFileName, BasicMap m, String name){
		this.name = name;
		point = new CollisionPoint(l.getLocX()*Constants.BLOCKSIZE, l.getLocY()*Constants.BLOCKSIZE);
		//Texture spriteSheet = new Texture(FileUtility.getCharSheet(imageFileName));
		Pixmap spriteSheet = new Pixmap(FileUtility.getCharSheet(imageFileName));
		spriteImages = ImageUtility.loadSpriteImages(spriteSheet);
		currentSpriteImage = spriteImages.get(2);
		
		//currentSpriteImage.draw(spriteSheet, 0, 0);
		direction = Direction.SOUTH;
		setSprite(new Sprite(currentSpriteImage));
		setLocation(l);
		m.setGameTileOccupied(l, name);
	}
	
	public Location getNewLocation(int key, Location oldL){
		int newX = oldL.getLocX(), newY = oldL.getLocY();
		switch(key){
		case Input.Keys.LEFT:
			this.direction = Direction.EAST;
			newX = oldL.getLocX()-1;
			break;
		case Input.Keys.RIGHT:
			this.direction = Direction.WEST;
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
		return new Location(newX, newY);
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public void setLocation(Location loc){
		this.loc = loc;
	}
	
	/*
	 * The X (horizontal) location of the character in terms of the map
	 */
	public int getX() {
		return loc.getLocX();
	}

	/*
	 * The Y (vertical) location of the character in terms of the map
	 */
	public int getY() {
		return loc.getLocY();
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
	
	public List<Location> getCardinals(){
		List<Location> cardinals = new ArrayList<Location>();
		cardinals.add(new Location(this.getX(), this.getY()-1));
		cardinals.add(new Location(this.getX(), this.getY()+1));
		cardinals.add(new Location(this.getX()-1, this.getY()));
		cardinals.add(new Location(this.getX()+1, this.getY()));
		return cardinals;
	}
}
