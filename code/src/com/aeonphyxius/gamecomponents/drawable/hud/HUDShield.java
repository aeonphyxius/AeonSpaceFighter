package com.aeonphyxius.gamecomponents.drawable.hud;


import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

/**
 * HUDShield Object.
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

public class HUDShield extends EngineGL{
	
	private static HUDShield instance = null;			// Singleton implementation
	private Vector<TextureRegion> textureRegionList;	// Texture region containing the icon to show
	
	/**
	 * Singleton implementation of the unique instance of this class
	 * @return
	 */
	public static HUDShield getInstance() {
		if (instance == null) {
			instance = new HUDShield();
		}
		return instance;
	}

	/**
	 * Will create a TextureRegion containing the textures to display into the HUD
	 * the current player's shields status
	 */
	private HUDShield() {
		textureRegionList = new Vector<TextureRegion>();

		TextureRegion tempTextureRegion = new TextureRegion( new float[] { 0.730f, 0.501f, 0.857f, 0.501f, 0.857f, 0.630f, 0.730f,0.630f,});
		textureRegionList.add(tempTextureRegion); // Texture for shields at 0 %
		
		//tempTextureRegion = new TextureRegion( new float[] {0.758f, 0.691f, 0.877f, 0.691f,	0.877f, 0.814f,	0.758f,0.814f,});
		tempTextureRegion = new TextureRegion( new float[] { 0.730f, 0.673f, 0.857f, 0.673f, 0.857f, 0.802f, 0.730f,0.802f,});
		textureRegionList.add(tempTextureRegion); // Texture for shields at 25 %
		
		//tempTextureRegion = new TextureRegion( new float[] { 0.611f, 0.683f,0.730f, 0.683f,	0.730f, 0.805f,	0.611f,0.805f,});
		tempTextureRegion = new TextureRegion( new float[] { 0.576f, 0.673f, 0.701f, 0.673f, 0.701f, 0.802f, 0.576f,0.802f,});
		textureRegionList.add(tempTextureRegion); // Texture for shields at 50 %
		
		//tempTextureRegion = new TextureRegion( new float[] { 0.332f, 0.683f, 0.453f, 0.683f, 0.453f, 0.805f, 0.332f,0.805f,});
		tempTextureRegion = new TextureRegion( new float[] { 0.419f, 0.673f, 0.548f, 0.673f, 0.548f, 0.802f, 0.419f,0.802f,});
		textureRegionList.add(tempTextureRegion); // Texture for shields at 75 %
		
		//tempTextureRegion = new TextureRegion( new float[] { 0.475f, 0.685f, 0.591f, 0.685f, 0.591f, 0.805f, 0.475f,0.805f,});
		tempTextureRegion = new TextureRegion( new float[] { 0.269f, 0.673f, 0.398f, 0.673f, 0.398f, 0.802f, 0.269f,0.802f,});
		textureRegionList.add(tempTextureRegion); // Texture for shields at 100 %
	}
	
	/**
	 * Draw the shields status at the specified position.
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 * @param textureNum position into the array of textures
	 */
	public void draw(GL10 gl, int[] spriteSheet,int textureNum) {		
		super.draw(gl, spriteSheet, Engine.TEXTURE_ITEMS, textureRegionList.get(textureNum));		
	}
	
}
