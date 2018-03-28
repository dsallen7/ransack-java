package com.allen.silo.ransack.editor;

import java.io.IOException;
import java.util.logging.Logger;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.Cursor;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.display.Display;
import com.allen.silo.ransack.maps.EditorMap;
import com.allen.silo.ransack.utils.Constants;
import com.badlogic.gdx.Input;

public class Editor extends BasicGame { 
	
	public static Logger logger = Logger.getLogger(Editor.class.getName());

	private Cursor cursor;
	//public static AppGameContainer appgc;
	private static Display display;
	
	private Toolbar toolbar;
	
	public EditorMap currentMap;
	
	public Editor() {}
	
	@Override
	public void initialise(){
		//InputProvider provider = new InputProvider(container.getInput());
		//provider.addListener(this);
		try {
			currentMap = new EditorMap("");
			display = new Display();
			cursor = new Cursor(new Location(0,0), "", currentMap);
		} catch ( TiledException te){ 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(float delta){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g){
		/*
		display.clear();
		display.drawImageMap(currentMap);
		//draw hero
		display.drawCursor(currentMap, cursor);
		display.showCursorLocation(cursor);
		display.flush();
		g.drawTexture(display.getWindowPane(), 50, 50);*/
		//display.getWindowPane().draw(0,0);
	}
	
	public void keyPressed(int key, char c){
		if (key == Input.Keys.ESCAPE) {
			System.exit(0);
		}
		if (key == Input.Keys.F1) {
			/*
			if (appgc != null) {
				try {
					appgc.setDisplayMode(600, 600, false);
					appgc.reinit();
				} catch (Exception e) {
					logger.log(Level.SEVERE, null, e); 
				}
			}*/
		}
		if (Constants.MOVE_KEYS.contains(key)){
			move(key);
		}
	}
	
	public void move(int key){
		cursor.move(key, currentMap);
	}
	
	public static void main(String[] args){
		/*
		try{
			logger.log(Level.INFO, "Launching editor...");
			appgc = new AppGameContainer(new Editor("Crawler Editor"));
			appgc.setDisplayMode(Constants.editorWindowX, Constants.editorWindowY, false);
			appgc.start();
		}catch (SlickException ex){
			logger.log(Level.SEVERE, null, ex);
		}*/
	}
	
	@Override
	public void interpolate(float arg0) {
		// TODO Auto-generated method stub
		
	}
}
