package com.allen.silo.ransack.handlers;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;

public class Event {
	
	private EventType eventType;
	private String data;
	private String dialogueFrom;
	private String dialogueTo;
	private String mapFrom;
	private String mapTo;
	private MapLocation newL;
	private Direction dir;
	
	/*
	 * Single-arg (close dialogue, ...)
	 */
	public Event(String type){
		this.eventType = EventType.valueOf(type);
	}
	
	/*
	 * Open Dialogue
	 */	
	public Event(String type, String dialogueFrom, String dialogueTo, String data){
		this.eventType = EventType.valueOf(type);
		this.dialogueFrom = dialogueFrom;
		this.dialogueTo = dialogueTo;
		this.data = data;
	}
	
	/*
	 * Stairs up/dwn
	 */
	public Event(String type, String mapFrom, String mapTo){
		this.eventType = EventType.valueOf(type);
		this.mapFrom = mapFrom;
		this.mapTo = mapTo;
	}
	/*
	 * Portal
	 */
	public Event(String type, String mapFrom, String mapTo, MapLocation newL){
		this.eventType = EventType.valueOf(type);
		this.mapFrom = mapFrom;
		this.mapTo = mapTo;
		this.newL = newL;
	}
	/*
	 * Intermap i.e. crossing border of one map into another
	 */
	public Event(String type, String mapFrom, String mapTo, Direction dir){
		this.eventType = EventType.valueOf(type);
		this.setMapFrom(mapFrom);
		this.setMapTo(mapTo);
		this.setDir(dir);
	}
	
	public EventType getEventType(){
		return eventType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public MapLocation getNewL() {
		return newL;
	}

	public void setNewL(MapLocation newL) {
		this.newL = newL;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public String getDialogueFrom() {
		return dialogueFrom;
	}

	public void setDialogueFrom(String dialogueFrom) {
		this.dialogueFrom = dialogueFrom;
	}

	public String getDialogueTo() {
		return dialogueTo;
	}

	public void setDialogueTo(String dialogueTo) {
		this.dialogueTo = dialogueTo;
	}
}
