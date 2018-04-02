package com.allen.silo.ransack.character;

import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.character.attributes.Script;
import com.allen.silo.ransack.maps.BasicMap;

public class NonPlayerCharacter extends PlayableCharacter {
		
	public NonPlayerCharacter(MapLocation l, Script script, BasicMap m){
		super(l, script, true, m);
	}
	
	public void move(boolean intramap){
		super.move(intramap);
	}
}
