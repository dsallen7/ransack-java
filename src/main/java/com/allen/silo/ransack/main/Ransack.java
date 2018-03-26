package com.allen.silo.ransack.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.tiled.exception.TiledException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.display.Display;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.FileUtility;
import com.allen.silo.ransack.character.NonPlayerCharacter;
import com.allen.silo.ransack.character.PlayableCharacter;

public class Ransack extends BasicGame implements InputProcessor{
	
	public static Logger logger = Logger.getLogger(Ransack.class.getName());
	
	public static final String GAME_IDENTIFIER = "com.allen.silo.crawler";
	private static Display display;
	private PlayableMap currentMap;
	private Player player;
	private NonPlayerCharacter girl;
	
	private ArrayList<PlayableCharacter> playables;

	public Ransack(){}

	@Override
	public void render(Graphics g){
		display.drawImageMap(g, currentMap);
		for (PlayableCharacter pc : playables){
			display.drawPlayableCharacter(g, currentMap, pc);
		}
		display.showPlayerLocation(g, player);
	}

	@Override
	public void initialise(){
		try {
			currentMap = new PlayableMap(FileUtility.loadMap("map1.tmx"));
			display = new Display();
			
			player = new Player(new Location(5, 5), currentMap);
			girl = new NonPlayerCharacter(new Location(8, 2), "fherosheet.bmp", currentMap, "girl");
			playables = new ArrayList<PlayableCharacter>();
			playables.add(player);
			playables.add(girl);
		} catch ( TiledException te){
			logger.log(Level.SEVERE, null, te);
		}catch (IOException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void update(float delta){
		for (PlayableCharacter pc : playables){
			if(pc.isNPC()){
				pc.enqueueRandom(currentMap);
			}
			pc.move(currentMap);
		}
	}
	
	@Override
	public boolean keyDown(int key){
		if (key == Input.Keys.ESCAPE) {
			System.exit(0);
		}
		if (key == Input.Keys.F1) {
		}
		if (Constants.MOVE_KEYS.contains(key)){
			move(key);
		}
		return true;
	}
	
	/*
	 * Ransack methods
	 */
		
	public void move(int key){
		player.enqueueMove(key, currentMap);
	}
	
	public void endScreen(){
		
	}
	
	public void loadWorld(){
		
	}
	
	public void loadSavedGame(){
		
	}
	
	@Override
	public void interpolate(float alpha) {
		for(PlayableCharacter pc : playables){
			pc.getPoint().interpolate(null, alpha);
		}
	}
}
