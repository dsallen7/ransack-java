package com.allen.silo.ransack.display.ui.listeners;

import java.util.logging.Logger;

import org.mini2Dx.core.controller.ControllerType;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.ui.InputSource;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.element.UiElement;
import org.mini2Dx.ui.layout.ScreenSize;
import org.mini2Dx.ui.listener.UiContainerListenerAdapter;

import com.allen.silo.ransack.display.ui.RansackUiContainer;
import com.allen.silo.ransack.handlers.EventType;
import com.allen.silo.ransack.main.Ransack;

public class RansackUiContainerListener extends UiContainerListenerAdapter  {
	public static Logger logger = Logger.getLogger(RansackUiContainerListener.class.getName());
	
	@Override
	public void onElementAction(UiContainer uiContainer, UiElement uiElement) {
		((RansackUiContainer)uiContainer).setWaiting(false);
		switch(uiElement.getId()){
		case "dialogueOkButton":
			Ransack.eventMessageBus.broadcast(EventType.CLOSEDIALOGUE.toString());
			((RansackUiContainer)uiContainer).closeDialogue();
		}
	}
	
	@Override
	public void onScreenSizeChanged(ScreenSize arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerTypeChanged(UiContainer arg0, ControllerType arg1, ControllerType arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputSourceChanged(UiContainer arg0, InputSource arg1, InputSource arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInterpolate(UiContainer arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postRender(UiContainer arg0, Graphics arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postUpdate(UiContainer arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preInterpolate(UiContainer arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preRender(UiContainer arg0, Graphics arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preUpdate(UiContainer arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}

}
