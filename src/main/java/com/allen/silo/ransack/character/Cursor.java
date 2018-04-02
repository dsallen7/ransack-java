package com.allen.silo.ransack.character;

import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.EditorMap;

public class Cursor extends BaseCharacter {
	
	public Cursor(MapLocation l, BasicMap m){
		super(l, m);
	}
	
	public void move(int key, EditorMap currentMap){
		
		MapLocation newL = getNewLocation(key, this.getLocation());
		
		if (currentMap.isMovable(newL)){
			this.setLocation(newL);
		}
	}

}
