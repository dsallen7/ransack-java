package com.allen.silo.ransack.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.di.annotation.Autowired;
import org.mini2Dx.core.di.annotation.Prototype;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.minibus.MessageBus;
import org.mini2Dx.tiled.TiledObject;
import org.mini2Dx.tiled.exception.TiledException;
import org.mini2Dx.ui.style.UiTheme;

import com.allen.silo.ransack.character.NonPlayerCharacter;
import com.allen.silo.ransack.character.PlayableCharacter;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.display.OverworldDisplay;
import com.allen.silo.ransack.display.RansackAssetManager;
import com.allen.silo.ransack.display.ui.RansackUiContainer;
import com.allen.silo.ransack.handlers.Event;
import com.allen.silo.ransack.handlers.EventMessageDataImpl;
import com.allen.silo.ransack.handlers.EventMessageHandlerImpl;
import com.allen.silo.ransack.handlers.EventType;
import com.allen.silo.ransack.handlers.LogMessageHandlerImpl;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.FileUtility;
import com.allen.silo.ransack.utils.MathUtility;
import com.allen.silo.ransack.world.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;

@Prototype
public class Ransack extends BasicGame implements InputProcessor{
	
	public static Logger logger = Logger.getLogger(Ransack.class.getName());
	
	public static final String GAME_IDENTIFIER = "com.allen.silo.ransack";
    
	@Autowired
	private RansackAssetManager assetManager;
    private RansackUiContainer uiContainer;
	private static OverworldDisplay display;
	
	public static World world;
	private PlayableMap currentMap;
	private Player player;
	private HashMap<String, PlayableCharacter> playables;
	
	private static Queue<Event> eventQueue;
	//private static Queue<Dialogue> dialogueQueue;
	
	public static MessageBus eventMessageBus;
	public static EventMessageHandlerImpl eventMessageHandler;

	public static MessageBus logMessageBus;
	public static LogMessageHandlerImpl logMessageHandler;
	
	public static FileHandleResolver fileHandleResolver;

	static{
        eventQueue = new LinkedList<Event>();
        
		eventMessageBus = new MessageBus();
		eventMessageHandler = new EventMessageHandlerImpl();
		eventMessageBus.createOnUpdateExchange(eventMessageHandler);
	}
	
	public Ransack(){}

	@Override
	public void initialise(){
		try {
			Mdx.di.scan(GAME_IDENTIFIER);
			world = new World();
			currentMap = (PlayableMap) world.getCurrentMap();
	        assetManager = Mdx.di.getBean(RansackAssetManager.class);
	        
	        uiContainer = new RansackUiContainer(this, assetManager);
	        			
			setPlayables();

	        display = new OverworldDisplay(uiContainer, currentMap, player);
			
			display.calculateMapWindow();
		} catch ( TiledException te){
			logger.log(Level.SEVERE, null, te);
		}catch (IOException e) {
			logger.log(Level.SEVERE, null, e);
		} catch (SerializationException e) {
			logger.log(Level.SEVERE, null, e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void update(float delta){
		/*
		 * Process UI
		 */
		try{
	        if(!assetManager.update()) {
	            //Wait for asset manager to finish loading assets
	            return;
		    }
		}catch (Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	    if(!uiContainer.isThemeApplied()) {
	    	uiContainer.setTheme(assetManager.get("ransack-mdx-theme.json", UiTheme.class));
	    }
	    uiContainer.update(delta);
	    if (uiContainer.isWaiting()){
	    	return;
	    }else{
	    	Gdx.input.setInputProcessor(this);
	    }

	    /*
	     * Process player and NPC movements
	     */
		for (PlayableCharacter pc : playables.values()){
			pc.update(currentMap);
		}
		/*
		 * Process Ransack Events
		 */
	    Event e = getEvent();
	    if (e != null){
	    	switch(e.getEventType()){
	    	case RETURNCONTROL:
	    		returnControl();
	    		break;
	    	case OPENDIALOGUE:
				//currentMap.playables.get(isCellOccupied).face(pc.getLocation());
	    		uiContainer.openDialogue(e.getData(), playables.get(e.getDialogueTo()).getFace() );
	    		break;
	    	case CLOSEDIALOGUE:
	    		uiContainer.closeDialogue();
	    		break;
	    	case BATTLE:
	    		break;
	    	case MINIGAME:
	    		break;
	    	case SHOPPING:
	    		break;
	    	case INTERMAP:
	    		logger.log(Level.INFO, "Intermap, direction: " + e.getDir());
	    		MapLocation newL = world.getIntermapLocation(e.getMapFrom(), e.getMapTo(), player.getLocation(), e.getDir());
	    		if (newL != null){
	    			changeCurrentMap(e.getMapTo(), newL);
	    		}
	    		break;
	    	case STAIRSUP:
	    		changeCurrentMap(e.getMapTo());
	    		break;
	    	case STAIRSDOWN:
	    		changeCurrentMap(e.getMapTo());
	    		break;
	    	case PORTAL:
	    		changeCurrentMap(e.getMapTo(), e.getNewL());
	    		break;
	    	}
	    }
		/*
		 * Update display and map
		 */
		display.update(delta);
		currentMap.updateOffsets(player);
	    /*
	     * Update MessageBus
	     */
	    eventMessageBus.update(delta);
	}
	
	@Override
	public void interpolate(float alpha) {
		for(PlayableCharacter pc : playables.values()){
			//pc.takeStep();
			pc.getPoint().interpolate(null, alpha);
		}
		display.interpolateMap();
        uiContainer.interpolate(alpha);
	}
	
	@Override
	public void render(Graphics g){
		display.render(g, playables);
	}
	
	@Override
	public boolean keyDown(int key){
		if (key == Input.Keys.ESCAPE) {
			this.dispose();
			System.exit(0);
		}
		if (key == Input.Keys.F1) {
		}
		if (Constants.MOVE_KEYS.contains(key)){
			playerMove(key);
		}
		if (key == Input.Keys.H){
			player.incrementHP(3);
		}
		if (key == Input.Keys.G){
			player.decrementHP(3);
		}
		if (key == Input.Keys.M){
			player.incrementMP(3);
		}
		if (key == Input.Keys.N){
			player.decrementMP(3);
		}
		return true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button){
				
		return true;
	}
		
	
	/*
	 * Ransack methods
	 */
	public void setPlayables(){
		playables = new HashMap<String, PlayableCharacter>();
		MapLocation newPlayerLocation = null;

		for (TiledObject to : currentMap.getObjectGroup("NPCs").getObjects()){
			if(to.getType().equals("NPC")){
				NonPlayerCharacter npc = new NonPlayerCharacter(MathUtility.getMapLocationFromScreenCoords(to.getX(), to.getY()), FileUtility.loadCharacterScript(to.getName()+".xml"), currentMap);
				playables.put( to.getName(), npc );
				currentMap.setCellOccupied(npc.getLocation(), npc.getName());
			}else if(to.getType().equals("Player")){
				if (currentMap.getActorLocation() == null){
					newPlayerLocation = MathUtility.getMapLocationFromScreenCoords(to.getX(), to.getY());
					currentMap.setActorLocation(newPlayerLocation);
				}
			}
		}
		if (newPlayerLocation == null)
			newPlayerLocation = currentMap.getActorLocation();
		if (player == null){
			player = new Player(newPlayerLocation, currentMap);
		}else{
			player.setLocation(newPlayerLocation);
		}
		currentMap.setActorLocation(newPlayerLocation);
		//player.enqueueMove(newPlayerLocation);
		playables.put("player", player);
		currentMap.setCellOccupied(player.getLocation(), player.getName());
	}
		
	public void playerMove(int key){
		player.processMove(player.getNewLocation(key, player.getLocation()), currentMap);
	}
	
	
	/*
	 * Map management methods
	 */
	
	public void changeCurrentMap(String mapName){
		BasicMap m = world.changeCurrentMap(mapName);
		setCurrentMap(m);
		setPlayables();
	}
	
	public void changeCurrentMap(String mapName, MapLocation target){
		BasicMap m = world.changeCurrentMap(mapName);
		setCurrentMap(m);
		player.setLocation(target);
		currentMap.setActorLocation(target);
		setPlayables();
	}
	
	public void setCurrentMap(BasicMap m){
		this.currentMap = (PlayableMap) m;
		display.setCurrentMap(m);		
	}
	
	/*
	 * UI Methods
	 */
	
	
	public void returnControl(){
		Gdx.input.setInputProcessor(this);		
	}
	
	public void endScreen(){
		
	}
	
	public void loadWorld(){
		
	}
	
	public void loadSavedGame(){
		
	}
	
	public static void enqueueEvent(Event event){
		eventQueue.add(event);
	}
	
	public static Event getEvent(){
		return eventQueue.poll();
	}
}
