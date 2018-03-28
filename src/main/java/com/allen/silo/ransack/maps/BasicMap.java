package com.allen.silo.ransack.maps;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import org.mini2Dx.tiled.Tile;
import org.mini2Dx.tiled.TiledMap;
import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.utils.FileUtility;

public class BasicMap extends TiledMap implements Serializable {
	
	private static final long serialVersionUID = -9197081825338329152L;

	public static Logger logger = Logger.getLogger(BasicMap.class.getName());
	
	protected Grid grid;
	protected int offsetX;
	protected int offsetY;
	private String name;

	public BasicMap(String fileName) throws IOException, TiledException{
		super(FileUtility.loadMap(fileName));
		grid = new Grid(getWidth(), getHeight());
		this.name = fileName;
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

	public Tile getTile(int x, int y){
		return getTile(x, y, 0);
	}
	
	public boolean isTileProperty(Location l, String property){
		//logger.log(Level.INFO, "tileProperty: " + getTile(l.getLocX(), l.getLocY()).getProperty(property));
		return getTile(l.getLocX(), l.getLocY()).getProperty(property).equals("true");
	}
	
	public boolean hasTileProperty(Location l, String property){
		return getTile(l.getLocX(), l.getLocY()).containsProperty(property);
	}
	
	public String isTileOccupied(Location l){
		return grid.getCellOccupied(l);
	}
	
	public void clearCellOccupied(Location l){
		grid.clearCellOccupied(l);
	}
	
	public void setTileProperty(Location l, String property){
		getTile(l.getLocX(), l.getLocY()).setProperty(property, "true");
	}
	
	public void setCellOccupied(Location l, String occupier){
		grid.setCellOccupied(l, occupier);
	}
	
	public boolean isMovable(Location l){
		boolean isEdge = !(l.getLocX() < 0 || l.getLocY() < 0 || l.getLocX() > getWidth()-1 || l.getLocY() > getHeight()-1 );
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
