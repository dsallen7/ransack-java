package com.allen.silo.ransack.character;

import java.util.logging.Logger;

import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.main.accessories.Clock;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.FileUtility;

public class Player extends PlayableCharacter {
	public static Logger logger = Logger.getLogger(Player.class.getName());
	
	private Clock clock;
		
	public Player(MapLocation l, BasicMap m){
		super(l, FileUtility.loadCharacterScript("player.xml"), false, m);
		this.currentHP = 45;
		this.maxHP = 50;
		this.currentMP = 50;
		this.maxMP = 50;
		this.clock = new Clock();
	}
	
	public void enqueueMove(int key, PlayableMap currentMap){
		MapLocation newL = getNewLocation(key, this.getLocation());
		super.enqueueMove(newL);
	}
	
	public void move(boolean intramap){
		super.move(intramap);
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
	
	public Clock getClock(){
		return this.clock;
	}
}
