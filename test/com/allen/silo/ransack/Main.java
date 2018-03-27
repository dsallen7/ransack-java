package com.allen.silo.ransack;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.allen.silo.ransack.main.Ransack;
import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

public class Main {
	public static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		DesktopMini2DxConfig cfg = new DesktopMini2DxConfig(Ransack.GAME_IDENTIFIER);
		cfg.title = "Ransack";
		cfg.width = 640;
		cfg.height = 480;
		cfg.vSyncEnabled = true;
		logger.log(Level.INFO, "Kicking off game...");
		new DesktopMini2DxGame(new Ransack(), cfg);
	}
}
