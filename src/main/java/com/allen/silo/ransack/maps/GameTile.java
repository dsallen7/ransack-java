package com.allen.silo.ransack.maps;

public class GameTile {
	
	private int base;
	private String occupied = null;	
	
	public GameTile(){}
	
	public GameTile(int value){
		this.base = value;
	}
	
	public void setBase(int value){
		this.base = value;
	}

	public int getBase() {
		return base;
	}
	
	public String getOccupied(){
		return this.occupied;
	}
	
	public void setOccupied(String occupier){
		this.occupied = occupier;
	}
}
