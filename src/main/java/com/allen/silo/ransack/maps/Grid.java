package com.allen.silo.ransack.maps;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.allen.silo.ransack.character.attributes.Location;

public class Grid {
	public static Logger logger = Logger.getLogger(Grid.class.getName());
	
	private Cell[][] grid;
	
	public Grid(){}
	
	public Grid(int x, int y){
		initGridDimensions(x, y);

		for (int i = 0;i<x;i++){
			for (int j = 0;j<y;j++){
				grid[i][j] = new Cell();
			}
		}
	}
	
	public Cell getCell(Location l){
		return grid[l.getLocX()][l.getLocY()];
	}
	
	public void setCellOccupied(Location l, String occupier){
		grid[l.getLocX()][l.getLocY()].setOccupied(occupier);
	}
	
	public String getCellOccupied(Location l){
		return grid[l.getLocX()][l.getLocY()].getOccupied();
	}
	
	public void clearCellOccupied(Location l){
		grid[l.getLocX()][l.getLocY()].setOccupied(null);
	}

	public int getTileBase(int x, int y){
		return grid[x][y].getBase();
	}

	public void initGridDimensions(int x, int y){
		this.grid = new Cell[x][y];
	}

	public void setTileBase(int x, int y, char value){
		if (grid[x][y] == null){
			grid[x][y] = new Cell(value);
		}else{
			grid[x][y].setBase(value);
		}
	}
	
	public int dimensionX(){
		int len = 0;
		if (grid != null)
			len =  grid.length;
		return len;
	}
	
	public int dimensionY(){
		int len = 0;
		if (grid != null && grid[0] != null)
			len = grid[0].length;
		return len;
	}
}
