package com.aeonphyxius.data;

import com.aeonphyxius.engine.Engine;


public class LevelData {
	
	private int currentLevel;
	
	private static LevelData instance = null;
	
	public static LevelData getInstance() {
		if (instance == null) {
			instance = new LevelData();
		}
		return instance;				
	}
	
	
	private LevelData(){
		this.currentLevel  = 1; 			// First level, is always level 1.
	}


	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void increaseLevel(){
		if (currentLevel < Engine.MAX_LEVEL){
			currentLevel ++;
		}
		
	}
	

}
