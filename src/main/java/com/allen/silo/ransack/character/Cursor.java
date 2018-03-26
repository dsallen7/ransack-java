package com.allen.silo.ransack.character;

import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.EditorMap;

public class Cursor extends BaseCharacter {
	
	public Cursor(Location l, String fileName, BasicMap m){
		super(l, fileName, m, "cursor");
	}
	
	public void move(int key, EditorMap currentMap){
		
		Location newL = getNewLocation(key, this.getLocation());
		
		if (currentMap.isMovable(newL)){
			this.setLocation(newL);
		}
	}

}
