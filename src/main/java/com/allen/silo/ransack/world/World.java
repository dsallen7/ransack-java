package com.allen.silo.ransack.world;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.di.annotation.Autowired;
import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.FileUtility;

public class World {
	public static Logger logger = Logger.getLogger(World.class.getName());
	
	private PlayableMap currentMap;
	
	private HashMap<String, BasicMap> maps;
	
	public World() throws TiledException, IOException{
		maps = new HashMap<String, BasicMap>();
		List<String> mapFileNameList = FileUtility.getMapFileNames();
		for (String fileName : mapFileNameList){
			PlayableMap newM = new PlayableMap(fileName);
			logger.log(Level.INFO, "fileName: " + fileName + " width: " + newM.getWidth() + " height: " + newM.getHeight());
			maps.put(fileName, newM);
			if(newM.containsProperty("initial"))
				this.currentMap = newM;
		}
	}
	
	public BasicMap changeCurrentMap(String mapName){
		currentMap = (PlayableMap) maps.get(mapName);
		return currentMap;
	}
	
	public BasicMap getCurrentMap(){
		return currentMap;
	}
	
	public static void ascendStairs(){
		
	}
	
	public static void descendStaire(){
		
	}
}
