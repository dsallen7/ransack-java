package com.allen.silo.ransack.character;

import java.util.logging.Logger;

import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;

public class Player extends PlayableCharacter {
	public static Logger logger = Logger.getLogger(Player.class.getName());
		
	public Player(Location l, BasicMap m){
		super(l, "player",false, m);
	}
	
	public void enqueueMove(int key, PlayableMap currentMap){
		Location newL = getNewLocation(key, this.getLocation());
		super.enqueueMove(newL, currentMap);
	}
	
	public void move(PlayableMap currentMap){
		super.move(currentMap);
	}
	
	public int getStepIndex(){
		return this.stepIndex;
	}
	
	public boolean isOutsideMargins(BasicMap m){
		return (isOutsideTopOrBottomMargins(m) || isOutsideLeftOrRightMargins(m));
	}
	
	public boolean isOutsideTopOrBottomMargins(BasicMap m){
		return (isOutsideTopMargin(m) || isOutsideBottomMargin(m));
	}
	
	public boolean isOutsideLeftOrRightMargins(BasicMap m){
		return (isOutsideLeftMargin(m) || isOutsideRightMargin(m));
	}
	
	public boolean isOutsideTopMargin(BasicMap m) {
		return this.getY() < 5;
	}
	public boolean isOutsideBottomMargin(BasicMap m) {
		return this.getY() > m.dimensionY()-5;
	}
	public boolean isOutsideLeftMargin(BasicMap m) {
		return this.getX() < 5;
	}
	public boolean isOutsideRightMargin(BasicMap m) {
		return this.getX() > m.dimensionX()-5;
	}
}
