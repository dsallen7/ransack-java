package com.allen.silo.ransack;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.allen.silo.ransack.editor.Editor;
import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

public class RunEditor {
	public static Logger logger = Logger.getLogger(RunEditor.class.getName());
	
	public static void main(String[] args) throws Exception {
		DesktopMini2DxConfig cfg = new DesktopMini2DxConfig(Editor.GAME_IDENTIFIER);
		cfg.title = "RansackEditor";
		cfg.width = 640;
		cfg.height = 480;
		cfg.vSyncEnabled = true;
		logger.log(Level.INFO, "Kicking off game...");
		new DesktopMini2DxGame(new Editor(), cfg);
	}
}
