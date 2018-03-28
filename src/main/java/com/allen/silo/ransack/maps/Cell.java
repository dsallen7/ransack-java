package com.allen.silo.ransack.maps;

public class Cell {
	
	private int base;
	private String occupied = null;	
	private String targetMap;
	private int targetX;
	private int targetY;
	
	public Cell(){}
	
	public Cell(int value){
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

	public String getTargetMap() {
		return targetMap;
	}

	public void setTargetMap(String targetMap) {
		this.targetMap = targetMap;
	}

	public int getTargetX() {
		return targetX;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	public int getTargetY() {
		return targetY;
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}
}
