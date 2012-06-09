package com.aeonphyxius.data;

import com.aeonphyxius.engine.Engine;

/**
* LevelData Object.
* 
* <P>Data manager for level.
*  
* <P>Contains all information needed to manage each level (background, level number)
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class LevelData {
	
	private int currentLevel;
	
	private static LevelData instance = null;
	
	public static LevelData getInstance() {
		if (instance == null) {
			instance = new LevelData();
		}
		return instance;				
	}
	

	public void resetLevelData(){
		this.currentLevel = 1;
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
