package com.allen.silo.ransack.character;

import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;

public class NonPlayerCharacter extends PlayableCharacter {
		
	public NonPlayerCharacter(Location l, String fileName, BasicMap m, String name){
		super(l, fileName, true, m, name);
	}
	
	public void move(PlayableMap currentMap){
		super.move(currentMap);
	}
}
