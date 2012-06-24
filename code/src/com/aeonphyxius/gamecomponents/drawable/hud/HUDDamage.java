package com.aeonphyxius.gamecomponents.drawable.hud;


import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

/**
 * HUDDamage Object.
 * 
 * <P>
 * HUD component to show how many shield has the player
 * 
 * <P>
 * This class contains logic to display the user how many shield left he has. 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */


public class HUDDamage extends EngineGL{


	private static HUDDamage instance = null;			// Singleton implementation
	private Vector<TextureRegion> textureRegionList;	// Texture region containing the icon to show


	/**
	 * Singleton implementation of the unique instance of this class
	 * @return
	 */
	public static HUDDamage getInstance() {
		if (instance == null) {
			instance = new HUDDamage();
		}
		return instance;
	}

	/**
	 * Will create a TextureRegion containing the textures to display into the HUD
	 * the current player's damage status
	 */
	private HUDDamage() {		

		textureRegionList = new Vector<TextureRegion>();

		TextureRegion tempTextureRegion = new TextureRegion( new float[] { 0.748f, 0.856f, 0.836f, 0.856f, 0.836f, 0.939f, 0.748f,0.939f,});
		textureRegionList.add(tempTextureRegion); // Texture for damage at 0 %

		tempTextureRegion = new TextureRegion( new float[] { 0.296f, 0.855f, 0.387f, 0.855f, 0.387f,  0.939f, 0.296f,0.939f,}); 
		textureRegionList.add(tempTextureRegion); // Texture for damage at 25 %

		tempTextureRegion = new TextureRegion( new float[] { 0.402f, 0.855f, 0.498f, 0.855f, 0.498f,  0.939f, 0.402f,0.939f,});
		textureRegionList.add(tempTextureRegion); // Texture for damage at 50 %

		tempTextureRegion = new TextureRegion( new float[] { 0.512f, 0.855f, 0.601f, 0.855f, 0.601f, 0.939f, 0.512f,0.939f,});
		textureRegionList.add(tempTextureRegion); // Texture for shield damage at 75 %

		tempTextureRegion = new TextureRegion( new float[] { 0.623f, 0.855f, 0.717f, 0.855f, 0.717f, 0.939f, 0.623f,0.939f,});
		textureRegionList.add(tempTextureRegion); // Texture for damage at 100 %
	}

	/**
	 * Draw the damage status at the specified position.
	 * @param gl OpenGL handler
	 * @param textureNum position into the array of textures
	 */
	public void draw(GL10 gl, int textureNum) {		
		super.draw(gl, Engine.TEXTURE_FILE_OLD, textureRegionList.get(textureNum));		
	}

}
