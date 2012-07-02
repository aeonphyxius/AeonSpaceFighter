package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.gamecomponents.drawable.Enemy;

/**
 * BackGroundManager Object.
 * 
 * <P>Manager for all game background layers.
 *  
 * <P>This class contains logic manage the game's background layers. 
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class Squadron {

	private ArrayList<Enemy> enemyList;						// Enemies list contained in this squadron
	private int squadronEnemyType;							// Squadron type
	private int squadronNumEnemies;							// Number of enemies in this squadron
	private int squadronEnemiesDestroyed;					// is destroyed this squadron
	private float squadronYPos;


	/**
	 * Creates a new squadron with the given  data
	 * @param enemyList 
	 * @param squadronEnemyType 
	 * @param squadronNumEnemies
	 * @param squadronEnemiesDestroyed
	 */
	public Squadron(ArrayList<Enemy> enemyList,  int squadronEnemyType, int squadronNumEnemies,int squadronEnemiesDestroyed, int squadronYPos){
		this.enemyList = enemyList;		

		this.squadronEnemyType = squadronEnemyType;
		this.squadronNumEnemies = squadronNumEnemies;
		this.squadronEnemiesDestroyed = squadronEnemiesDestroyed;	
		this.squadronYPos = squadronYPos;		
	}

	public boolean isVisible (){
		if (squadronYPos-Engine.yScroll >= Engine.SQUADRON_START_Y){
			return false;
		}else{
			return true;
		}		
	}

	public boolean isDestroyed(){
		return squadronNumEnemies <= squadronEnemiesDestroyed;
	}

	public void increaseEnemiesDestroyed(){
		squadronEnemiesDestroyed++;
	}
	public int getSquadronNumEnemies() {
		return squadronNumEnemies;
	}


	public void setSquadronNumEnemies(int squadronNumEnemies) {
		this.squadronNumEnemies = squadronNumEnemies;
	}



	public int getSquadronEnemiesDestroyed() {
		return squadronEnemiesDestroyed;
	}



	public void setSquadronEnemiesDestroyed(int squadronEnemiesDestroyed) {
		this.squadronEnemiesDestroyed = squadronEnemiesDestroyed;
	}



	public int getSquadronEnemyType() {
		return squadronEnemyType;
	}

	public void setSquadronEnemyType(int squadronEnemyType) {
		this.squadronEnemyType = squadronEnemyType;
	}


	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}


	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
}
