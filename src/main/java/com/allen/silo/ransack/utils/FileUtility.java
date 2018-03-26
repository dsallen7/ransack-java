package com.allen.silo.ransack.utils;

import java.util.ArrayList;
import java.util.List;

import org.mini2Dx.ui.element.Image;

import com.badlogic.gdx.files.FileHandle;

public class FileUtility {
	
	public static List<Image> loadCharacterSheer(String filename){
		return new ArrayList<Image>();
	}
	
	public static FileHandle loadMap(String fileName){
		return getAsset(Constants.LOCAL_MAPS_PATH+fileName);
	}
	
	public static FileHandle getCharSheet(String fileName){
		return getAsset(Constants.LOCAL_CHARSHEET_PATH+fileName);
	}
	
	public static FileHandle getAsset(String fileName){
		return new FileHandle(Constants.LOCAL_ASSETS_PATH+fileName);
	}

}
