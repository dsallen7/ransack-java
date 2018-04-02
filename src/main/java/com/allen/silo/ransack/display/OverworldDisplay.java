package com.allen.silo.ransack.display;

import org.mini2Dx.core.serialization.SerializationException;

import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.display.ui.RansackUiContainer;
import com.allen.silo.ransack.maps.PlayableMap;

public class OverworldDisplay extends PlayableDisplay {
	
	protected Player player;
	
	public OverworldDisplay(RansackUiContainer uiContainer, PlayableMap m, Player p) throws SerializationException{
		super(uiContainer);
		setCurrentMap(m);
		setActor(p);
	}
}
