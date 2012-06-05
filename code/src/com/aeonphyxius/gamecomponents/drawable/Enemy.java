package com.aeonphyxius.gamecomponents.drawable;


import java.util.Random;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.engine.TextureRegion;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;

public class Enemy extends EngineGL{

	private static final int TYPE_INTERCEPTOR = 1;
	private static final int TYPE_SCOUT = 2;
	private static final int TYPE_WARSHIP = 3;
	private static final int ATTACK_RANDOM = 0;	
	private static final int ATTACK_RIGHT = 1;
	private static final int ATTACK_LEFT = 2;
	
	public float posY = 0f;
	public float posX = 0f;
	public float posT = 0f;
	public boolean isShooting;
	public float incrementXToTarget = 0f;
	public float incrementYToTarget = 0f;
	public int attackDirection = 0;
	public boolean isDestroyed = false;
	private int damage = 0;

	public int enemyType = 0;

	public boolean isLockedOn = false;
	public float lockOnPosX = 0f;
	public float lockOnPosY = 0f;

	private Random randomPos = new Random();
	
	private TextureRegion enemyTexture;	// Texture region containing the enemy texture


	public void applyDamage() {
		damage++;
		switch (enemyType) {
		case TYPE_INTERCEPTOR:
			if (damage >= Engine.INTERCEPTOR_SHIELDS) {
				isDestroyed = true;
				ExplosionManager.getInstance().addExplosion(this.posX,this.posY);
				MusicManager.getInstance().playSound(Engine.SOUND_EXPLOSION);
			}
			break;
		case TYPE_SCOUT:
			if (damage >= Engine.SCOUT_SHIELDS) {
				isDestroyed = true;
				ExplosionManager.getInstance().addExplosion(this.posX,this.posY);
				MusicManager.getInstance().playSound(Engine.SOUND_EXPLOSION);
			}
			break;
		case TYPE_WARSHIP:
			if (damage >= Engine.WARSHIP_SHIELDS) {
				isDestroyed = true;
				ExplosionManager.getInstance().addExplosion(this.posX,this.posY);
				MusicManager.getInstance().playSound(Engine.SOUND_EXPLOSION);
			}
			break;
		}
	}
	
	public Enemy (int type,int direction,int x, int y){
		enemyType = type;
		attackDirection = direction;		
		posX = x;
		posY = y;
		
		
		
		
		switch (enemyType) {
		case TYPE_INTERCEPTOR:
			posT = Engine.INTERCEPTOR_SPEED;
			enemyTexture = new TextureRegion( new float[] { 0.027f, 0.548f, 0.183f, 0.548f, 0.183f, 0.713f, 0.027f, 0.713f, });
			if (Math.random()< 0.3){
				isShooting =true;
			}else{
				isShooting =false;
			}
			break;
		case TYPE_SCOUT:
			posT = Engine.SCOUT_SPEED;
			enemyTexture = new TextureRegion(new float[] { 0.003f, 0.738f,	0.252f, 0.738f, 0.252f, 0.984f, 0.003f, 0.984f, });
			if (Math.random()< 0.3){
				isShooting =true;
			}else{
				isShooting =false;
			}
			break;
		case TYPE_WARSHIP:
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

	public Enemy(int type, int direction) {
		enemyType = type;
		attackDirection = direction;
		posY = (randomPos.nextFloat() * 4) + 4;
		switch (attackDirection) {
		case ATTACK_LEFT:
			posX = 0;
			break;
		case ATTACK_RANDOM:
			posX = randomPos.nextFloat() * 3;
			break;
		case ATTACK_RIGHT:
			posX = 3;
			break;
		}
		posT = Engine.SCOUT_SPEED;

	}

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

	public float getNextScoutY() {
		return (float) ((Engine.BEZIER_Y_1 * (posT * posT * posT))
				+ (Engine.BEZIER_Y_2 * 3 * (posT * posT) * (1 - posT))
				+ (Engine.BEZIER_Y_3 * 3 * posT * ((1 - posT) * (1 - posT))) + (Engine.BEZIER_Y_4 * ((1 - posT)
				* (1 - posT) * (1 - posT))));
	}

	public void draw(GL10 gl, int[] spriteSheet) {
		super.draw(gl, spriteSheet, 0, enemyTexture);
	}

}
