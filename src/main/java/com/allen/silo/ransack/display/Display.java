package com.allen.silo.ransack.display;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.geom.Line;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.ui.element.Image;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.allen.silo.ransack.character.Cursor;
import com.allen.silo.ransack.character.PlayableCharacter;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.utils.Constants;

public class Display implements Screen{
	public static Logger logger = Logger.getLogger(Display.class.getName());
	private Spritesheet spritesheet;
	
	private Texture windowPaneT;
	private Image windowPane;
	private Graphics windowPaneG;
		
	public Display(){
		this.spritesheet = new Spritesheet();
		windowPaneT = new Texture(Constants.windowX, Constants.windowY, Pixmap.Format.Alpha);
		windowPane = new Image(windowPaneT);
		try {
			//windowPaneG = new Graphics();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "SlickException in Display(): ", e);
		}
	}
	
	/*
	 * Slick wrapper methods
	 */

	public void flush() {
		windowPaneG.flush();
	}
	
	public void clear(){
		//windowPaneG.clear();
	}
	
	
	/*
	 * Ransack methods
	 */
	

	public void drawImageMap(Graphics g, int x, int y, BasicMap m){
		m.draw(g, x, y);
	}
	
	public void redrawMapWindow(BasicMap m){
		
	}
	
	public void drawPlayableCharacter(Graphics g, BasicMap m, PlayableCharacter p){
		if (p.getName().equalsIgnoreCase("player")){
			int playerRenderX = p.getPoint().getRenderX();
			int playerRenderY = p.getPoint().getRenderY();
			
			if ( !((Player)p).isOutsideTopOrBottomMargins(m) ){
				playerRenderY = 5*Constants.BLOCKSIZE;
			}
			if( !((Player)p).isOutsideLeftOrRightMargins(m) ) {
				playerRenderX = 5*Constants.BLOCKSIZE;
			}
			if ( ((Player)p).isOutsideBottomMargin(m) ){
				playerRenderY = playerRenderY + m.getOffsetY();
			}
			if( ((Player)p).isOutsideRightMargin(m)){
				playerRenderX = playerRenderX + m.getOffsetX();
			}
			this.showActualPlayerRenderCoords(g, playerRenderX, playerRenderY);
			g.drawSprite(p.getSprite(), playerRenderX, playerRenderY);
		}else{
			g.drawSprite(p.getSprite(), p.getPoint().getRenderX()+m.getOffsetX(), p.getPoint().getRenderY()+m.getOffsetY());
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
		
	public Image getWindowPane(){
		return windowPane;
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
}
