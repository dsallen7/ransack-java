package com.allen.silo.ransack.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ImageUtility {
	public static Logger logger = Logger.getLogger(ImageUtility.class.getName());
	
	public static List<Texture> loadSpriteImages(Pixmap spriteSheet){
		List<Texture> lt = new ArrayList<Texture>();
		for (int i = 0; i < 8*Constants.BLOCKSIZE; i+=Constants.BLOCKSIZE){
			Pixmap p = new Pixmap(30, 30, spriteSheet.getFormat());
			int alpha = p.getPixel(0, 0);
			p.drawPixmap(spriteSheet, 0, 0, i, 0, Constants.BLOCKSIZE, Constants.BLOCKSIZE);
			Texture t = new Texture(p);
			lt.add(t);
		}
		for (int i = 0; i < 8*Constants.BLOCKSIZE; i+=Constants.BLOCKSIZE){
			Pixmap p = new Pixmap(30, 30, spriteSheet.getFormat());
			p.drawPixmap(spriteSheet, 0, 0, i, Constants.BLOCKSIZE, Constants.BLOCKSIZE, Constants.BLOCKSIZE);
			lt.add(new Texture(p));
		}
		Pixmap p = new Pixmap(30, 30, spriteSheet.getFormat());
		p.drawPixmap(spriteSheet, 0, 0, 240, 0, Constants.BLOCKSIZE, Constants.BLOCKSIZE);
		lt.add(new Texture(p));
		p = new Pixmap(30, 30, spriteSheet.getFormat());
		p.drawPixmap(spriteSheet, 0, 0, 240, Constants.BLOCKSIZE, Constants.BLOCKSIZE, Constants.BLOCKSIZE);
		lt.add(new Texture(p));
		return lt;
	}
}
