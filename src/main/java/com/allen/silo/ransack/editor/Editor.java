package com.allen.silo.ransack.editor;

import java.io.IOException;
import java.util.logging.Logger;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.Cursor;
import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.display.BaseDisplay;
import com.allen.silo.ransack.maps.EditorMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.world.World;
import com.badlogic.gdx.Input;

public class Editor extends BasicGame { 
	
	public static Logger logger = Logger.getLogger(Editor.class.getName());
	public static final String GAME_IDENTIFIER = "com.allen.silo.ransack.seditor";

	private Cursor cursor;
	//public static AppGameContainer appgc;
	private static BaseDisplay display;
	
	private Toolbar toolbar;
	public EditorMap currentMap;
	private static World world;
	
	public Editor() {}
	
	@Override
	public void initialise(){
		//InputProvider provider = new InputProvider(container.getInput());
		//provider.addListener(this);
		try {
			currentMap = new EditorMap("");
			//display = new Display();
			cursor = new Cursor(new MapLocation(0,0), currentMap);
			world = new World(currentMap);
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
	public void interpolate(float arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g){
		//display.drawImageMap(g);
	}
	
	@Override
	public boolean keyDown(int key){
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
		return true;
	}
	
	public void move(int key){
		cursor.move(key, currentMap);
	}
}
