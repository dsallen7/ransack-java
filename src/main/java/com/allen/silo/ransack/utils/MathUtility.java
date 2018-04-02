package com.allen.silo.ransack.utils;

import java.util.ArrayList;
import java.util.List;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.character.attributes.ScreenLocation;

public class MathUtility {
	
	public static MapLocation getMapLocationFromScreenCoords(float x, float y){
		return new MapLocation((int)Math.floor(x)/Constants.BLOCKSIZE,(int)Math.floor(y)/Constants.BLOCKSIZE);
	}
	
	public static ScreenLocation getScreenLocationFromGridCoords(int x, int y){
		return new ScreenLocation( x*Constants.BLOCKSIZE, y*Constants.BLOCKSIZE);
	}
	
	public static ScreenLocation mapLocationToScreenLocation(MapLocation ml){
		return getScreenLocationFromGridCoords(ml.getLocX(), ml.getLocY());
	}
	
	public static MapLocation screenLocationToMapLocation(ScreenLocation sl){
		return getMapLocationFromScreenCoords(sl.getLocX(), sl.getLocY());
	}
	
	public static List<ScreenLocation> getInterpolatedLocations(MapLocation oldL, MapLocation newL, Direction dir){
		ScreenLocation oldScreenL = mapLocationToScreenLocation(oldL);
		ScreenLocation newScreenL = mapLocationToScreenLocation(newL);
		
		List<ScreenLocation> interList = new ArrayList<ScreenLocation>();
		
		switch(dir){
		case NORTH:
			for (int i = (int)oldScreenL.getLocY(); i >= (int)newScreenL.getLocY(); i = i - 3){
				interList.add(new ScreenLocation(oldScreenL.getLocX(), i));
			}
			break;
		case SOUTH:
			for (int i = (int)oldScreenL.getLocY(); i <= (int)newScreenL.getLocY(); i = i + 3){
				interList.add(new ScreenLocation(oldScreenL.getLocX(), i));
			}
			break;
		case EAST:
			for (int i = (int)oldScreenL.getLocX(); i <= (int)newScreenL.getLocX(); i = i + 3){
				interList.add(new ScreenLocation(i, oldScreenL.getLocY()));
			}
			break;
		case WEST:
			for (int i = (int)oldScreenL.getLocX(); i >= (int)newScreenL.getLocX(); i = i - 3){
				interList.add(new ScreenLocation(i, oldScreenL.getLocY()));
			}
			break;
		}
		
		return interList;
	}
}
