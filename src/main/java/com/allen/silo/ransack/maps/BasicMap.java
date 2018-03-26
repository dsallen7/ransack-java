package com.allen.silo.ransack.maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.tiled.TiledMap;
import org.mini2Dx.tiled.exception.TiledException;
import org.mini2Dx.tiled.Tile;

import com.allen.silo.ransack.character.attributes.Location;
import com.badlogic.gdx.files.FileHandle;

public class BasicMap extends TiledMap implements Serializable {
	
	private static final long serialVersionUID = -9197081825338329152L;

	public static Logger logger = Logger.getLogger(BasicMap.class.getName());
	
	protected Grid grid;
	
	protected TiledMap tiledMap;
		
	public BasicMap(FileHandle fileHandle) throws IOException, TiledException{
		super(fileHandle);
		grid = new Grid(this.getWidth(), this.getHeight());
		/*
		try{
			File f = new File(mapName);
			List<String> mapLines = readLines(f);
			initGrid(mapLines.size(), mapLines.get(0).length());
			for (int i = 0; i < mapLines.size(); i++){
				for (int j = 0; j < mapLines.get(i).length(); j++){
					setTileBase(j, i, mapLines.get(i).charAt(j));
				}
			}
		}catch (Exception e){
			logger.log(Level.SEVERE, "Exception in BasicMap: ", e);
		}*/
	}

	public static List<String> readLines(File file){
		BufferedReader reader;
		List<String> results = new ArrayList<String>();
		try {
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				results.add(line);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "FileNotFoundException in BasicMap: ", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "IOException in BasicMap: ", e);
		}
		return results;
	}
	
	public int dimensionX(){
		return grid.dimensionX();
	}
	
	public int dimensionY(){
		return grid.dimensionY();
	}
	
	public void setTileBase(int x, int y, char value){
		grid.setTileBase(x, y, value);
	}
	
	public int getTileId(int x, int y){
		return getTile(x, y, 0).getTileId();
		//return grid.getTileBase(x, y);
	}
	public Tile getTile(int x, int y){
		return getTile(x, y, 0);
		//return grid.getTileBase(x, y);
	}
	
	public boolean isTileProperty(Location l, String property){
		//logger.log(Level.INFO, "tileProperty: " + getTile(l.getLocX(), l.getLocY()).getProperty(property));
		return getTile(l.getLocX(), l.getLocY()).getProperty(property).equals("true");
	}
	
	public boolean hasTileProperty(Location l, String property){
		return getTile(l.getLocX(), l.getLocY()).containsProperty(property);
	}
	
	public String isTileOccupied(Location l){
		return grid.getGameTileOccupied(l);
	}
	
	public void clearTileProperty(Location l){
		grid.clearGameTileOccupied(l);
	}
	
	public void setTileProperty(Location l, String property){
		getTile(l.getLocX(), l.getLocY()).setProperty(property, "true");
	}
	
	public void setGameTileOccupied(Location l, String occupier){
		grid.setGameTileOccupied(l, occupier);
	}
	
	public boolean isMovable(Location l){
		boolean isEdge = !(l.getLocX() < 0 || l.getLocY() < 0 || l.getLocX() > this.getWidth()-1 || l.getLocY() > this.getHeight()-1 );
		return isEdge;
	}
	
}
