package com.allen.silo.ransack.character.attributes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Script {
	private String characterName;
	private String fileName;
	private String defaultMessage;
	
	public String getCharacterName() {
		return characterName;
	}

	@XmlElement
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
	public String getFileName() {
		return fileName;
	}

	@XmlElement
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	@XmlElement
	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}
}
