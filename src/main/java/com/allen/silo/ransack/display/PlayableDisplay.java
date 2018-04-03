package com.allen.silo.ransack.display;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.di.annotation.Autowired;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.tiled.renderer.OrthogonalTileLayerRenderer;
import org.mini2Dx.ui.element.Image;

import com.allen.silo.ransack.character.BaseCharacter;
import com.allen.silo.ransack.character.Cursor;
import com.allen.silo.ransack.character.PlayableCharacter;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.Direction;
import com.allen.silo.ransack.character.attributes.MapLocation;
import com.allen.silo.ransack.display.events.MapShift;
import com.allen.silo.ransack.display.ui.RansackUiContainer;
import com.allen.silo.ransack.handlers.Log;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class PlayableDisplay extends BaseDisplay {
	public static Logger logger = Logger.getLogger(PlayableDisplay.class.getName());

	@Autowired
	private RansackAssetManager assetManager;
	
	private Texture windowPaneT;
	private Image windowPane;
	private Graphics windowPaneG;
	private OrthogonalTileLayerRenderer tileLayerRenderer;
	private MapLocation windowTopLeft;
	private MapLocation windowBottomRight;
	private MapLocation oldWindowTopLeft;
	private MapLocation oldWindowBottomRight;

	protected PlayableMap currentMap;
	protected BaseCharacter actor;
	protected Queue<MapShift> mapShiftQueue;
	protected MapShift mapShiftInProgress;
	
	private RansackTexture ewMapShift;
	private RansackTexture nsMapShift;
	private RansackTexture mapWindow;
	
	private static Queue<Log> logQueue;
	private static String[] logHistory;
	
	static{

        logQueue = new LinkedList<Log>();
        logHistory = new String[3];
	}
	
	public PlayableDisplay(RansackUiContainer uiContainer) throws SerializationException{
		super(uiContainer);
		assetManager = Mdx.di.getBean(RansackAssetManager.class);
		mapShiftQueue = new LinkedList<MapShift>();
	}
	
	public void update(float delta){
		calculateMapWindow();
		currentMap.setActorLocation(actor.getLocation());
		uiContainer.updateStats((Player) actor);
	    Log l = getLog();
	    if (l != null){
	    	logHistory[2] = logHistory[1];
	    	logHistory[1] = logHistory[0];
	    	logHistory[0] = l.getContents();
	    }
		super.update(delta);
	}
	
	public void render(Graphics g, HashMap<String, PlayableCharacter> playables){
		/*
		if(mapShiftQueue.poll() != null){
			mapShiftInProgress = mapShiftQueue.remove();
		}
		if (mapShiftInProgress != null){
			mapShiftInProgress.decrementCounter();
			if (mapShiftInProgress.getCounter() > 0){
				
				return;	
			}else{
				mapShiftInProgress = null;
			}
		}*/
		drawImageMapBelowCharacters(g);
		for (PlayableCharacter pc : playables.values()){
			if(isInWindow(pc.getLocation()))
				drawPlayableCharacter(g, pc);
		}
		//g.setFont(assetManager.get("arial-15.fnt"));
		g.setFont(assetManager.get("courier.ttf"));
		drawImageMapAboveCharacters(g);
		showTopLeftAndOldTopLeft(g);
		showPlayerLocation(g, (Player) playables.get("player"));
		//showPlayerStats(g, (Player) playables.get("player"));
		//showWindowOffsets(g, currentMap);

        uiContainer.render(g);
    	uiContainer.printLog(g, logHistory);
	}
	
	public void drawImageMapBelowCharacters(Graphics g){
		tileLayerRenderer.drawLayer(g, currentMap.getTileLayer(0), 0, 0, getWindowTopLeft().getLocX(),getWindowTopLeft().getLocY(),	10, 10);
		tileLayerRenderer.drawLayer(g, currentMap.getTileLayer(1), 0, 0, getWindowTopLeft().getLocX(),getWindowTopLeft().getLocY(),	10, 10);
		//currentMap.draw(g, currentMap.getOffsetX(), currentMap.getOffsetY());
	}
	
	public void drawImageMapAboveCharacters(Graphics g){
		
		tileLayerRenderer.drawLayer(g, currentMap.getTileLayer(2), 0, 0, getWindowTopLeft().getLocX(),getWindowTopLeft().getLocY(),	10, 10);
		//currentMap.draw(g, currentMap.getOffsetX(), currentMap.getOffsetY());
	}
	
	public void drawPlayableCharacter(Graphics g, PlayableCharacter p){
		if (p.getName().equalsIgnoreCase("player")){
			int playerRenderX = p.getPoint().getRenderX();
			int playerRenderY = p.getPoint().getRenderY();
			
			if ( !((Player)p).isOutsideTopOrBottomMargins(currentMap) ){
				playerRenderY = 5*Constants.BLOCKSIZE;
			}
			if( !((Player)p).isOutsideLeftOrRightMargins(currentMap) ) {
				playerRenderX = 5*Constants.BLOCKSIZE;
			}
			if ( ((Player)p).isOutsideBottomMargin(currentMap) ){
				playerRenderY = playerRenderY + currentMap.getOffsetY();
			}
			if( ((Player)p).isOutsideRightMargin(currentMap)){
				playerRenderX = playerRenderX + currentMap.getOffsetX();
			}
			//this.showActualPlayerRenderCoords(g, playerRenderX, playerRenderY);
			g.drawSprite(p.getSprite(), playerRenderX, playerRenderY);
		}else{
			g.drawSprite(p.getSprite(), p.getPoint().getRenderX()+currentMap.getOffsetX(), p.getPoint().getRenderY()+currentMap.getOffsetY());
		}
	}
	
	public void drawPlayer(Graphics g, BasicMap m, Player p){
		g.drawSprite(p.getSprite(), p.getPoint().getRenderX(), p.getPoint().getRenderY());
	}
	
	public void showPlayerLocation(Graphics g, Player p){
		g.drawString("Player X: " + p.getX() + "rX: " + p.getPoint().getRenderX(), 400, 400);
		g.drawString("Player Y: " + p.getY() + "rY: " + p.getPoint().getRenderY(), 400, 420);
		g.drawString("Player stepIndex: " + p.getStepIndex(), 400, 440);
	}

	public void showPlayerStats(Graphics g, Player p){
		g.drawString("HP: " + p.getCurrentHP() + " MaxHP: " + p.getMaxHP(), 400, 400);
		g.drawString("MP: " + p.getCurrentMP() + " MaxMP: " + p.getMaxMP(), 400, 420);
		g.drawString("Player quantums: " + p.getClock().getQuantums(), 400, 440);
	}

	public void showWindowOffsets(Graphics g, BasicMap m){
		g.drawString("Offset X: " + m.getOffsetX(), 400, 430);
		g.drawString("Offset Y: " + m.getOffsetY(), 400, 450);
	}
	
	public void showActualPlayerRenderCoords(Graphics g, int x, int y){
		g.drawString("Actual X: " + x, 400, 450);
		g.drawString("Actual Y: " + y, 400, 460);
	}
	
	public void showTopLeftAndOldTopLeft(Graphics g){
		g.drawString("Left: " + this.windowTopLeft.getLocX() + " Top: " + this.getWindowTopLeft().getLocY(), 400, 460);
		if (this.oldWindowTopLeft != null)
			g.drawString("OldLeft: " + this.oldWindowTopLeft.getLocX() + " OldTop: " + this.getOldWindowTopLeft().getLocY(), 400, 480);
	}
	
	public void showCursorLocation(Cursor c){
		windowPaneG.drawString("Cursor X: " + c.getX(), 400, 400);
		windowPaneG.drawString("Cursor Y: " + c.getY(), 400, 410);
	}


	public void drawCursor(BasicMap currentMap, Cursor cursor) {
		/*
		windowPaneG.drawShape(new Line(cursor.getX()*Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE, 
				cursor.getX()*Constants.BLOCKSIZE + Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE));  //top border
		windowPaneG.draw(new Line(cursor.getX()*Constants.BLOCKSIZE + Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE,
				cursor.getX()*Constants.BLOCKSIZE + Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE + Constants.BLOCKSIZE));  //right border
		windowPaneG.draw(new Line(cursor.getX()*Constants.BLOCKSIZE + Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE + Constants.BLOCKSIZE,
				cursor.getX()*Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE + Constants.BLOCKSIZE));  //bottom border
		windowPaneG.draw(new Line(cursor.getX()*Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE + Constants.BLOCKSIZE,
				cursor.getX()*Constants.BLOCKSIZE, cursor.getY()*Constants.BLOCKSIZE));  //bottom border
				*/
	}
	
	public void interpolateMap(){
		
	}
	
	public void calculateMapWindow(){
		int windowLeftX = Math.max(Math.min(this.currentMap.getActorLocation().getLocX()-5, this.currentMap.dimensionX()-10), 0);
		int windowTopY = Math.max(Math.min(this.currentMap.getActorLocation().getLocY()-5, this.currentMap.dimensionY()-10), 0);
		MapLocation newTopLeft = new MapLocation(windowLeftX, windowTopY);
		if (windowTopLeft != null && (windowLeftX != windowTopLeft.getLocX() || windowTopY != windowTopLeft.getLocY())){
			if (windowLeftX < windowTopLeft.getLocX()){
				mapShiftQueue.add(new MapShift(windowTopLeft, newTopLeft, Direction.WEST));
			}
			if (windowLeftX > windowTopLeft.getLocX()){
				mapShiftQueue.add(new MapShift(windowTopLeft, newTopLeft, Direction.EAST));
			}
			if (windowTopY < windowTopLeft.getLocY()){
				mapShiftQueue.add(new MapShift(windowTopLeft, newTopLeft, Direction.NORTH));
			}
			if (windowTopY > windowTopLeft.getLocY()){
				mapShiftQueue.add(new MapShift(windowTopLeft, newTopLeft, Direction.SOUTH));
			}
			setOldWindowTopLeft(getWindowTopLeft());
		}
		setWindowTopLeft( newTopLeft );
		setWindowBottomRight( new MapLocation(windowLeftX+10, windowTopY + 10));
	}
	
	/*
	 * Access/helper methods
	 */
	
	public void setCurrentMap(BasicMap m){
		this.currentMap = (PlayableMap) m;
		if(tileLayerRenderer != null)
			tileLayerRenderer.dispose();
		tileLayerRenderer = new OrthogonalTileLayerRenderer(this.currentMap, false);
	}
		
	public Image getWindowPane(){
		return windowPane;
	}
	
	public MapLocation getWindowTopLeft() {
		return windowTopLeft;
	}

	public void setWindowTopLeft(MapLocation windowTopLeft) {
		this.windowTopLeft = windowTopLeft;
	}

	public MapLocation getWindowBottomRight() {
		return windowBottomRight;
	}

	public void setWindowBottomRight(MapLocation windowBottomRight) {
		this.windowBottomRight = windowBottomRight;
	}

	public boolean isInWindow(MapLocation location) {
		return getWindowTopLeft().getLocX() <= location.getLocX() && 
				location.getLocX() < getWindowBottomRight().getLocX() &&
				getWindowTopLeft().getLocY() <= location.getLocY() && 
				location.getLocY() < getWindowBottomRight().getLocY(); 
	}

	public MapLocation getOldWindowTopLeft() {
		return oldWindowTopLeft;
	}

	public void setOldWindowTopLeft(MapLocation oldWindowTopLeft) {
		this.oldWindowTopLeft = oldWindowTopLeft;
	}

	public MapLocation getOldWindowBottomRight() {
		return oldWindowBottomRight;
	}

	public void setOldWindowBottomRight(MapLocation oldWindowBottomRight) {
		this.oldWindowBottomRight = oldWindowBottomRight;
	}

	public BaseCharacter getActor() {
		return actor;
	}

	public void setActor(BaseCharacter actor) {
		this.actor = actor;
	}
	
	public static void enqueueLog(Log log){
		logQueue.add(log);
	}
	
	public static Log getLog(){
		return logQueue.poll();
	}
	
	public static String[] getLogHistory(){
		return logHistory;
	}
}
