package com.allen.silo.ransack.character;

import java.util.logging.Logger;

import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;

public class Player extends PlayableCharacter {
	public static Logger logger = Logger.getLogger(Player.class.getName());
		
	public Player(Location l, BasicMap m){
		super(l, "mherosheet.bmp", false, m, "Player");
	}
	
	public void enqueueMove(int key, PlayableMap currentMap){
		Location newL = getNewLocation(key, this.getLocation());
		super.enqueueMove(newL, currentMap);
	}
	
	public void move(PlayableMap currentMap){
		super.move(currentMap);
	}
}
