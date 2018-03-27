package com.allen.silo.ransack.maps;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.tiled.Layer;
import org.mini2Dx.tiled.TiledObject;
import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.MathUtility;
import com.badlogic.gdx.files.FileHandle;

public class PlayableMap extends BasicMap {

	private static final long serialVersionUID = 1202377979271661004L;
	public static Logger logger = Logger.getLogger(PlayableMap.class.getName());
	
	public PlayableMap(FileHandle fileName) throws TiledException, IOException{
		super(fileName);
		int numGroups = getObjectGroups().size();
		List<TiledObject> npcs = getObjectGroup("NPCs").getObjects();
		/*
		for (TiledObject to : npcs){
			logger.log(Level.INFO, "tiledObject Name: " + to.getName());
			logger.log(Level.INFO, "tiledObject X: " + to.getX());
			logger.log(Level.INFO, "tiledObject Y: " + to.getY());
			Location gridLocation = MathUtility.getGridCoordFromScreen(to.getX(), to.getY());
			logger.log(Level.INFO, "tiledObject grid X: " + gridLocation.getLocX());
			logger.log(Level.INFO, "tiledObject grid Y: " + gridLocation.getLocY());
		}*/
	}
	
	public void updateOffsets(Player p){
		if (p.isOutsideBottomMargin(this)){
			this.setOffsetY(-((this.dimensionY()*Constants.BLOCKSIZE)-(Constants.WINDOWSIZE*Constants.BLOCKSIZE)));
		}else if(p.isOutsideTopMargin(this)){
			this.setOffsetY(0);
		}else{
			this.setOffsetY(-(p.getY()-5)*Constants.BLOCKSIZE);
		}
		if (p.isOutsideRightMargin(this)){
			this.setOffsetX(-((this.dimensionX()*Constants.BLOCKSIZE)-(Constants.WINDOWSIZE*Constants.BLOCKSIZE)));
		}else if(p.isOutsideLeftMargin(this)){
			this.setOffsetX(0);
		}else{
			this.setOffsetX(-(p.getX()-5)*Constants.BLOCKSIZE);
		}
	}
		
	@Override
	public boolean isMovable(Location l){
		boolean isOnMap = super.isMovable(l);
		if (!isOnMap){
			return isOnMap;
		}
		//logger.log(Level.INFO, "x="+l.getLocX()+"y="+l.getLocY()+" isOccupied: " +isTileProperty(l, "isOccupied"));
		boolean isMovable = isOnMap && isTileProperty(l, "isFloor");
		return isMovable;
	}
}
