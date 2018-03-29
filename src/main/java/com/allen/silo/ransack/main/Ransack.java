package com.allen.silo.ransack.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.di.annotation.Autowired;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.minibus.MessageBus;
import org.mini2Dx.tiled.TiledObject;
import org.mini2Dx.tiled.exception.TiledException;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.AlignedModal;
import org.mini2Dx.ui.style.UiTheme;

import com.allen.silo.ransack.character.NonPlayerCharacter;
import com.allen.silo.ransack.character.PlayableCharacter;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.display.Display;
import com.allen.silo.ransack.handlers.Event;
import com.allen.silo.ransack.handlers.EventType;
import com.allen.silo.ransack.handlers.MessageDataImpl;
import com.allen.silo.ransack.handlers.MessageHandlerImpl;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.FileUtility;
import com.allen.silo.ransack.utils.MathUtility;
import com.allen.silo.ransack.world.World;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;

public class Ransack extends BasicGame implements InputProcessor{
	
	public static Logger logger = Logger.getLogger(Ransack.class.getName());
	
	public static final String GAME_IDENTIFIER = "com.allen.silo.ransack";
    private AssetManager assetManager;
    private UiContainer uiContainer;
	private static Display display;
	
	public static World world;
	@Autowired
	private PlayableMap currentMap;
	private Player player;
	
	private static Queue<Event> eventQueue;
	
	private ArrayList<PlayableCharacter> playables;
	
	public static MessageBus messageBus;
	public static MessageHandlerImpl messageHandler;

	public Ransack(){}

	@Override
	public void initialise(){
		try {
			world = new World();
			currentMap = (PlayableMap) world.getCurrentMap();
	        //Create fallback file resolver so we can use the default mini2Dx-ui theme
	        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());
	        //Create asset manager for loading resources
	        assetManager = new AssetManager(fileHandleResolver);
	        //Add mini2Dx-ui theme loader
	        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));
	        //Load default theme
	        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);
	        
	        eventQueue = new LinkedList<Event>();
	        
	        AlignedModal modal = Mdx.xml.fromXml(FileUtility.getUIAsset("startgamemodal.xml").reader(), AlignedModal.class);

	        uiContainer = new UiContainer(this, assetManager);
	        uiContainer.add(modal);
			display = new Display();
			display.setCurrentMap(currentMap);
			
			messageBus = new MessageBus();
			messageHandler = new MessageHandlerImpl();
			messageBus.createOnUpdateExchange(messageHandler);
	        			
			setPlayables();
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
		 * Process Ransack Events
		 */
	    Event e = getEvent();
	    if (e != null){
	    	switch(e.getEventType()){
	    	case DIALOG:
	    		break;
	    	case BATTLE:
	    		break;
	    	case MINIGAME:
	    		break;
	    	case SHOPPING:
	    		break;
	    	case INTERMAP:
	    		logger.log(Level.INFO, "Intermap, direction: " + e.getDir());
	    		Location newL = world.getIntermapLocation(e.getMapFrom(), e.getMapTo(), player.getLocation(), e.getDir());
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
	     * Process player and NPC movements
	     */
		for (PlayableCharacter pc : playables){
			if(pc.isNPC()){
				Location newL = null;
				newL = pc.getRandomLocation();
				processMove(pc, newL);
			}else{
				currentMap.setActorLocation(pc.getLocation());
			}
			pc.move();
		}
		/*
		 * Update map
		 */
		currentMap.updateOffsets(player);
		/*
		 * Update display
		 */
		display.calculateMapWindow();
		/*
		 * Process UI
		 */
        if(!assetManager.update()) {
            //Wait for asset manager to finish loading assets
            return;
	    }
	    if(!uiContainer.isThemeApplied()) {
	            uiContainer.setTheme(assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
	    }
	    uiContainer.update(delta);
	    /*
	     * Update MessageBus
	     */
	    messageBus.update(delta);
	    
	}
	
	@Override
	public void interpolate(float alpha) {
		for(PlayableCharacter pc : playables){
			pc.takeStep();
			pc.getPoint().interpolate(null, alpha);
		}
        uiContainer.interpolate(alpha);
	}
	
	@Override
	public void render(Graphics g){
		display.drawImageMap(g);
		for (PlayableCharacter pc : playables){
			if(display.isInWindow(pc.getLocation()))
				display.drawPlayableCharacter(g, pc);
		}
		display.showTopLeftAndOldTopLeft(g);
		//display.showPlayerLocation(g, player);
		//display.showWindowOffsets(g, currentMap);
        //uiContainer.render(g);
	}
	
	@Override
	public boolean keyDown(int key){
		if (key == Input.Keys.ESCAPE) {
			System.exit(0);
		}
		if (key == Input.Keys.F1) {
		}
		if (Constants.MOVE_KEYS.contains(key)){
			playerMove(key);
		}
		return true;
	}
	
	/*
	 * Ransack methods
	 */
	
	public void setPlayables(){
		playables = new ArrayList<PlayableCharacter>();
		Location newPlayerLocation = null;
		for (TiledObject to : currentMap.getObjectGroup("NPCs").getObjects()){
			if(to.getType().equals("NPC")){
				NonPlayerCharacter npc = new NonPlayerCharacter(MathUtility.getGridCoordFromScreen(to.getX(), to.getY()), to.getName(), currentMap);
				playables.add( npc );
				currentMap.setCellOccupied(npc.getLocation(), npc.getName());
			}else if(to.getType().equals("Player")){
				if (currentMap.getActorLocation() == null)
					newPlayerLocation = MathUtility.getGridCoordFromScreen(to.getX(), to.getY());
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
		player.enqueueMove(newPlayerLocation);
		playables.add(player);
		currentMap.setCellOccupied(player.getLocation(), player.getName());
	}
		
	public void playerMove(int key){
		processMove(player, player.getNewLocation(key, player.getLocation()));
	}
	
	public void processMove(PlayableCharacter pc, Location newL){
		if (currentMap.isMovable(newL)){
			String isCellOccupied = currentMap.isTileOccupied(newL);
			if( isCellOccupied != null){
				//logger.log(Level.INFO, "Tile occupied by: " + isCellOccupied);
				Ransack.messageBus.broadcast(EventType.DIALOG.toString(), new MessageDataImpl(0, "Hello"));//"from: " + this.name + " to: " + isCellOccupied
				
				return;
			}
			currentMap.setCellOccupied(newL, pc.getName());
			currentMap.clearCellOccupied(pc.getLocation());	
			pc.enqueueMove(newL);
		}
	}
	
	public void changeCurrentMap(String mapName){
		BasicMap m = world.changeCurrentMap(mapName);
		setCurrentMap(m);
		setPlayables();
	}
	
	public void changeCurrentMap(String mapName, Location target){
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
