package com.allen.silo.ransack.maps;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.attributes.Location;
import com.badlogic.gdx.files.FileHandle;

public class EditorMap extends BasicMap {
	public static Logger logger = Logger.getLogger(EditorMap.class.getName());
	
	public EditorMap(FileHandle fileHandle) throws IOException, TiledException{
		super(fileHandle);
		this.grid = new Grid(10, 10);
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				setTileBase(j, i, ' ');
			}
		}
	}
	
	public boolean isMovable(Location l){
		return super.isMovable(l);
	}

}
