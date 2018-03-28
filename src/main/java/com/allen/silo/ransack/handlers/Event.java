package com.allen.silo.ransack.handlers;

import com.allen.silo.ransack.character.attributes.Location;

public class Event {
	
	private EventType eventType;
	private String data;
	private String mapFrom;
	private String mapTo;
	private Location newL;
	
	public Event(String type, String data){
		this.eventType = EventType.valueOf(type);
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
	public Event(String type, String mapFrom, String mapTo, Location newL){
		this.eventType = EventType.valueOf(type);
		this.mapFrom = mapFrom;
		this.mapTo = mapTo;
		this.newL = newL;
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

	public Location getNewL() {
		return newL;
	}

	public void setNewL(Location newL) {
		this.newL = newL;
	}
}
