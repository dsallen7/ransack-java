package com.allen.silo.ransack.handlers;

import org.mini2Dx.minibus.MessageData;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.Location;

public class MessageDataImpl implements MessageData {
	
	private final int value;
	private String data;
	private String mapFrom;
	private String mapTo;
	private Location newL;
	private Direction dir;
	
	public MessageDataImpl(int value, String data){
		this.value = value;
		this.data = data;
	}
	/*
	 * Stairs up/down
	 */
	public MessageDataImpl(int value, String mapFrom, String mapTo){
		this.value = value;
		this.setMapFrom(mapFrom);
		this.setMapTo(mapTo);
	}
	/*
	 * Portal
	 */
	public MessageDataImpl(int value, String mapFrom, String mapTo, Location newL){
		this.value = value;
		this.setMapFrom(mapFrom);
		this.setMapTo(mapTo);
		this.setNewL(newL);
	}
	/*
	 * Intermap i.e. crossing border of one map into another
	 */
	public MessageDataImpl(int value, String mapFrom, String mapTo, Direction dir){
		this.value = value;
		this.setMapFrom(mapFrom);
		this.setMapTo(mapTo);
		this.setDir(dir);
	}

	public int getValue() {
		return value;
	}
	
	public String getData(){
		return this.data;
	}

	public String getMapFrom() {
		return mapFrom;
	}

	public void setMapFrom(String mapFrom) {
		this.mapFrom = mapFrom;
	}

	public String getMapTo() {
		return mapTo;
	}

	public void setMapTo(String mapTo) {
		this.mapTo = mapTo;
	}

	public Location getNewL() {
		return newL;
	}

	public void setNewL(Location newL) {
		this.newL = newL;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}

}
