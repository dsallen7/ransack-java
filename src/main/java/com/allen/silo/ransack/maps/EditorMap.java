package com.allen.silo.ransack.maps;

import java.io.IOException;
import java.util.logging.Logger;

import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.attributes.MapLocation;

public class EditorMap extends BasicMap {

	private static final long serialVersionUID = -5489692366157952670L;
	public static Logger logger = Logger.getLogger(EditorMap.class.getName());
	
	public EditorMap(String fileName) throws IOException, TiledException{
		super();
		this.grid = new Grid(10, 10);
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				setTileBase(j, i, ' ');
			}
		}
	}
	
	public boolean isMovable(MapLocation l){
		return super.isMovable(l);
	}

}
