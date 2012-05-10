package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;

import com.aeonphyxius.gamecomponents.drawable.Enemy;


public class EnemyManager {
	
	private static EnemyManager instance = null;

	protected EnemyManager() {
		enemyList = new ArrayList<Enemy>();
	}

	public static EnemyManager getInstance() {
		if (instance == null) {
			instance = new EnemyManager();
		}
		return instance;
	}
	
	private ArrayList<Enemy> enemyList;

	
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
	

}
