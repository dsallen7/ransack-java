package com.allen.silo.ransack.maps;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import org.mini2Dx.tiled.Layer;
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
	
	public BasicMap(){
		super(FileUtility.loadMap("prototype"));
	}

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

	public Tile getTile(int x, int y, int layer){
		Tile tile = super.getTile(x, y, layer);
		if (tile == null){
			tile = new Tile();
		}
		return tile;
	}
	
	public boolean isTileProperty(Location l, String property){
		boolean isTileProperty = getTile(l.getLocX(), l.getLocY(), 0).getProperty(property).equals("true") || 
				getTile(l.getLocX(), l.getLocY(), 1).getProperty(property).equals("true");
		return isTileProperty;
	}
	
	public boolean hasTileProperty(Location l, String property){
		boolean hasTileProperty = getTile(l.getLocX(), l.getLocY(), 0).containsProperty(property) || 
				getTile(l.getLocX(), l.getLocY(), 1).containsProperty(property);
		for (int i = 0; i < this.getLayers().size(); i++){
			hasTileProperty = hasTileProperty || getTile(l.getLocX(), l.getLocY(), i).containsProperty(property);
		}
		return hasTileProperty;
	}
	
	public String isTileOccupied(Location l){
		return grid.getCellOccupied(l);
	}
	
	public void clearCellOccupied(Location l){
		grid.clearCellOccupied(l);
	}
	
	public void setTileProperty(Location l, int layer, String property){
		getTile(l.getLocX(), l.getLocY(), layer).setProperty(property, "true");
	}
	
	public void setCellOccupied(Location l, String occupier){
		grid.setCellOccupied(l, occupier);
	}
	
	public boolean isMovable(Location l){
		boolean isEdge = !(isLeftEdge(l) || isTopEdge(l) || isRightEdge(l) || isBottomEdge(l) );
		return isEdge;
	}
	
	public boolean isTopEdge(Location l){
		return l.getLocY() < 0;
	}
	
	public boolean isBottomEdge(Location l){
		return l.getLocY() > getHeight()-1;
	}
	
	public boolean isLeftEdge(Location l){
		return l.getLocX() < 0;
	}
	
	public boolean isRightEdge(Location l){
		return l.getLocX() > getWidth()-1;
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
