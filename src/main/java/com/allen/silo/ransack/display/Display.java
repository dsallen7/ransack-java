package com.allen.silo.ransack.display;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.ui.element.Image;
import org.mini2Dx.tiled.renderer.OrthogonalTileLayerRenderer;

import com.allen.silo.ransack.character.Cursor;
import com.allen.silo.ransack.character.PlayableCharacter;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.maps.PlayableMap;
import com.allen.silo.ransack.utils.Constants;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Display implements Screen{
	public static Logger logger = Logger.getLogger(Display.class.getName());
	private Spritesheet spritesheet;
	
	private Texture windowPaneT;
	private Image windowPane;
	private Graphics windowPaneG;
	private OrthogonalTileLayerRenderer tileLayerRenderer;
	private Location windowTopLeft;
	private Location windowBottomRight;
	private Location oldWindowTopLeft;
	private Location oldWindowBottomRight;
	
	protected PlayableMap currentMap;
		
	public Display(){
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Ransack methods
	 */
	public void drawImageMap(Graphics g){
		tileLayerRenderer.drawLayer(g, currentMap.getTileLayer(0), 0, 0, getWindowTopLeft().getLocX(),getWindowTopLeft().getLocY(),	10, 10);
		tileLayerRenderer.drawLayer(g, currentMap.getTileLayer(1), 0, 0, getWindowTopLeft().getLocX(),getWindowTopLeft().getLocY(),	10, 10);
		//currentMap.draw(g, currentMap.getOffsetX(), currentMap.getOffsetY());
	}
	
	public void calculateMapWindow(){
		int windowLeftX = Math.max(Math.min(this.currentMap.getActorLocation().getLocX()-5, this.currentMap.dimensionX()-10), 0);
		int windowTopY = Math.max(Math.min(this.currentMap.getActorLocation().getLocY()-5, this.currentMap.dimensionY()-10), 0);
		Location newTopLeft = new Location(windowLeftX, windowTopY);
		if (windowTopLeft != null && (windowLeftX != windowTopLeft.getLocX() || windowTopY != windowTopLeft.getLocY()))
			setOldWindowTopLeft(getWindowTopLeft());
		/*
		if (windowTopLeft != null && windowLeftX != windowTopLeft.getLocX())
			oldWindowTopLeft = new Location(windowLeftX, windowTopLeft.getLocY());
		if (windowTopLeft != null && windowTopY != windowTopLeft.getLocY())
			oldWindowTopLeft = new Location(windowTopLeft.getLocX(), windowTopY);
			*/
		setWindowTopLeft( new Location(windowLeftX, windowTopY) );
		setWindowBottomRight( new Location(windowLeftX+10, windowTopY + 10));
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
		g.drawString("Player Y: " + p.getY() + "rY: " + p.getPoint().getRenderY(), 400, 410);
		g.drawString("Player stepIndex: " + p.getStepIndex(), 400, 420);
	}
	

	public void showWindowOffsets(Graphics g, BasicMap m){
		g.drawString("Offset X: " + m.getOffsetX(), 400, 430);
		g.drawString("Offset Y: " + m.getOffsetY(), 400, 440);
	}
	
	public void showActualPlayerRenderCoords(Graphics g, int x, int y){
		g.drawString("Actual X: " + x, 400, 450);
		g.drawString("Actual Y: " + y, 400, 460);
	}
	
	public void showTopLeftAndOldTopLeft(Graphics g){
		g.drawString("Left: " + this.windowTopLeft.getLocX() + " Top: " + this.getWindowTopLeft().getLocY(), 400, 450);
		if (this.oldWindowTopLeft != null)
			g.drawString("OldLeft: " + this.oldWindowTopLeft.getLocX() + " OldTop: " + this.getOldWindowTopLeft().getLocY(), 400, 460);
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

	public Spritesheet getSpritesheet() {
		return spritesheet;
	}

	public void setSpritesheet(Spritesheet spritesheet) {
		this.spritesheet = spritesheet;
	}

	public Location getWindowTopLeft() {
		return windowTopLeft;
	}

	public void setWindowTopLeft(Location windowTopLeft) {
		this.windowTopLeft = windowTopLeft;
	}

	public Location getWindowBottomRight() {
		return windowBottomRight;
	}

	public void setWindowBottomRight(Location windowBottomRight) {
		this.windowBottomRight = windowBottomRight;
	}

	public boolean isInWindow(Location location) {
		return getWindowTopLeft().getLocX() <= location.getLocX() && 
				location.getLocX() < getWindowBottomRight().getLocX() &&
				getWindowTopLeft().getLocY() <= location.getLocY() && 
				location.getLocY() < getWindowBottomRight().getLocY(); 
	}

	public Location getOldWindowTopLeft() {
		return oldWindowTopLeft;
	}

	public void setOldWindowTopLeft(Location oldWindowTopLeft) {
		this.oldWindowTopLeft = oldWindowTopLeft;
	}

	public Location getOldWindowBottomRight() {
		return oldWindowBottomRight;
	}

	public void setOldWindowBottomRight(Location oldWindowBottomRight) {
		this.oldWindowBottomRight = oldWindowBottomRight;
	}
}
