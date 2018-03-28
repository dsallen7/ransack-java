package com.allen.silo.ransack.character.attributes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Script {
	private String characterName;
	private String fileName;
	
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
}
