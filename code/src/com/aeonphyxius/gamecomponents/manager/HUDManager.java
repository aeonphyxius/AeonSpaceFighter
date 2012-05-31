package com.aeonphyxius.gamecomponents.manager;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.DrawableComponent;
import com.aeonphyxius.gamecomponents.drawable.HUDLives;
import com.aeonphyxius.gamecomponents.drawable.HUDShield;
import com.aeonphyxius.gamecomponents.drawable.Player;



public class HUDManager implements DrawableComponent {
	

	private static HUDManager instance = null;

	public static HUDManager getInstance() {
		if (instance == null) {
			instance = new HUDManager();
		}
		return instance;
	}
	
	
	
	/**
	 * 
	 */
	private HUDManager() {		
		
	}

	/**
	 * 
	 * @param gl
	 * @param spriteSheet
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		drawLives  (gl,spriteSheet);
		drawShields(gl,spriteSheet);
	}
	
	/**
	 * 
	 * @param gl
	 * @param spriteSheet
	 */
	private void drawShields (GL10 gl, int[] spriteSheet) {
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);		
		// Save Matrix before conversions
		gl.glLoadIdentity();
		gl.glPushMatrix();
		// Transformations to display the player
		gl.glScalef(.05f, .05f, 1f);
				
		gl.glTranslatef(10.0f, 18f, 0f); // Position
		
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslatef(0.f, 0.f, 0.0f);
		//gl.glScalef(1.0f, -1.0f, 1.0f);
		
		HUDShield.getInstance().draw(gl, spriteSheet);
		// Recover previous Matrix
		gl.glPopMatrix();
		gl.glLoadIdentity();
	}
	
	/**
	 * 
	 * @param gl
	 * @param spriteSheet
	 */
	private void drawLives (GL10 gl, int[] spriteSheet) {
		
	
		for (int i=0;i<Player.getInstance().getData().getLives();i++){
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			// Save Matrix before conversions
			gl.glLoadIdentity();
			gl.glPushMatrix();
			// Transformations to display the player
			gl.glScalef(.05f, .05f, 1f);
			gl.glTranslatef(i+0.5f, 18f, 0f); // Position
			
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.33f, 0.67f, 0.0f);
			
			HUDLives.getInstance().draw(gl, spriteSheet);
			// Recover previous Matrix
			gl.glPopMatrix();
			gl.glLoadIdentity();
		}
	}

}
