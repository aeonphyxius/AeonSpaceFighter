package com.aeonphyxius.gamecomponents.drawable.hud;


import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

/**
 * HUDControl Object.
 * 
 * <P>
 * HUD component to show the control area
 * 
 * <P>
 * This class contains logic to display the user  controls at the bottom of the playable area 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class HUDControl extends EngineGL{

	private static HUDControl instance = null;			// Singleton implementation
	private Vector<TextureRegion> textureRegionList;	// Texture region containing the icon to show

	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of this class
	 */
	public static HUDControl getInstance() {
		if (instance == null) {
			instance = new HUDControl();
		}
		return instance;
	}

	/**
	 * Will create a TextureRegion containing the textures to display into the HUD
	 * the controls
	 */
	private HUDControl() {
		textureRegionList = new Vector<TextureRegion>();

		TextureRegion tempTextureRegion = new TextureRegion( new float[] { 0.248f, 0.0f, 0.280f, 0.0f, 0.280f, 0.125f, 0.248f,0.125f, });
		textureRegionList.add(tempTextureRegion); // Texture for background image (black)

		tempTextureRegion = new TextureRegion( new float[]  { 0.308f, 0.143f, 0.476f, 0.143f, 0.476f, 0.279f, 0.308f,0.279f, });
		textureRegionList.add(tempTextureRegion); // Texture for left arrow

		tempTextureRegion = new TextureRegion( new float[] { 0.316f, 0.0f, 0.484f, 0.0f, 0.484f, 0.136f,0.316f,0.136f, });				
		textureRegionList.add(tempTextureRegion); // Texture for left pressed arrow

		tempTextureRegion = new TextureRegion( new float[] { 0.488f, 0.143f, 0.654f, 0.143f, 0.654f, 0.279f, 0.488f,0.279f, });
		textureRegionList.add(tempTextureRegion); // Texture for right arrow

		tempTextureRegion = new TextureRegion( new float[] { 0.488f, 0.0f, 0.654f, 0.0f, 0.654f, 0.136f, 0.488f,0.136f, });
		textureRegionList.add(tempTextureRegion); // Texture for right pressed arrow
	}	


	/**
	 * Draw the background for the control panel 
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 */
	public void drawBkg(GL10 gl) {
		super.draw(gl,  Engine.TEXTURE_FILE_OLD, textureRegionList.get(0));

	}

	/**
	 * Draw the control left arrow 
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 * @param textureNum position into the array of textures
	 */
	public void drawLeftArrow (GL10 gl, int textureNum) {
		// Draw the normal left arrow or the pressed version
		if (Engine.playerFlightAction == Engine.PLAYER_BANK_LEFT_1){ 
			super.draw(gl, Engine.TEXTURE_FILE_OLD, textureRegionList.get(textureNum));
		}else{
			super.draw(gl, Engine.TEXTURE_FILE_OLD, textureRegionList.get(textureNum+1));
		}	
	}

	/**
	 * Draw the control right arrow 
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 * @param textureNum position into the array of textures
	 */
	public void drawRightArrow(GL10 gl,int textureNum) {
		// Draw the right left arrow or the pressed version
		if (Engine.playerFlightAction == Engine.PLAYER_BANK_RIGHT_1){
			super.draw(gl,  Engine.TEXTURE_FILE_OLD, textureRegionList.get(textureNum));
		}
		else {
			super.draw(gl, Engine.TEXTURE_FILE_OLD, textureRegionList.get(textureNum+1));
		}
	}

}
