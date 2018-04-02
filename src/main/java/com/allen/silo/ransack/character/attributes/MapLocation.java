package com.allen.silo.ransack.character.attributes;

public class MapLocation {
	
	private int locX;
	private int locY;
	
	public MapLocation(){}
	
	public MapLocation(int x, int y){
		this.setLocX(x);
		this.setLocY(y);
	}
	
	public MapLocation(float x, float y){
		this.setLocX((int)x);
		this.setLocY((int)y);
	}
	
	public int getLocX() {
		return locX;
	}
	public void setLocX(int locX) {
		this.locX = locX;
	}
	public int getLocY() {
		return locY;
	}
	public void setLocY(int locY) {
		this.locY = locY;
	}
	
	public boolean equals(MapLocation l){
		return l.getLocX() == this.getLocX() && l.getLocY() == this.getLocY();
	}

}
