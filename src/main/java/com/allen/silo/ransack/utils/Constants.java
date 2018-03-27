package com.allen.silo.ransack.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Input;

public interface Constants {
	
	public static final String LOCAL_ASSETS_PATH = "C:/Users/Dan/workspace/RansackMini2Dx/core/assets/";
	public static final String LOCAL_CHARSHEET_PATH = "IMG/CHAR/";
	public static final String LOCAL_MAPS_PATH = "MAPS/";
	public static final String LOCAL_SCRIPTS_PATH = "SCRIPTS/";
	public static final String LOCAL_UI_PATH = "UI/";

	public static final int windowX = 640;
	public static final int windowY = 480;
	
	public static final int editorWindowX = 640;
	public static final int editorWindowY = 480;
	
	public static final int BLOCKSIZE = 30;
	/*
	 * Size of window in tiles
	 */
	public static final int WINDOWSIZE = 10;
	
	/*
	 * Non-blocking tiles
	 */
	public static final int DUNGEON_FLOOR1 = 0;
	public static final int DUNGEON_FLOOR2 = 1;
	public static final int DUNGEON_FLOOR3 = 2;
	public static final int DUNGEON_FLOOR4 = 3;
	public static final int FORTRESS_FLOOR1 = 4;
	public static final int FORTRESS_FLOOR2 = 5;
	public static final int FORTRESS_FLOOR3 = 6;
	public static final int FORTRESS_FLOOR4 = 7;
	public static final int ROAD1 = 8;
	public static final int ROAD2 = 9;
	public static final int NS_BRIDGE = 10;
	public static final int EW_BRIDGE = 11;
	public static final int GRASS1 = 12;
	public static final int GRASS2 = 13;
	public static final int GRASS3 = 14;
	public static final int GRASS4 = 15;
	public static final int WOOD_FLOOR1 = 16;
	public static final int WOOD_FLOOR2 = 17;
	public static final int NS_OPEN_DOOR = 18;
	public static final int EW_OPEN_DOOR = 19;
	public static final int CASTLE_FLOOR1 = 20;
	public static final int CASTLE_FLOOR2 = 21;
	public static final int CASTLE_FLOOR3 = 22;
	public static final int CASTLE_FLOOR4 = 23;
	/*
	 * Blocking tiles
	 */
	public static final int BRICK1 = 24;
	public static final int BRICK2 = 25;
	// 26
	
	
	
	public static final List<Integer> MOVE_KEYS = new ArrayList<Integer>(Arrays.asList(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN));
	public static final List<Integer> WALKING_LIST = new ArrayList<Integer>(Arrays.asList(1, 9, -1, -9));
}
