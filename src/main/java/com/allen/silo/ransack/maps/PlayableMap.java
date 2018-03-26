package com.allen.silo.ransack.maps;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.attributes.Location;
import com.badlogic.gdx.files.FileHandle;

public class PlayableMap extends BasicMap {
	public static Logger logger = Logger.getLogger(PlayableMap.class.getName());
		
	@Override
	public boolean isMovable(Location l){
		boolean isOnMap = super.isMovable(l);
		if (!isOnMap){
			return isOnMap;
		}
		//logger.log(Level.INFO, "x="+l.getLocX()+"y="+l.getLocY()+" isOccupied: " +isTileProperty(l, "isOccupied"));
		boolean isMovable = isOnMap && isTileProperty(l, "isFloor");
		return isMovable;
	}
	
	public PlayableMap(FileHandle fileHandle) throws IOException, TiledException{
		super(fileHandle);
	}

}
