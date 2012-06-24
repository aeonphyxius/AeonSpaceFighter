package com.aeonphyxius.gamecomponents.drawable.hud;

import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

/**
 * HUDLives Object.
 * 
 * <P>
 * HUD component to show how many lives left has the player
 * 
 * <P>
 * This class contains logic to display the icons showing the user how many
 * lives left.
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class HUDLives extends EngineGL {

	private static HUDLives instance = null; 	// Singleton implementations
	private TextureRegion textureRegion; 		// Texture region containing the icon to show

	/**
	 * Singleton implementation of the unique instance of this class
	 * 
	 * @return
	 */
	public static HUDLives getInstance() {
		if (instance == null) {
			instance = new HUDLives();
		}
		return instance;
	}

	/**
	 * Will create a TextureRegion containing the icon to display into the HUD lives
	 */
	private HUDLives() {
		textureRegion = new TextureRegion( new float[] { 0.808f, 0.201f, 0.885f, 0.201f, 0.885f, 0.273f, 0.808f, 0.273f, });

	}

	/**
	 * Draw the lives icon at the specified position.
	 * 
	 * @param gl OpenGL handler
	 */
	public void draw(GL10 gl) {
		super.draw(gl, Engine.TEXTURE_FILE_OLD, textureRegion);
	}

}
