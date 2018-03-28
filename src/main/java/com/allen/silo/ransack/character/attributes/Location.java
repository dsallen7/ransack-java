package com.allen.silo.ransack.character.attributes;

public class Location {
	
	private int locX;
	private int locY;
	
	public Location(){}
	
	public Location(int x, int y){
		this.setLocX(x);
		this.setLocY(y);
	}
	
	public Location(float x, float y){
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

}
