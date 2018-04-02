package com.allen.silo.ransack.handlers;

import org.mini2Dx.minibus.MessageData;

import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;

public class EventMessageDataImpl implements MessageData {
	
	private final int value;
	private String data;
	private String dialogueFrom;
	private String dialogueTo;
	private String mapFrom;
	private String mapTo;
	private MapLocation newL;
	private Direction dir;
	
	/*
	 * Dialogue
	 */
	public EventMessageDataImpl(int value, String dialogueFrom, String dialogueTo, String data){
		this.value = value;
		this.dialogueFrom = dialogueFrom;
		this.dialogueTo = dialogueTo;
		this.data = data;
	}
	/*
	 * Stairs up/down
	 */
	public EventMessageDataImpl(int value, String mapFrom, String mapTo){
		this.value = value;
		this.setMapFrom(mapFrom);
		this.setMapTo(mapTo);
	}
	/*
	 * Portal
	 */
	public EventMessageDataImpl(int value, String mapFrom, String mapTo, MapLocation newL){
		this.value = value;
		this.setMapFrom(mapFrom);
		this.setMapTo(mapTo);
		this.setNewL(newL);
	}
	/*
	 * Intermap i.e. crossing border of one map into another
	 */
	public EventMessageDataImpl(int value, String mapFrom, String mapTo, Direction dir){
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
