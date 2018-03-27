package com.allen.silo.ransack.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.tiled.TiledMap;
import org.mini2Dx.tiled.TiledObject;
import org.mini2Dx.tiled.exception.TiledException;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.AlignedModal;
import org.mini2Dx.ui.style.UiTheme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.display.Display;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.FileUtility;
import com.allen.silo.ransack.utils.MathUtility;
import com.allen.silo.ransack.character.NonPlayerCharacter;
import com.allen.silo.ransack.character.PlayableCharacter;

public class Ransack extends BasicGame implements InputProcessor{
	
	public static Logger logger = Logger.getLogger(Ransack.class.getName());
	
	public static final String GAME_IDENTIFIER = "com.allen.silo.crawler";
    private AssetManager assetManager;
    private UiContainer uiContainer;
	private static Display display;
	private PlayableMap currentMap;
	private Player player;
	private NonPlayerCharacter girl;
	
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
	
	private ArrayList<PlayableCharacter> playables;

	public Ransack(){}

	@Override
	public void render(Graphics g){
		display.drawImageMap(g, currentMap.getOffsetX(), currentMap.getOffsetY(), currentMap);
		for (PlayableCharacter pc : playables){
			display.drawPlayableCharacter(g, currentMap, pc);
		}
		display.showPlayerLocation(g, player);
		display.showWindowOffsets(g, currentMap);
        uiContainer.render(g);
	}

	@Override
	public void initialise(){
		try {
	        //Create fallback file resolver so we can use the default mini2Dx-ui theme
	        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());
	        //Create asset manager for loading resources
	        assetManager = new AssetManager(fileHandleResolver);
	        //Add mini2Dx-ui theme loader
	        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));
	        //Load default theme
	        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);
	        
	        AlignedModal modal = Mdx.xml.fromXml(FileUtility.getUIAsset("startgamemodal.xml").reader(), AlignedModal.class);

	        uiContainer = new UiContainer(this, assetManager);
	        uiContainer.add(modal);
	        float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();
			display = new Display();
			currentMap = new PlayableMap(FileUtility.loadMap("map1.tmx"));
			
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, w, h);
	        camera.update();
	        			
			playables = new ArrayList<PlayableCharacter>();
			//player = new Player(new Location(5, 5), currentMap);
			//playables.add(player);
			for (TiledObject to : currentMap.getObjectGroup("NPCs").getObjects()){
				if(to.getType().equals("NPC")){
					playables.add( new NonPlayerCharacter(MathUtility.getGridCoordFromScreen(to.getX(), to.getY()), to.getName(), currentMap));
				}else if(to.getType().equals("Player")){
					player = new Player(MathUtility.getGridCoordFromScreen(to.getX(), to.getY()), currentMap);
					playables.add(player);
				}
			}
			//playables.add(girl);
		} catch ( TiledException te){
			logger.log(Level.SEVERE, null, te);
		}catch (IOException e) {
			logger.log(Level.SEVERE, null, e);
		} catch (SerializationException e) {
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
		currentMap.updateOffsets(player);
        if(!assetManager.update()) {
            //Wait for asset manager to finish loading assets
            return;
	    }
	    if(!uiContainer.isThemeApplied()) {
	            uiContainer.setTheme(assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
	    }
	    uiContainer.update(delta);
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
			//pc.takeStep();
			pc.getPoint().interpolate(null, alpha);
		}
        uiContainer.interpolate(alpha);
	}
}
