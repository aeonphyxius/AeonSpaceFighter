package com.aeonphyxius.gamecomponents.drawable.overlay;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.data.ExplosionData;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.Overlay;
import com.aeonphyxius.gamecomponents.drawable.Explosion;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
import com.aeonphyxius.gamecomponents.manager.WeaponManager;



/**
 * PlayerDestructionOverlay Object.
 * 
 * <P>
 * Overlay shown when the player is destroyed. 
 * 
 * <P>
 * Displays all explosion animation and when finished, reset all information and restart the level
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class PlayerDestructionOverlay implements Overlay {


	private static PlayerDestructionOverlay instance = null;			// Singleton implementation
	private final int EXPLOSION_ANIMATION = 4;							//  Number of frames for each explosion	
	private ExplosionData explosionData;								// Explosion information


	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of this class
	 */
	public static PlayerDestructionOverlay getInstance() {
		if (instance == null) {
			instance = new PlayerDestructionOverlay();
		}
		return instance;
	}


	/**
	 * Creates the textures information for the explosion
	 */
	private PlayerDestructionOverlay() {
		explosionData = new ExplosionData(0, 0);
	}

	@Override
	public void resetOverlay(){
		explosionData = new ExplosionData(0.5f,0.5f);		
	}

	@Override
	public void draw(GL10 gl, int[] spriteSheet) {
		float elapsed;

		// update the sprite position
		Explosion.getInstance().update(gl, 0.4f, 0.4f, 1.0f, explosionData.x, explosionData.y, 0f);

		// draw the sprite
		Explosion.getInstance().draw(gl, spriteSheet,explosionData.animation);

		// restore Matrix transformations to its original configuration
		Explosion.getInstance().restoreMatrix(gl);

		elapsed = System.currentTimeMillis() - explosionData.elapsed;		//lastShoot;			

		if ( elapsed > Engine.SHOOT_SLEEP){ 
			explosionData.elapsed=System.currentTimeMillis();				// Add a new time stamp
			explosionData.animation++;										// Next animation
			if (explosionData.animation > EXPLOSION_ANIMATION){				// When the animation ends, reset all values, before continue
				GameStartOvelay.getInstance().resetOverlay();
				Engine.GameSatus = Engine.GAMESTATUS.START;	
				WeaponManager.getInstance().resetWeapons();
				ExplosionManager.getInstance().resetExplosions();
			}
		}
	}	
}
