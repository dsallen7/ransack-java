package com.allen.silo.ransack.display.ui;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.element.AbsoluteContainer;
import org.mini2Dx.ui.element.AbsoluteModal;
import org.mini2Dx.ui.element.AlignedModal;
import org.mini2Dx.ui.element.Image;
import org.mini2Dx.ui.element.Label;
import org.mini2Dx.ui.element.Visibility;

import com.allen.silo.ransack.character.Player;
import com.allen.silo.ransack.display.ui.listeners.RansackUiContainerListener;
import com.allen.silo.ransack.utils.Constants;
import com.allen.silo.ransack.utils.FileUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class RansackUiContainer extends UiContainer {

	private boolean isWaiting;
	
	private AlignedModal mainMenuModal;
	private AbsoluteModal dialogue;
	private AbsoluteContainer hudContainer;
	private HeadsUpDisplay HUD;
	
	public RansackUiContainer(GameContainer gc, AssetManager assetManager) throws SerializationException {
		super(gc, assetManager);
		mainMenuModal = Mdx.xml.fromXml(FileUtility.getUIAsset("startgamemodal.xml").reader(), AlignedModal.class);
        dialogue = Mdx.xml.fromXml(FileUtility.getUIAsset("dialogue.xml").reader(), AbsoluteModal.class);
        AbsoluteContainer hudContainer = Mdx.xml.fromXml(FileUtility.getUIAsset("HUD.xml").reader(), AbsoluteContainer.class);
        hudContainer.set(0, 300);
        HUD = new HeadsUpDisplay(hudContainer);
        addListener(new RansackUiContainerListener());
        //uiContainer.add(mainMenuModal);
		add(hudContainer);
		add(dialogue);
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
	}
	
	public void updateStats(Player p){
		HUD.updateStats(p);		
	}

	@Override
	public boolean keyDown(int key){
		if (key == Input.Keys.ESCAPE) {
			System.exit(0);
		}
		if (key == Input.Keys.F1) {
		}
		if (Constants.MOVE_KEYS.contains(key)){
			
		}
		return true;
	}

	/*
	 * Ransack methods
	 */

	public void openDialogue(String message, Texture image){
		dialogue.setVisibility(Visibility.VISIBLE);
		Label uiLabel = (Label) dialogue.getElementById("dialogueText");
		uiLabel.setText(message);
		Image uiImage = (Image) dialogue.getElementById("dialogueImage");
		uiImage.setTexture(image);
		setWaiting(true);
		Gdx.input.setInputProcessor(this);
	}
	
	public void closeDialogue(){
		dialogue.setVisibility(Visibility.HIDDEN);
	}
	public boolean isWaiting() {
		return isWaiting;
	}
	
	public void printLog(Graphics g, String[] logHistory){
		HUD.printLog(g, logHistory);
	}


	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}
}
