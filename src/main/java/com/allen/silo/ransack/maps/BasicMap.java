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
	protected int offsetX;
	protected int offsetY;
		
	public BasicMap(FileHandle fileHandle) throws IOException, TiledException{
		super(fileHandle);
		grid = new Grid(this.getWidth(), this.getHeight());
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

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
}
