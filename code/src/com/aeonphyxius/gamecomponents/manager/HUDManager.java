package com.aeonphyxius.gamecomponents.manager;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.drawable.hud.HUDControl;
import com.aeonphyxius.gamecomponents.drawable.hud.HUDDamage;
import com.aeonphyxius.gamecomponents.drawable.hud.HUDLives;
import com.aeonphyxius.gamecomponents.drawable.hud.HUDScore;
import com.aeonphyxius.gamecomponents.drawable.hud.HUDShield;

/**
 * HUDManager Object.
 * 
 * <P>
 * HUD manager , displays all HUD components
 * 
 * <P>
 * This class contains logic to display the different HUD components on screen
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class HUDManager {


	private static HUDManager instance = null;			// Singleton implementation
	private final int MAX_SCORE = 5;					// Max numbers on the score HUD (00000)

	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of this class
	 */
	public static HUDManager getInstance() {
		if (instance == null) {
			instance = new HUDManager();
		}
		return instance;
	}


	/**
	 * private constructor to do not allow others instantiate this class. Empty
	 */
	private HUDManager() {		
	}

	/**
	 * HUD draw method to display all components on screen
	 * @param gl OpenGL handler
	 */
	public void draw(GL10 gl) {

		drawControl (gl);
		drawLives  (gl);		
		drawShields(gl);
		drawDamage (gl);
		drawScore (gl);
	}

	/**
	 * Preparation to draw the player's score 
	 * @param gl OpenGL handler
	 */
	private void drawScore(GL10 gl) {

		int maxValue = 10000;
		int countStringPos = 0;
		String tempScore = Integer.toString(Player.getInstance().getData().getPoints());

		for (int i=0;i<MAX_SCORE;i++) { // loop the 5 numbers that compose the scores

			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();	
			gl.glPushMatrix();							// Save Matrix before transformations
			gl.glScalef(.025f, .025f, 1f);				// Scale the original image
			gl.glTranslatef(i+32.0f, 37.5f, 0f); 		// Position on screen (-0.1) because at 0 there is a small space at the bottom 		
			gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
			gl.glLoadIdentity();

			// Select the correct texture (number) to display
			if (Player.getInstance().getData().getPoints()< maxValue){
				HUDScore.getInstance().draw(gl, Engine.TEXTURE_FILE_OLD); 
			}else{				
				HUDScore.getInstance().draw(gl, Integer.parseInt(tempScore.charAt(countStringPos)+"") ); 
				countStringPos++;
			}

			maxValue= maxValue/10; // Reduce to next digit

			gl.glPopMatrix();							// Recover previous Matrix
			gl.glLoadIdentity();
		}			
	}


	/**
	 * Preparation to draw the player's damage status 
	 * @param gl OpenGL handler
	 */
	private void drawDamage(GL10 gl) {

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();							// Save Matrix before transformations
		gl.glScalef(.10f, .08f, 1f);				// Scale the original image
		gl.glTranslatef(06.f, 11.3f, 0f);				// Position on screen
		gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
		gl.glLoadIdentity();				
		HUDDamage.getInstance().draw(gl, Player.getInstance().getData().getDamagePercentage());	// Draw the damage texture
		gl.glPopMatrix();							// Recover previous Matrix
		gl.glLoadIdentity();
	}

	/**
	 * Preparation to draw the player's controls 
	 * @param gl OpenGL handler
	 */
	public void drawControl(GL10 gl) {

		// Background BOTTOM
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();	
		gl.glPushMatrix();							// Save Matrix before transformations
		gl.glScalef(1.f, .11f, 1f);					// Scale the original image
		gl.glTranslatef(0.0f, -0.1f, 0f); 			// Position on screen (-0.1) because at 0 there is a small space at the bottom 		
		gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
		gl.glLoadIdentity();
		HUDControl.getInstance().drawBkg(gl); // Draw black background for controls zone
		gl.glPopMatrix();							// Recover previous Matrix
		gl.glLoadIdentity();		

		// Background TOP
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();	
		gl.glPushMatrix();							// Save Matrix before transformations
		gl.glScalef(1.f, .15f, 1f);					// Scale the original image
		gl.glTranslatef(0.0f, 5.9f, 0f); 			// Position on screen (-0.1) because at 0 there is a small space at the bottom 		
		gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
		gl.glLoadIdentity();
		HUDControl.getInstance().drawBkg(gl); // Draw black background for controls zone
		gl.glPopMatrix();							// Recover previous Matrix
		gl.glLoadIdentity();		



		// Left Arrow
		gl.glMatrixMode(GL10.GL_MODELVIEW);		
		gl.glLoadIdentity();	
		gl.glPushMatrix();							// Save Matrix before transformations
		gl.glScalef(.3f, .1f, 1f);					// Scale the original image
		gl.glTranslatef(0.4f, 0.f, 0f); 			// Position on screen		
		gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
		gl.glLoadIdentity();
		HUDControl.getInstance().drawLeftArrow(gl,Engine.LEFT_TEXTURE_POSITION);	// Draw Left arrow
		gl.glPopMatrix();							// Recover previous Matrix
		gl.glLoadIdentity();

		// Right Arrow
		gl.glMatrixMode(GL10.GL_MODELVIEW);				
		gl.glLoadIdentity();	
		gl.glPushMatrix();							// Save Matrix before transformations
		gl.glScalef(.3f, .1f, 1f);					// Scale the original image		
		gl.glTranslatef(1.9f, 0.f, 0f); 			// Position on screen		
		gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
		gl.glLoadIdentity();
		HUDControl.getInstance().drawRightArrow(gl, Engine.RIGHT_TEXTURE_POSITION);	// Draw Right arrow
		gl.glPopMatrix();							// Recover previous Matrix
		gl.glLoadIdentity();

	}

	/**
	 * Preparation to draw the player's shields status 
	 * @param gl OpenGL handler
	 */
	private void drawShields (GL10 gl) {

		gl.glMatrixMode(GL10.GL_MODELVIEW);		
		gl.glLoadIdentity();
		gl.glPushMatrix();							// Save Matrix before transformations
		gl.glScalef(.10f, .08f, 1f);				// Scale the original image	
		gl.glTranslatef(04.f, 11.3f, 0f);				// Position on screen
		gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
		gl.glLoadIdentity();
		HUDShield.getInstance().draw(gl, Player.getInstance().getData().getShieldPercentage()); // Draw the texture
		gl.glPopMatrix();							// Recover previous Matrix
		gl.glLoadIdentity();
	}

	/**
	 * Preparation to draw the player's remaining lives 
	 * @param gl OpenGL handler
	 */
	private void drawLives (GL10 gl) {


		for (int i=0;i<Player.getInstance().getData().getLives();i++){
			gl.glMatrixMode(GL10.GL_MODELVIEW);		
			gl.glLoadIdentity();
			gl.glPushMatrix();							// Save Matrix before transformations
			gl.glScalef(.05f, .05f, 1f);				// Scale the original image	
			gl.glTranslatef(i+0.5f, 18.5f, 0f);			// Position on screen			
			gl.glMatrixMode(GL10.GL_TEXTURE);			// Texture Mode
			gl.glLoadIdentity();
			HUDLives.getInstance().draw(gl);	// Draw the texture
			gl.glPopMatrix();							// Recover previous Matrix
			gl.glLoadIdentity();
		}
	}

}
