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
	 * Singleton implementation of the unique instance of this class
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
		
		
		if ( elapsed > Engine.SHOOT_SLEEP){ 
			lastShoot=System.currentTimeMillis();
			MusicManager.getInstance().playSound(Engine.SOUND_FUSHIONSHOT);
			//MusicManager.getInstance().playSound(Engine.SOUND_BLASTER);
			playeFireList.add(new Weapon());			
		}
		
		if(playeFireList.size()>0){
			if (playeFireList.get(0).shootFired == false){
				playeFireList.remove(playeFireList.get(0));
			}		
			
			for(int x = 0; x < playeFireList.size(); x++  ){
	
				if (playeFireList.get(x).posY > 5.5f){ // TODO: add constant
					playeFireList.get(x).shootFired = false;
				}else if(playeFireList.get(x).shootFired){
	
					playeFireList.get(x).posY += Engine.PLAYER_BULLET_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 0f);
					gl.glTranslatef(playeFireList.get(x).posX, playeFireList.get(x).posY, 0f); // TODO: add a constant
					gl.glScalef(.4f, .4f, 0f);
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
		
					playeFireList.get(x).draw(gl,spriteSheets);
					gl.glPopMatrix();
					gl.glLoadIdentity();
				}
			}
		}
		
		if(enemyFireList.size()>0){
			if (enemyFireList.get(0).shootFired == false){
				enemyFireList.remove(enemyFireList.get(0));
			}		
			
			for(int x = 0; x < enemyFireList.size(); x++  ){
	
				if (enemyFireList.get(x).posY < 0.4f){ // TODO: add constant
					enemyFireList.get(x).shootFired = false;
				}else if(enemyFireList.get(x).shootFired){
	
					enemyFireList.get(x).posY -= Engine.PLAYER_BULLET_SPEED;
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

