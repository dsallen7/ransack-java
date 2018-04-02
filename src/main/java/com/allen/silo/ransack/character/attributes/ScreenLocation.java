package com.allen.silo.ransack.character.attributes;

public class ScreenLocation {
	private float locX;
	private float locY;
	
	public ScreenLocation(){}
	
	public ScreenLocation(float x, float y){
		this.setLocX(x);
		this.setLocY(y);
	}
	
	public float getLocX() {
		return locX;
	}
	public void setLocX(float locX) {
		this.locX = locX;
	}
	public float getLocY() {
		return locY;
	}
	public void setLocY(float locY) {
		this.locY = locY;
	}
	
	public boolean equals(ScreenLocation l){
		return l.getLocX() == this.getLocX() && l.getLocY() == this.getLocY();
	}
}
