package com.aeonphyxius.gamecomponents.drawable.hud;

import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

/**
 * HUDScore Object.
 * 
 * <P>
 * HUD component to show how many points has the player
 * 
 * <P>
 * This class contains logic to display numbers as a font, showing the user how many
 * he has. The font textures are contained in a PNG file as the rest of the game textures
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class HUDScore extends EngineGL {

	private static HUDScore instance = null;			// Singleton implementations
	private Vector<TextureRegion> textureRegionList;	// Texture region containing the icon to show

	/**
	 * Singleton implementation of the unique instance of this class
	 * @return
	 */
	public static HUDScore getInstance() {
		if (instance == null) {
			instance = new HUDScore();
		}
		return instance;
	}

	/**
	 * Will create a TextureRegion containing the font textures to display into the HUD
	 * the current player's score
	 */
	private HUDScore() {
		textureRegionList = new Vector<TextureRegion>();

		TextureRegion tempTextureRegion = new TextureRegion( new float[] { 0.007f, 0.328f, 0.035f, 0.328f, 0.035f, 0.355f, 0.007f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 0

		tempTextureRegion = new TextureRegion(new float[] { 0.043f, 0.328f,	0.066f, 0.328f, 0.066f, 0.355f, 0.043f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 1

		tempTextureRegion = new TextureRegion(new float[] { 0.07f, 0.328f, 0.097f, 0.328f, 0.097f, 0.355f, 0.07f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 2

		tempTextureRegion = new TextureRegion(new float[] { 0.101f, 0.328f,	0.129f, 0.328f, 0.129f, 0.355f, 0.101f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 3

		tempTextureRegion = new TextureRegion(new float[] { 0.132f, 0.328f,	0.160f, 0.328f, 0.160f, 0.355f, 0.132f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 4

		tempTextureRegion = new TextureRegion(new float[] { 0.164f, 0.328f,	0.191f, 0.328f, 0.191f, 0.355f, 0.164f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 5

		tempTextureRegion = new TextureRegion(new float[] { 0.195f, 0.328f,	0.222f, 0.328f, 0.222f, 0.355f, 0.195f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 6

		tempTextureRegion = new TextureRegion(new float[] { 0.226f, 0.328f,	0.253f, 0.328f, 0.253f, 0.355f, 0.226f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 7

		tempTextureRegion = new TextureRegion(new float[] { 0.257f, 0.328f,	0.285f, 0.328f, 0.285f, 0.355f, 0.257f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 8

		tempTextureRegion = new TextureRegion(new float[] { 0.289f, 0.328f,	0.316f, 0.328f, 0.316f, 0.355f, 0.289f, 0.355f, });
		textureRegionList.add(tempTextureRegion); // Texture for 9

	}

	/**
	 * Draw the score at the specified position.
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 * @param textureNum position into the array of textures
	 */
	public void draw(GL10 gl, int textureNum) {

		super.draw(gl, Engine.TEXTURE_FILE_OLD,
				textureRegionList.get(textureNum));

	}

}
