package com.allen.silo.ransack.character;

import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;

public class NonPlayerCharacter extends PlayableCharacter {
		
	public NonPlayerCharacter(Location l, String name, BasicMap m){
		super(l, name, true, m);
	}
	
	public void move(){
		super.move();
	}
}
