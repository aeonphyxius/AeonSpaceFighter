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
		boolean isOverlaping = false;
		switch (enemy.enemyType) {
			case Engine.TYPE_INTERCEPTOR:
				isOverlaping=ovelapsInterceptorWeapon(enemy, weapon);
				break;
			case Engine.TYPE_SCOUT:
				isOverlaping = ovelapsScoutWeapon(enemy, weapon);
				break;
		}
		return isOverlaping;
	}
	
	/**
	 * 
	 * @param enemy
	 * @param weapon
	 * @return
	 */
	private boolean ovelapsInterceptorWeapon(Enemy enemy, Weapon weapon){
		boolean isOverlapingTop,isOverlapingBottom;
		// Interceptor Bottom Bounding Box			

		isOverlapingTop=overlaps(enemy.posX + 0.30f,enemy.posY,enemy.posX + 0.60f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
					weapon.posX,weapon.posY,weapon.posX+0.3f,weapon.posY+0.3f); // minX2, minY2, maxX2, maxY2
					
		// Interceptor Top Bounding Box
		isOverlapingBottom=overlaps(enemy.posX ,enemy.posY + 0.3f, enemy.posX + 0.90f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				weapon.posX,weapon.posY,weapon.posX+0.3f,weapon.posY+0.3f); // minX2, minY2, maxX2, maxY2
				
		return isOverlapingTop || isOverlapingBottom ;
	}

	
	private boolean ovelapsScoutWeapon(Enemy enemy, Weapon weapon){
		boolean isOverlapingTop,isOverlapingBottom;
		// Scout Bottom Bounding Box			
		isOverlapingTop=overlaps(enemy.posX ,enemy.posY,enemy.posX + 1.0f,enemy.posY + 0.45f, // minX1, minY1, maxX1, maxY1
					weapon.posX,weapon.posY,weapon.posX+0.3f,weapon.posY+0.3f); // minX2, minY2, maxX2, maxY2
					
		// Scout Top Bounding Box
		isOverlapingBottom=overlaps(enemy.posX+0.25f ,enemy.posY + 0.45f, enemy.posX + 0.7f,enemy.posY + 0.7f, // minX1, minY1, maxX1, maxY1
				weapon.posX,weapon.posY,weapon.posX+0.3f,weapon.posY+0.3f); // minX2, minY2, maxX2, maxY2
				
		return isOverlapingTop || isOverlapingBottom;
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
		boolean isOverlapingTop,isOverlapingBottom;
		
		// Bottom Bounding Box
		isOverlapingBottom = overlaps ( weapon.posX,weapon.posY, weapon.posX + 0.30f,weapon.posY + 0.30f,
				Engine.playerBankPosX + 0.1f,Engine.PLAYER_POS_Y+0.1f,Engine.playerBankPosX + 0.90f,Engine.PLAYER_POS_Y + 0.5f);//minX2, minY2, maxX2, maxY2
	
		// Player Top Bounding Box		
		isOverlapingTop = overlaps (weapon.posX,weapon.posY, weapon.posX + 0.30f,weapon.posY + 0.30f, 
				 Engine.playerBankPosX+0.20f,Engine.PLAYER_POS_Y+0.5f,Engine.playerBankPosX+0.7f,Engine.PLAYER_POS_Y + 0.9f);//minX2, minY2, maxX2, maxY2


		return isOverlapingBottom || isOverlapingTop ;
	}
	
	/**
	 * 
	 * @param weapon
	 * @return
	 */
	public boolean overlapsPlayer(Enemy enemy) {
		boolean isOverlaping = false;
		
		switch (enemy.enemyType) {
		case Engine.TYPE_INTERCEPTOR:
			isOverlaping=ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_SCOUT:
			isOverlaping=ovelapsScout(enemy);
			break;
		case Engine.TYPE_WARSHIP:
			//isOverlaping=ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_FINAL1:
			//isOverlaping=ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_FINAL2:
			//isOverlaping=ovelapsInterceptor(enemy);
			break;
		case Engine.TYPE_FINAL3:
			//isOverlaping=ovelapsInterceptor(enemy);
			break;
		}
		
		return isOverlaping;
	}
	
	/**
	 * 
	 * @param enemy
	 * @return
	 */
	public boolean ovelapsInterceptor(Enemy enemy){
		boolean isOverlapingBottomBottom=false,isOverlapingBottomTop=false,isOverlapingTopBottom=false,isOverlapingTopTop  = false;
		
		// Player bottom bounding box <--> enemy bottom bounding box 
		isOverlapingBottomBottom=overlaps (enemy.posX + 0.3f,enemy.posY,enemy.posX + 0.6f,enemy.posY + 0.6f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX + 0.1f,Engine.PLAYER_POS_Y+0.1f,Engine.playerBankPosX + 0.90f,Engine.PLAYER_POS_Y + 0.5f);//minX2, minY2, maxX2, maxY2
		
		// Player bottom bounding box <--> enemy top bounding box
		isOverlapingBottomTop=overlaps (enemy.posX ,enemy.posY + 0.3f, enemy.posX + 0.90f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX + 0.1f,Engine.PLAYER_POS_Y+0.1f,Engine.playerBankPosX + 0.90f,Engine.PLAYER_POS_Y + 0.5f);//minX2, minY2, maxX2, maxY2
		
		
		// Player top bounding box <--> enemy bottom bounding box 
		isOverlapingTopBottom=overlaps (enemy.posX + 0.30f,enemy.posY,enemy.posX + 0.60f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.20f,Engine.PLAYER_POS_Y+0.5f,Engine.playerBankPosX+0.7f,Engine.PLAYER_POS_Y + 0.9f);//minX2, minY2, maxX2, maxY2
		
		// Player top bounding box <--> enemy top bounding box
		isOverlapingTopTop=overlaps (enemy.posX ,enemy.posY + 0.3f, enemy.posX + 0.90f,enemy.posY + 0.60f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.20f,Engine.PLAYER_POS_Y+0.5f,Engine.playerBankPosX+0.7f,Engine.PLAYER_POS_Y + 0.9f);//minX2, minY2, maxX2, maxY2
		
		return isOverlapingBottomBottom || isOverlapingBottomTop || isOverlapingTopBottom ||isOverlapingTopTop;
	}
	
	/**
	 * 
	 * @param enemy
	 * @return
	 */
	public boolean ovelapsScout(Enemy enemy){
		boolean isOverlaping = false;
		
		// Player bottom bounding box <--> enemy bottom bounding box 
		isOverlaping=overlaps (enemy.posX ,enemy.posY,enemy.posX + 1.0f,enemy.posY + 0.45f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX + 0.1f,Engine.PLAYER_POS_Y+0.1f,Engine.playerBankPosX + 0.90f,Engine.PLAYER_POS_Y + 0.5f);//minX2, minY2, maxX2, maxY2
		
		// Player bottom bounding box <--> enemy top bounding box
		isOverlaping=overlaps (enemy.posX+0.25f ,enemy.posY + 0.45f, enemy.posX + 0.7f,enemy.posY + 0.7f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX + 0.1f,Engine.PLAYER_POS_Y+0.1f,Engine.playerBankPosX + 0.90f,Engine.PLAYER_POS_Y + 0.5f);//minX2, minY2, maxX2, maxY2
		
		
		// Player top bounding box <--> enemy bottom bounding box 
		isOverlaping=overlaps (enemy.posX ,enemy.posY,enemy.posX + 1.0f,enemy.posY + 0.45f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.20f,Engine.PLAYER_POS_Y+0.5f,Engine.playerBankPosX+0.7f,Engine.PLAYER_POS_Y + 0.9f);//minX2, minY2, maxX2, maxY2
		
		// Player top bounding box <--> enemy top bounding box
		isOverlaping=overlaps (enemy.posX+0.25f ,enemy.posY + 0.45f, enemy.posX + 0.7f,enemy.posY + 0.7f, // minX1, minY1, maxX1, maxY1
				Engine.playerBankPosX+0.20f,Engine.PLAYER_POS_Y+0.5f,Engine.playerBankPosX+0.7f,Engine.PLAYER_POS_Y + 0.9f);//minX2, minY2, maxX2, maxY2
		
		return isOverlaping;
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
