package com.aeonphyxius.gamecomponents.drawable;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.engine.TextureRegion;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;

/**
* Enemy Object.
* 
* <P> Class to manage an enemy. Contains all information about it, manages status, drawing procedure, etc
*  
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class Enemy extends EngineGL{
	
	public float posY = 0f;							// Enemy X position
	public float posX = 0f;							// Enemy Y position
	public float posT = 0f;							// Enemy T position
	public boolean isShooting;						// is shooting this enemy (has the capability of)
	public float incrementXToTarget = 0f;			//
	public float incrementYToTarget = 0f;			//
	public int attackDirection = 0;					//
	public boolean isDestroyed = false;				// is it destroyed?
	private int damage;								// Enemy's damage	
	public int enemyType;							// Enemy0s type	
	public boolean isLockedOn = false;				// 
	public float lockOnPosX = 0f;					//
	public float lockOnPosY = 0f;					//
	private TextureRegion enemyTexture;				// Texture region containing the enemy texture



	/**
	 * Builds a default enemy, at the given coordinates, type and direction
	 * @param type
	 * @param direction
	 * @param x
	 * @param y
	 */
	public Enemy (int type,int direction,int x, int y){
		enemyType = type;
		attackDirection = direction;		
		posX = x;
		posY = y;
		
		switch (enemyType) { // Depending on the enemy type, will use a different texture
		case Engine.TYPE_INTERCEPTOR:
			posT = Engine.INTERCEPTOR_SPEED;
			enemyTexture = new TextureRegion( new float[] { 0.027f, 0.548f, 0.183f, 0.548f, 0.183f, 0.713f, 0.027f, 0.713f, });
			if (Math.random()< 0.3){
				isShooting =true;
			}else{
				isShooting =false;
			}
			break;
		case Engine.TYPE_SCOUT:
			posT = Engine.SCOUT_SPEED;
			enemyTexture = new TextureRegion(new float[] { 0.003f, 0.738f,	0.252f, 0.738f, 0.252f, 0.984f, 0.003f, 0.984f, });
			if (Math.random()< 0.3){
				isShooting =true;
			}else{
				isShooting =false;
			}
			break;
		case Engine.TYPE_WARSHIP:
			posT = Engine.WARSHIP_SPEED;
			enemyTexture = new TextureRegion(new float[] { 0.029f, 0.011f, 0.215f, 0.011f, 0.215f, 0.258f, 0.029f, 0.258f, });
			if (Math.random()< 0.3){
				isShooting =true;
			}else{
				isShooting =false;
			}
			break;
		}
	}

	/**
	 * Apply damage on this enemy, depending of the level, and plays a sound + explosion
	 */
	public void applyDamage() {
		damage++;
		switch (enemyType) { // Depending on the enemy type, will apply different shield rates
		case Engine.TYPE_INTERCEPTOR:
			if (damage >= Engine.INTERCEPTOR_SHIELDS) {
				isDestroyed = true;
				ExplosionManager.getInstance().addExplosion(this.posX,this.posY);
				MusicManager.getInstance().playSound(Engine.SOUND_EXPLOSION_ENEMY);
			}
			break;
		case Engine.TYPE_SCOUT:
			if (damage >= Engine.SCOUT_SHIELDS) {
				isDestroyed = true;
				ExplosionManager.getInstance().addExplosion(this.posX,this.posY);
				MusicManager.getInstance().playSound(Engine.SOUND_EXPLOSION_ENEMY);
			}
			break;
		case Engine.TYPE_WARSHIP:
			if (damage >= Engine.WARSHIP_SHIELDS) {
				isDestroyed = true;
				ExplosionManager.getInstance().addExplosion(this.posX,this.posY);
				MusicManager.getInstance().playSound(Engine.SOUND_EXPLOSION_ENEMY);
			}
			break;
		}
	}

	/**
	 * Small IA algorithm
	 * @return next scout X position
	 */
	public float getNextScoutX() {
		if (attackDirection == Engine.ATTACK_LEFT) {
			return (float) ((Engine.BEZIER_X_4 * (posT * posT * posT))
					+ (Engine.BEZIER_X_3 * 3 * (posT * posT) * (1 - posT))
					+ (Engine.BEZIER_X_2 * 3 * posT * ((1 - posT) * (1 - posT))) + (Engine.BEZIER_X_1 * ((1 - posT)
					* (1 - posT) * (1 - posT))));
		} else {
			return (float) ((Engine.BEZIER_X_1 * (posT * posT * posT))
					+ (Engine.BEZIER_X_2 * 3 * (posT * posT) * (1 - posT))
					+ (Engine.BEZIER_X_3 * 3 * posT * ((1 - posT) * (1 - posT))) + (Engine.BEZIER_X_4 * ((1 - posT)
					* (1 - posT) * (1 - posT))));
		}

	}

	/**
	 * Small IA algorithm 
	 * @return next scout Y position
	 */
	public float getNextScoutY() {
		return (float) ((Engine.BEZIER_Y_1 * (posT * posT * posT))
				+ (Engine.BEZIER_Y_2 * 3 * (posT * posT) * (1 - posT))
				+ (Engine.BEZIER_Y_3 * 3 * posT * ((1 - posT) * (1 - posT))) + (Engine.BEZIER_Y_4 * ((1 - posT)
				* (1 - posT) * (1 - posT))));
	}

	/**
	 * Drawing the enemy
	 * @param gl
	 * @param spriteSheet
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		super.draw(gl, spriteSheet, 0, enemyTexture);
	}

}
