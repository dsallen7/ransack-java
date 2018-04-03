package com.allen.silo.ransack.world;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.di.annotation.Autowired;
import org.mini2Dx.tiled.TiledMap;
import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.display.RansackAssetManager;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.EditorMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.FileUtility;
import com.badlogic.gdx.utils.SerializationException;

public class World {
	
	@Autowired
	private RansackAssetManager assetManager;
	
	public static Logger logger = Logger.getLogger(World.class.getName());
	
	private PlayableMap currentMap;
	
	private HashMap<String, BasicMap> maps;
	
	public World() throws TiledException, IOException{
        assetManager = Mdx.di.getBean(RansackAssetManager.class);
		maps = new HashMap<String, BasicMap>();
		/*
		List<String> mapFileNameList = FileUtility.getMapFileNames();
		for (String fileName : mapFileNameList){
			PlayableMap newM = new PlayableMap(fileName);
			logger.log(Level.INFO, "fileName: " + fileName + " width: " + newM.getWidth() + " height: " + newM.getHeight());
			maps.put(fileName, newM);
			if(newM.containsProperty("initial"))
				this.currentMap = newM;
		}*/
		PlayableMap village1 = new PlayableMap("village1");
		maps.put("village1", village1);
		currentMap = village1;
		
	}
	
	public World(EditorMap em){
		
	}
	
	public BasicMap changeCurrentMap(String mapName){
		currentMap = (PlayableMap) maps.get(mapName);
		currentMap.resetOccupied();
		return currentMap;
	}
	
	public BasicMap getCurrentMap(){
		return currentMap;
	}
	
	public static void ascendStairs(){
		
	}
	
	public static void descendStaire(){
		
	}

	public MapLocation getIntermapLocation(String mapFrom, String mapTo, MapLocation location, Direction dir) {
		PlayableMap oldMap = (PlayableMap) maps.get(mapFrom);
		PlayableMap newMap = (PlayableMap) maps.get(oldMap.getProperty(dir.toString().toLowerCase()));
		if (newMap == null)
			return null;
		MapLocation newL = null;
		switch(dir){
		case NORTH:
			newL = new MapLocation(location.getLocX(), newMap.dimensionY()-1);
			break;
		case SOUTH:
			newL = new MapLocation(location.getLocX(), 0);
			break;
		case EAST:
			newL = new MapLocation(0, location.getLocY());
			break;
		case WEST:
			newL = new MapLocation(newMap.dimensionX()-1, location.getLocY());
			break;
		}
		if(newMap.isCellBlocker(newL))
			return null;
		return newL;
	}
}
