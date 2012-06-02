package com.aeonphyxius.gamecomponents.drawable;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

/**
 * Weapon Object.
 * 
 * <P>
 * Weapon component to manage a fired weapon
 * 
 * <P>
 * This class contains logic to display the weapon image 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class Weapon extends EngineGL {
	
	public float posY = 0f;
	public float posX = 0f;
	public boolean shootFired = false;
	private TextureRegion weaponTexture;
	
	/**
	 * Initialize a new weapon. 
	 */
	public Weapon() {
		weaponTexture = new TextureRegion(new float[] { 0.474f, 0.605f, 0.496f,
				0.605f, 0.496f, 0.626f, 0.474f, 0.626f, });
		this.shootFired = true;
		this.posX = Engine.playerBankPosX + Engine.PLAYER_FIRE_START_X;
		this.posY = Engine.PLAYER_FIRE_START_Y; 
	}

	/**
	 * Draw the weapon fired 
	 * @param gl OpenGL handler
	 * @param spriteSheet array containing all sprites ids
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		super.draw(gl, spriteSheet, Engine.TEXTURE_ITEMS, weaponTexture);
	}

}
