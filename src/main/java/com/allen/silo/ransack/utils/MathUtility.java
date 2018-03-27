package com.allen.silo.ransack.utils;

import com.allen.silo.ransack.character.attributes.Location;

public class MathUtility {
	
	public static Location getGridCoordFromScreen(float x, float y){
		return new Location((int)Math.floor(x)/Constants.BLOCKSIZE,(int)Math.floor(y)/Constants.BLOCKSIZE);
	}
}
