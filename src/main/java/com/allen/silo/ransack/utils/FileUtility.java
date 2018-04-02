package com.allen.silo.ransack.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.mini2Dx.ui.element.Image;

import com.allen.silo.ransack.character.attributes.Script;
import com.badlogic.gdx.files.FileHandle;

public class FileUtility {
	public static Logger logger = Logger.getLogger(FileUtility.class.getName());
	
	public static Script loadCharacterScript(String fileName){
		FileHandle file = getAsset(Constants.SCRIPTS_PATH+fileName);
		JAXBContext jaxbContext;
		Script script = null;
		try {
			jaxbContext = JAXBContext.newInstance(Script.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			script = (Script) jaxbUnmarshaller.unmarshal(file.file());
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, null, e);
		}
		return script;
	}
	
	public static List<Image> loadCharacterSheet(String filename){
		return new ArrayList<Image>();
	}
	
	public static List<String> getMapFileNames(){
		List<String> mapFileNameList = new ArrayList<String>();
		
		File folder = new File(Constants.ABSOLUTE_ASSETS_PATH+Constants.MAPS_PATH);
		File[] listOfFiles = folder.listFiles();
		
		for (File f : listOfFiles){
			if (f.getName().endsWith(".tmx"))
				mapFileNameList.add(f.getName().substring(0, f.getName().length()-4));
		}		
		return mapFileNameList;
	}
	
	
	public static FileHandle loadMap(String fileName){
		return getAsset(Constants.MAPS_PATH+fileName+".tmx");
	}
	
	public static FileHandle loadUIImageFile(String fileName){
		return getAsset(Constants.UI_TEXTURE_PATH+fileName);
	}
	
	public static FileHandle loadFont(String fileName){
		return getAsset(Constants.FONTS_PATH+fileName+".tmx");
	}
	
	public static FileHandle getCharSheet(String fileName){
		return getAsset(Constants.CHARSHEET_PATH+fileName);
	}
	
	public static FileHandle getUIAsset(String fileName){
		return getAsset(Constants.UI_PATH+fileName);
	}
	
	public static FileHandle getAsset(String fileName){
		return new FileHandle(Constants.ABSOLUTE_ASSETS_PATH+fileName);
	}

}
