package com.allen.silo.ransack.display;

import java.util.logging.Logger;

import org.mini2Dx.core.serialization.SerializationException;

import com.allen.silo.ransack.display.ui.RansackUiContainer;

public abstract class BaseDisplay{
	public static Logger logger = Logger.getLogger(BaseDisplay.class.getName());
	private Spritesheet spritesheet;
	
	protected RansackUiContainer uiContainer;
		
	public BaseDisplay(RansackUiContainer uiContainer) throws SerializationException{
		
		this.uiContainer = uiContainer;
	}

	public void createUI() throws SerializationException{
	}
	
	/*
	 * Ransack methods
	 */
	
	public void update(float delta){
	    uiContainer.update(delta);
	}
}
