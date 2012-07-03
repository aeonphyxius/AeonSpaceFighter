package com.aeonphyxius.gamecomponents.manager;

import java.util.Iterator;
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
	 */

	public void drawWeapon(GL10 gl){

		float elapsed = System.currentTimeMillis() - lastShoot;
		Weapon iterWeapon;
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
			for (Iterator<Weapon> i = playeFireList.iterator(); i.hasNext();) {				
				iterWeapon = i.next();
				// When the shot reaches the max position in screen
				if (iterWeapon.posY >  Engine.MAX_Y){
					iterWeapon.isFired = false;
				}else if(iterWeapon.isFired){
					iterWeapon.posY += Engine.PLAYER_BULLET_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 0f);
					gl.glTranslatef(iterWeapon.posX, iterWeapon.posY, 0f);
					//gl.glTranslatef(Engine.playerBankPosX+0.20f,Engine.PLAYER_POS_Y+0.5f, 0f);
					gl.glScalef(.4f, .4f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();

					iterWeapon.draw(gl);
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

			for (Iterator<Weapon> i = enemyFireList.iterator(); i.hasNext();) {				
				iterWeapon = i.next();			
				// When the shot reaches the min position in screen	
				if (iterWeapon.posY <  Engine.MIN_Y){
					iterWeapon.isFired = false;					
				}else if(iterWeapon.isFired){
					iterWeapon.posY -= Engine.ENEMY_BULLET_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 0f);
					gl.glTranslatef(iterWeapon.posX, iterWeapon.posY, 0f); 
					gl.glScalef(.4f, .4f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();		
					iterWeapon.draw(gl);
					gl.glPopMatrix();
					gl.glLoadIdentity();
				}				
			}
		}
	}
}

