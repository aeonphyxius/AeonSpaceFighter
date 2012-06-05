package com.aeonphyxius.gamecomponents.drawable;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.data.ExplosionData;
import com.aeonphyxius.engine.DrawableComponent;
import com.aeonphyxius.engine.Engine;



/**
 * ExplosionManager Object.
 * 
 * <P>
 * Explosion manager , displays all explosion animations
 * 
 * <P>
 * This class contains logic to display the different explosion animating them
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class PlayerDestructionOverlay implements DrawableComponent {
	

	private static PlayerDestructionOverlay instance = null;			// Singleton implementation
	private final int EXPLOSION_ANIMATION = 4;					// 
	private ExplosionData explosionData;

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
	 * private constructor to do not allow others instanciate this class. Empty
	 */
	private PlayerDestructionOverlay() {
		explosionData = new ExplosionData(0, 0);
	}

	public void resetOverlay(){
		explosionData = new ExplosionData(1f,1f);		
	}
	/**
	 * HUD draw method to display all components on screen
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		float elapsed;
			
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();													// Save Matrix before transformations
		gl.glScalef(.4f, .4f, 1f);											// Scale the original image (same % as the ships)
		gl.glTranslatef(explosionData.x, explosionData.y, 0f);				// Position on screen		
		gl.glMatrixMode(GL10.GL_TEXTURE);									// Texture Mode
		gl.glLoadIdentity();				
		Explosion.getInstance().draw(gl, spriteSheet,explosionData.animation);	
		gl.glPopMatrix();													// Recover previous Matrix
		gl.glLoadIdentity();

		elapsed = System.currentTimeMillis() - explosionData.elapsed;//lastShoot;			

		if ( elapsed > Engine.SHOOT_SLEEP){ // TODO: add a constant
			explosionData.elapsed=System.currentTimeMillis();
			explosionData.animation++;
			if (explosionData.animation > EXPLOSION_ANIMATION){
				GameStartOvelay.getInstance().resetOverlay();
				Engine.GAMESTATUS = Engine.GameSatus.START;				
			}

		}

		
		
	}	
}
