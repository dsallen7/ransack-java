package com.allen.silo.ransack.utils;

import java.util.Random;

public class RandomUtility {
	
	public static Random random = new Random();
	
	public static Random getRandom(){
		return random;
	}
	
	public static int randRange(int begin, int end){
		return random.nextInt(end + begin) + begin;
	}
}
