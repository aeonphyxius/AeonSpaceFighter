package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;

import com.aeonphyxius.gamecomponents.drawable.Enemy;

public class Squadron {
	
	private ArrayList<Enemy> enemyList;
	private int squadronPosY;
	
	
	public Squadron(ArrayList<Enemy> enemyList, int ypos){
		this.enemyList = enemyList;
		this.squadronPosY = ypos;
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
