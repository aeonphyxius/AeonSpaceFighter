package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;

import com.aeonphyxius.gamecomponents.drawable.Enemy;

public class Squadron {
	
	private ArrayList<Enemy> enemyList;	
	private int squadronEnemyType;	
	private int squadronNumEnemies;
	private int squadronEnemiesDestroyed;
	
	
	public Squadron(ArrayList<Enemy> enemyList,  int squadronEnemyType, int squadronNumEnemies,int squadronEnemiesDestroyed){
		this.enemyList = enemyList;		
		
		this.squadronEnemyType = squadronEnemyType;
		this.squadronNumEnemies = squadronNumEnemies;
		this.squadronEnemiesDestroyed = squadronEnemiesDestroyed;		
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
