package com.aeonphyxius.gamecomponents.manager;

import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.gamecomponents.drawable.Weapon;

/**
 * WeaponManager Object.
 * 
 * <P>
 * Weapon Manager component to control all weapons (player and enemies)
 * 
 * <P>
 * This class contains logic to display and fire the weapons  
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class WeaponManager {


	private static WeaponManager instance = null;	// Singleton implementation
	private long lastShoot;							// Elapsed time since last shoot
	private Vector<Weapon> playeFireList;			// list of shoots from player
	private Vector<Weapon> enemyFireList;			// shoots list from enemies


	/**
	 * Singleton implementation to manage the unique instance of this class
	 * @return unique instance of this class
	 */
	public static WeaponManager getInstance() {
		if (instance == null) {
			instance = new WeaponManager();
		}
		return instance;
	}

	/**
	 * Initialize the Vectors for weapons and first player shoot
	 */
	private WeaponManager() {
		playeFireList = new Vector<Weapon>();
		enemyFireList = new Vector<Weapon>();
		playeFireList.add(new Weapon());
		lastShoot = System.currentTimeMillis();

	}

	public Vector<Weapon> getPlayeFireList() {
		return playeFireList;
	}


	public Vector<Weapon> getEnemyFireList() {
		return enemyFireList;
	}

	/**
	 * When the player is destroyed, or the level is finished, reset all weapons
	 */
	public void resetWeapons(){
		playeFireList = new Vector<Weapon>();
		enemyFireList = new Vector<Weapon>();
	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 */
	public void addEnemyShot(float posX, float posY){
		enemyFireList.add(new Weapon(posX,posY));
	}

	/**
	 * Draw the control weapons fired 
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 */
	public void drawWeapon(GL10 gl,int[] spriteSheets){

		float elapsed = System.currentTimeMillis() - lastShoot;

		// Automatic players shooting
		if ( elapsed > Engine.SHOOT_SLEEP){ 
			lastShoot=System.currentTimeMillis();
			MusicManager.getInstance().playSound(Engine.SOUND_FUSHIONSHOT);
			playeFireList.add(new Weapon());			
		}

		// Check all player's shots and update + draw them
		if(playeFireList.size()>0){
			// If the first position is destroyed, should be removed from the list (to free resources)
			if (playeFireList.get(0).isFired == false){
				playeFireList.remove(playeFireList.get(0));
			}		

			for(int x = 0; x < playeFireList.size(); x++  ){	
				// When the shot reaches the max position in screen
				if (playeFireList.get(x).posY >  Engine.MAX_Y){
					playeFireList.get(x).isFired = false;
				}else if(playeFireList.get(x).isFired){

					playeFireList.get(x).posY += Engine.PLAYER_BULLET_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 0f);
					gl.glTranslatef(playeFireList.get(x).posX, playeFireList.get(x).posY, 0f); 
					gl.glScalef(.4f, .4f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();

					playeFireList.get(x).draw(gl,spriteSheets);
					gl.glPopMatrix();
					gl.glLoadIdentity();
				}
			}
		}

		// Check all enemies shots and update + draw them
		if(enemyFireList.size()>0){
			// If the first position is destroyed, should be removed from the list (to free resources)
			if (enemyFireList.get(0).isFired == false){
				enemyFireList.remove(enemyFireList.get(0));
			}		

			for(int x = 0; x < enemyFireList.size(); x++  ){
				// When the shot reaches the min position in screen	
				if (enemyFireList.get(x).posY <  Engine.MIN_Y){
					enemyFireList.get(x).isFired = false;
				}else if(enemyFireList.get(x).isFired){

					enemyFireList.get(x).posY -= Engine.ENEMY_BULLET_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 0f);
					gl.glTranslatef(enemyFireList.get(x).posX, enemyFireList.get(x).posY, 0f); 
					gl.glScalef(.4f, .4f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();		
					enemyFireList.get(x).draw(gl,spriteSheets);
					gl.glPopMatrix();
					gl.glLoadIdentity();
				}
			}
		}
	}
}

