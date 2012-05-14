package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;

import com.aeonphyxius.gamecomponents.drawable.Enemy;

public class Squadron {
	
	private ArrayList<Enemy> enemyList;
	private int squadronPosY;
	private int squadronEnemyType;	
	
	
	public Squadron(ArrayList<Enemy> enemyList, int ypos, int squadronEnemyType){
		this.enemyList = enemyList;		
		this.squadronPosY = ypos;
		this.squadronEnemyType = squadronEnemyType;
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


	public int getSquadronPosY() {
		return squadronPosY;
	}


	public void setSquadronPosY(int squadronPosY) {
		this.squadronPosY = squadronPosY;
	}
	
	

}
