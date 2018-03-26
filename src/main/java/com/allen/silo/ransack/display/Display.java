package com.allen.silo.ransack.display;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.core.geom.Line;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.ui.element.Image;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.allen.silo.ransack.character.Cursor;
import com.allen.silo.ransack.character.PlayableCharacter;
import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.maps.BasicMap;
import com.allen.silo.ransack.utils.Constants;

public class Display {
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
	

	public void drawImageMap(Graphics g, BasicMap m){
		m.draw(g, 0, 0);
	}
	
	public void drawPlayableCharacter(Graphics g, BasicMap m, PlayableCharacter p){
		g.drawSprite(p.getSprite(), p.getPoint().getRenderX(), p.getPoint().getRenderY());
	}
	
	public void drawPlayer(Graphics g, BasicMap m, Player p){
		g.drawSprite(p.getSprite(), p.getPoint().getRenderX(), p.getPoint().getRenderY());
	}
	
	public void showPlayerLocation(Graphics g, Player p){
		g.drawString("Player X: " + p.getX(), 400, 400);
		g.drawString("Player Y: " + p.getY(), 400, 410);
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
}
