package com.aeonphyxius.gamecomponents.manager;

import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.data.ExplosionData;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.gamecomponents.drawable.Explosion;



/**
 * ExplosionManager Object.
 * 
 * <P>
 * Explosion manager , displays all explosion animations
 * 
 * <P>
 * Contains logic to display the different explosion animating textures
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class ExplosionManager{


	private static ExplosionManager instance = null;			// Singleton implementation
	private final int EXPLOSION_ANIMATION = 4;					// How many animation frames?
	private Vector <ExplosionData> explosionList;				// List containing all explosions currently on screen

	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of this class
	 */
	public static ExplosionManager getInstance() {
		if (instance == null) {
			instance = new ExplosionManager();
		}
		return instance;
	}

	/**
	 * private constructor to do not allow others instantiate this class. Empty
	 */
	private ExplosionManager() {
		explosionList = new Vector<ExplosionData>();
	}

	/**
	 * Reset the explosions list, when the player has been destroyed or the level is over
	 */
	public void resetExplosions(){
		explosionList = new Vector<ExplosionData>();
	}

	/**
	 * Add a new explosion to the explosions list. 
	 * @param x coordinate on screen to create the new explosion
	 * @param y coordinate on screen to create the new explosion
	 */
	public void addExplosion(float x, float y){
		explosionList.add(new ExplosionData(x,y));		
	}

	/**
	 * HUD draw method to display all components on screen
	 * @param gl OpenGL handler
	 */
	public void draw(GL10 gl) {
		float elapsed;

		for (int i = 0; i < explosionList.size();i++){		

			if (!explosionList.get(i).isDisabled ){

				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();							// Save Matrix before transformations
				gl.glScalef(.15f, .15f, 1f);				// Scale the original image (same % as the ships)
				gl.glTranslatef(explosionList.get(i).x+0.25f, explosionList.get(i).y, 0f);				// Position on screen
				gl.glScalef(.35f, .35f, 1f);				// Scale the original image (half more to make it even more small)
				gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
				gl.glLoadIdentity();				
				Explosion.getInstance().draw(gl, explosionList.get(i).animation);	
				gl.glPopMatrix();							// Recover previous Matrix
				gl.glLoadIdentity();

				elapsed = System.currentTimeMillis() - explosionList.get(i).elapsed;	//lastShoot;			

				if ( elapsed > Engine.SHOOT_SLEEP){ // TODO: add a constant
					explosionList.get(i).elapsed=System.currentTimeMillis();
					explosionList.get(i).animation++;
					if (explosionList.get(i).animation > EXPLOSION_ANIMATION){
						explosionList.get(i).isDisabled = true;
					}

				}
			}
		}

	}	
}
