package com.aeonphyxius.engine;

import com.aeonphyxius.gamecomponents.drawable.Enemy;
import com.aeonphyxius.gamecomponents.drawable.Weapon;

/**
 * EngineGL Object.
 * 
 * <P>All OpenGL related rendering operations .
 *  
 * <P>This class contains logic to display enemies, player and firepower. 
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */


public class BoundingBox {

	public int minX;
	public int minY;
	public int maxX;
	public int maxY;	
	private static BoundingBox instance = null;

	/**
	 * Singleton pattern implementation
	 * @return the unique instance of the Music Manager
	 */
	public static BoundingBox getInstance() {
		if (instance == null) {
			instance = new BoundingBox();
		}
		return instance;
	}

	/**
	 * 
	 */
	public BoundingBox() {

		this.minX = 0;
		this.minY = 0;
		this.maxX = 0;
		this.maxY = 0;
	}

	/**
	 * 
	 * @param bbox
	 */
	public BoundingBox(BoundingBox bbox) {

		this.minX = bbox.minX;
		this.minY = bbox.minY;
		this.maxX = bbox.maxX;
		this.maxY = bbox.maxY;
	}

	/**
	 * 
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 */
	public BoundingBox(int minX, int maxX, int minY, int maxY) {

		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * Overlaps the given bounding box within the current box
	 * @param box
	 * @return
	 */
	public boolean overlaps(BoundingBox box) {

		return overlaps(box.minX, box.minY, box.maxX, box.maxY);
	}

	/**
	 * Overlpas the given boxes
	 * @param minX1
	 * @param minY1
	 * @param maxX1
	 * @param maxY1
	 * @param minX2
	 * @param minY2
	 * @param maxX2
	 * @param maxY2
	 * @return
	 */
	private boolean overlaps(float minX1, float minY1, float maxX1, float maxY1,float minX2, float minY2, float maxX2, float maxY2) {            
		if(maxX1 <= minX2 || minX1 >= maxX2)
			return false;

		if(maxY1 <= minY2 || minY1 >= maxY2)
			return false;

		return true;
	}
	
	public boolean overlapsEnemy(Enemy enemy, Weapon weapon){
		
		switch (enemy.enemyType) {
			case Engine.TYPE_INTERCEPTOR:
				ovelapsInterceptorWeapon(enemy, weapon);
				break;
		}
		return true;
	}
	
	
	private boolean ovelapsInterceptorWeapon(Enemy enemy, Weapon weapon){
		
		// Interceptor Bottom Bounding Box
		if (weapon.posX +0.30f <= enemy.posX+0.30f 	|| weapon.posX >= enemy.posX + 0.06f) // TODO: Add constants
			return false;
		if (weapon.posY +0.30f <= enemy.posY 		|| weapon.posY >= enemy.posY + 0.30f)
			return false;
		
		
		// Interceptor Top Bounding Box
		if (weapon.posX +0.30f <= enemy.posX  		 || weapon.posX >= enemy.posX + 0.90f)
			return false;
		if (weapon.posY -0.30f <= enemy.posY + 0.30f || weapon.posY >= enemy.posY + 0.60f)
			return false;
		
		

		return true;
	}
			
			
	/**
	 * Overlpas the given box within the curren box
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 * @return
	 */
	public boolean overlaps(int minX, int minY, int maxX, int maxY) {

		if (maxX <= this.minX || minX >= this.maxX)
			return false;

		if (maxY <= this.minY || minY >= this.maxY)
			return false;

		return true;
	}
	

	/**
	 * 
	 * @param weapon
	 * @return
	 */
	public boolean overlapsPlayer(Weapon weapon) {
		//tempWeapon.posX,tempWeapon.posY-0.3f,tempWeapon.posX+0.3f,tempWeapon.posY
		
		// Player Bottom Bounding Box
		if (weapon.posX + 0.30f <= Engine.playerBankPosX + 0.15f || weapon.posX >= Engine.playerBankPosX + 0.80f) // TODO: Add constants
			return false;
		if (weapon.posY - 0.30f <= Engine.PLAYER_POS_Y || weapon.posY >= Engine.PLAYER_POS_Y + 0.60f)
			return false;
		
		
		// Player Top Bounding Box
		if (weapon.posX + 0.30f <= Engine.playerBankPosX+0.35f || weapon.posX >= Engine.playerBankPosX+0.60f)
			return false;
		if (weapon.posY - 0.30f <= Engine.PLAYER_POS_Y+0.60f || weapon.posY >= Engine.PLAYER_POS_Y + 0.90f)
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * @param weapon
	 * @return
	 */
	public boolean overlapsPlayer(Enemy enemy) {
		
		switch (enemy.enemyType) {
		case Engine.TYPE_INTERCEPTOR:
			ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_SCOUT:
			ovelapsScout(enemy);
			break;
		case Engine.TYPE_WARSHIP:
			ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_FINAL1:
			ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_FINAL2:
			ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_FINAL3:
			ovelapsInterceptor(enemy);
			break;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param enemy
	 * @return
	 */
	public boolean ovelapsInterceptor(Enemy enemy){
		//(float minX1, float minY1, float maxX1, float maxY1,float minX2, float minY2, float maxX2, float maxY2) {
		
		// Player bottom bounding box <--> enemy bottom bounding box 
		overlaps (enemy.posX + 0.30f,enemy.posY,enemy.posX + 0.60f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.15f,Engine.PLAYER_POS_Y+0.2f,Engine.playerBankPosX+0.80f,Engine.PLAYER_POS_Y + 0.60f ); //minX2, minY2, maxX2, maxY2
		
		// Player bottom bounding box <--> enemy top bounding box
		overlaps (enemy.posX ,enemy.posY + 0.3f, enemy.posX + 0.90f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.15f,Engine.PLAYER_POS_Y+0.2f,Engine.playerBankPosX+0.80f,Engine.PLAYER_POS_Y + 0.60f );	//minX2, minY2, maxX2, maxY2
		
		
		// Player top bounding box <--> enemy bottom bounding box 
		overlaps (enemy.posX + 0.30f,enemy.posY,enemy.posX + 0.60f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.35f,Engine.PLAYER_POS_Y+0.6f,Engine.playerBankPosX+0.60f,Engine.PLAYER_POS_Y + 0.90f ); //minX2, minY2, maxX2, maxY2
		
		// Player top bounding box <--> enemy top bounding box
		overlaps (enemy.posX ,enemy.posY + 0.3f, enemy.posX + 0.90f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.35f,Engine.PLAYER_POS_Y+0.6f,Engine.playerBankPosX+0.60f,Engine.PLAYER_POS_Y + 0.90f ); //minX2, minY2, maxX2, maxY2
		
		return true;
	}
	
	/**
	 * 
	 * @param enemy
	 * @return
	 */
	public boolean ovelapsScout(Enemy enemy){
		//(float minX1, float minY1, float maxX1, float maxY1,float minX2, float minY2, float maxX2, float maxY2) {
		
		// Player bottom bounding box <--> enemy bottom bounding box 
		overlaps (enemy.posX + 0.30f,enemy.posY,enemy.posX + 0.60f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.15f,Engine.PLAYER_POS_Y+0.2f,Engine.playerBankPosX+0.80f,Engine.PLAYER_POS_Y + 0.60f ); //minX2, minY2, maxX2, maxY2
		
		// Player bottom bounding box <--> enemy top bounding box
		overlaps (enemy.posX ,enemy.posY + 0.3f, enemy.posX + 0.90f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.15f,Engine.PLAYER_POS_Y+0.2f,Engine.playerBankPosX+0.80f,Engine.PLAYER_POS_Y + 0.60f );	//minX2, minY2, maxX2, maxY2
		
		
		// Player top bounding box <--> enemy bottom bounding box 
		overlaps (enemy.posX + 0.30f,enemy.posY,enemy.posX + 0.60f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.35f,Engine.PLAYER_POS_Y+0.6f,Engine.playerBankPosX+0.60f,Engine.PLAYER_POS_Y + 0.90f ); //minX2, minY2, maxX2, maxY2
		
		// Player top bounding box <--> enemy top bounding box
		overlaps (enemy.posX ,enemy.posY + 0.3f, enemy.posX + 0.90f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.35f,Engine.PLAYER_POS_Y+0.6f,Engine.playerBankPosX+0.60f,Engine.PLAYER_POS_Y + 0.90f ); //minX2, minY2, maxX2, maxY2
		
		return true;
	}
	
	
	

	/**
	 * Is included given box coordinates in the current bounding box
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean isIncludedIn(int x1, int y1, int x2, int y2) {

		if (this.minX < x1 || this.maxX > x2)
			return false;

		if (this.minY < y1 || this.maxY > y2)
			return false;

		return true;
	}

	/**
	 * Are both the same bounding box
	 */
	public boolean equals(Object object) {

		if (object instanceof BoundingBox) {
			BoundingBox bbox = (BoundingBox) object;
			return (this.minX == bbox.minX) && (this.maxX == bbox.maxX)
					&& (this.minY == bbox.minY) && (this.maxY == bbox.maxY);
		}
		return false;
	}

}
