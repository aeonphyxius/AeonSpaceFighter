package com.aeonphyxius.gamecomponents.drawable;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

public class Explosion extends EngineGL{
	
	private static Explosion instance = null;			// Singleton implementation
	private Vector<TextureRegion> textureRegionList;	// Texture region containing the icon to show
	
	
	/**
	 * Singleton implementation of the unique instance of this class
	 * @return
	 */
	public static Explosion getInstance() {
		if (instance == null) {
			instance = new Explosion();
		}
		return instance;
	}

	/**
	 * Will create a Explosion containing the textures to display the explosion animation
	 */
	private Explosion() {
		textureRegionList = new Vector<TextureRegion>();

		TextureRegion tempTextureRegion = new TextureRegion( new float[] { 0.566f, 0.605f, 0.582f, 0.605f, 0.582f, 0.621f, 0.566f,0.621f,});
		textureRegionList.add(tempTextureRegion); // Texture for explosion step 0		
		
		tempTextureRegion = new TextureRegion( new float[] { 0.589f, 0.607f, 0.599f, 0.607f, 0.599f, 0.617f, 0.589f,0.617f,});
		textureRegionList.add(tempTextureRegion); // Texture for explosion step 1
		
		tempTextureRegion = new TextureRegion( new float[] { 0.603f, 0.603f, 0.609f, 0.603f, 0.609f, 0.623f, 0.603f,0.623f,});
		textureRegionList.add(tempTextureRegion); // Texture for explosion step 2
		
		tempTextureRegion = new TextureRegion( new float[] { 0.615f, 0.603f, 0.625f, 0.603f, 0.625f, 0.625f, 0.615f,0.625f,});
		textureRegionList.add(tempTextureRegion); // Texture for explosion step 3
		
		tempTextureRegion = new TextureRegion( new float[] { 0.628f, 0.601f, 0.654f, 0.601f, 0.654f, 0.626f, 0.628f,0.626f,});
		textureRegionList.add(tempTextureRegion); // Texture for explosion step 4
	}
	
	
	
	/**
	 * Draw the explosion animation at the specified position.
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 * @param textureNum position into the array of textures
	 */
	public void draw(GL10 gl, int[] spriteSheet,int textureNum) {		
		super.draw(gl, spriteSheet, Engine.TEXTURES, textureRegionList.get(textureNum));		
	}
	

}
