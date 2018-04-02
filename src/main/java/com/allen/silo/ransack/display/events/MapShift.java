package com.allen.silo.ransack.display.events;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;

public class MapShift {
	
	// Top corner of old map window
	private MapLocation from;
	// Top corner of new map window
	private MapLocation to;
	private Direction direction;
	private int counter;
	
	public MapShift(MapLocation from, MapLocation to, Direction direction){
		this.from = from;
		this.to = to;
		this.direction = direction;
		this.counter = 30;
	}
	
	
	public MapLocation getFrom() {
		return from;
	}
	public void setFrom(MapLocation from) {
		this.from = from;
	}
	public MapLocation getTo() {
		return to;
	}
	public void setTo(MapLocation to) {
		this.to = to;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public void incrementCounter(){
		counter++;
	}
	public void decrementCounter(){
		counter--;
	}
}
