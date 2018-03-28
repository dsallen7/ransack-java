package com.allen.silo.ransack.maps;

import java.io.IOException;
import java.util.logging.Logger;

import org.mini2Dx.core.di.annotation.Singleton;
import org.mini2Dx.tiled.TiledObject;
import org.mini2Dx.tiled.exception.TiledException;

import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.character.attributes.Location;
import com.allen.silo.ransack.handlers.EventType;
import com.allen.silo.ransack.handlers.MessageDataImpl;
import com.allen.silo.ransack.main.Ransack;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.MathUtility;

@Singleton
public class PlayableMap extends BasicMap {

	private static final long serialVersionUID = 1202377979271661004L;
	public static Logger logger = Logger.getLogger(PlayableMap.class.getName());
	private Location playerLocation = null;
	
	public PlayableMap(String fileName) throws TiledException, IOException{
		super(fileName);
		for (TiledObject to : getObjectGroup("Portals").getObjects()){
			if(to.getType().equals("portal")){
				int pX = Integer.parseInt(to.getProperty("targetX"));
				int pY = Integer.parseInt(to.getProperty("targetY"));
				String targetMap = to.getProperty("targetMap");
				Location portalLocation = MathUtility.getGridCoordFromScreen(to.getX(),to.getY());
				grid.getCell(portalLocation).setTargetMap(targetMap);
				grid.getCell(portalLocation).setTargetX(pX);
				grid.getCell(portalLocation).setTargetY(pY);
			}
		}
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
		if (l == null)
			return false;
		boolean isOnMap = super.isMovable(l);
		if (!isOnMap){
			return isOnMap;
		}
		if (hasTileProperty(l, "stairsUp")){
			Ransack.messageBus.broadcast(EventType.STAIRSUP.toString(), new MessageDataImpl(0, this.getName(), this.getProperty("up")));
			return true;
		}
		if (hasTileProperty(l, "stairsDown")){
			Ransack.messageBus.broadcast(EventType.STAIRSDOWN.toString(), new MessageDataImpl(0, this.getName(), this.getProperty("down")));
			return true;
		}
		if (hasTileProperty(l, "isPortal")){
			Cell c = grid.getCell(l);
			Ransack.messageBus.broadcast(EventType.PORTAL.toString(), new MessageDataImpl(0, this.getName(), c.getTargetMap(), new Location(c.getTargetX(), c.getTargetY()) ));
			return true;
		}
		//logger.log(Level.INFO, "x="+l.getLocX()+"y="+l.getLocY()+" isOccupied: " +isTileProperty(l, "isOccupied"));
		boolean isMovable = isOnMap && isTileProperty(l, "isFloor");
		return isMovable;
	}

	public Location getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Location playerLocation) {
		this.playerLocation = playerLocation;
	}
}
