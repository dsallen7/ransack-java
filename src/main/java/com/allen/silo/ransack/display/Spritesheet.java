package com.allen.silo.ransack.display;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.ui.element.Image;

import com.allen.silo.ransack.utils.Constants;
import com.badlogic.gdx.assets.AssetManager;

public class Spritesheet {
	
	private Image sheet;
	private List<Image> mapTiles;
	
	public static Logger logger = Logger.getLogger(Spritesheet.class.getName());
	
	public Spritesheet(){
		this.sheet = new Image("/src/resources/mastersheet.bmp");
		mapTiles = new ArrayList<Image>();
		for (int i = 0; i < 128; i++){
			mapTiles.add(getSubImage(i*Constants.BLOCKSIZE%240, (i/8)*Constants.BLOCKSIZE));
		}
		for (int i = 128; i < 256; i++){
			mapTiles.add(getSubImage(i*Constants.BLOCKSIZE%240 + 240, ((i-128)/8)*Constants.BLOCKSIZE));
		}
		try {
		} catch (Exception e) {
			logger.log(Level.SEVERE, "SlickException in Spritesheet: ", e);
		}
	}
	
	public Image getSubImage(int x, int y){
		return new Image(sheet.getTextureRegion(new AssetManager()));
		//new AssetManager(new Texture(Constants.BLOCKSIZE, Constants.BLOCKSIZE, Pixmap.Format.Alpha), x, y, Constants.BLOCKSIZE, Constants.BLOCKSIZE))
		//return sheet.getSubImage(x, y, Constants.BLOCKSIZE, Constants.BLOCKSIZE);
	}
	
	public Image getTileImage(char c){
		Image ret = null;
		if (c == 'x'){
			ret = mapTiles.get(32);
		}else{
			ret = mapTiles.get(0);
		}
		return ret;
	}
	
	public Image getTileImage(int i){
		return mapTiles.get(i);
	}
}
