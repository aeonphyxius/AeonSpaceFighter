package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;

import com.aeonphyxius.engine.Engine;
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
	
	/**
	 * 
	 */
	public void initializeInterceptors() {
		Enemy interceptor;
		//for (int x = 0; x < Engine.TOTAL_INTERCEPTORS - 1; x++) {
		//	interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM);
		//	enemyList.add(x, interceptor);
		//}
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,0,4);
		enemyList.add(0, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,1,4);
		enemyList.add(1, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,2,4);
		enemyList.add(2, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,0,10);
		enemyList.add(3, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,1,10);
		enemyList.add(4, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,2,10);
		enemyList.add(5, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,3,10);
		enemyList.add(6, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,4,10);
		enemyList.add(7, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,5,10);
		enemyList.add(8, interceptor);
		interceptor = new Enemy(Engine.TYPE_INTERCEPTOR,Engine.ATTACK_RANDOM,1,22);
		enemyList.add(9, interceptor);
		
	}

	/**
	 * 
	 */
	public void initializeScouts() {
		/*Enemy scout;
		for (int x = Engine.TOTAL_INTERCEPTORS - 1; x < Engine.TOTAL_INTERCEPTORS + Engine.TOTAL_SCOUTS - 1; x++) {
			
			if (x >= (Engine.TOTAL_INTERCEPTORS + Engine.TOTAL_SCOUTS) / 2) {
				scout = new Enemy(Engine.TYPE_SCOUT, Engine.ATTACK_RIGHT);
			} else {
				scout = new Enemy(Engine.TYPE_SCOUT, Engine.ATTACK_LEFT);
			}
			enemyList.add(x, scout);
		}*/
	}

	/**
	 * 
	 */
	public void initializeWarships() {
		/*Enemy warship;
		for (int x = Engine.TOTAL_INTERCEPTORS + Engine.TOTAL_SCOUTS - 1; x < Engine.TOTAL_INTERCEPTORS + Engine.TOTAL_SCOUTS + Engine.TOTAL_WARSHIPS - 1; x++) {
			warship = new Enemy(Engine.TYPE_WARSHIP, Engine.ATTACK_RANDOM);
			enemyList.add(x, warship);
		}*/
	}

}
