package com.aeonphyxius.gamecomponents.drawable;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.data.PlayerData;
import com.aeonphyxius.engine.DrawableComponent;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.engine.TextureRegion;

public class Player extends EngineGL implements DrawableComponent {

	private static Player instance = null;
	private PlayerData data;
	private TextureRegion playerTexture;

	
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	private Player() {
		data = new PlayerData();
		playerTexture=new TextureRegion( new float[] { 0.0f, 0.0f, 0.33f, 0.0f, 0.33f, 0.30f, 0.0f,0.33f, } );
	}	
	
	public void increasePoints(){
		this.data.setPoints(this.data.getPoints()+10);
	}
	
	public void applyDamage() {
		MusicManager.getInstance().playSound(Engine.SOUND_LASER_HIT);
		data.increaseDamage();
		
		/*if (data.getDamage() == Engine.PLAYER_SHIELDS) {
			data.setDestroyed(true);
		}*/
	}	
	
	/*public BoundingBox getBoundingBox(){
		
	}*/
	
	public PlayerData getData() {
		return data;
	}

	public void setData(PlayerData data) {
		this.data = data;
	}

	public boolean isDestroyed(){
		return data.isDestroyed();
	}
	


	/**
	 * 
	 * @param gl
	 * @param spriteSheet
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		switch (Engine.playerFlightAction) {
		
		case Engine.PLAYER_BANK_LEFT_1: // Going LEFT
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.15f, .15f, 1f);				
			
			if (Engine.playerBankPosX > 0) {
				Engine.playerBankPosX -= Engine.PLAYER_BANK_SPEED;
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.0f, 0.34f, 0.0f);// texture position
			} else {
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();					
				gl.glTranslatef(0.33f, 0.0f, 0.0f); // texture position
			}
			break;
			
		case Engine.PLAYER_BANK_RIGHT_1: // Going RIGHT
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(.15f, .15f, 1f);				
			if (Engine.playerBankPosX < 5.5f) {
				Engine.playerBankPosX += Engine.PLAYER_BANK_SPEED;
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.66f, 0.34f, 0.0f);// texture position
			} else {
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.33f, 0.0f, 0.0f); // texture position					
			}
			break;
			
		case Engine.PLAYER_RELEASE: // Stay
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			// Save Matrix before conversions
			gl.glLoadIdentity();
			gl.glPushMatrix();
			// Transformations to display the player
			gl.glScalef(.15f, .15f, 1f);
			gl.glTranslatef(Engine.playerBankPosX,Engine.PLAYER_POS_Y, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.33f, 0.0f, 0.0f);
			break;
			
		default:
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			// Save Matrix before conversions
			gl.glLoadIdentity();
			gl.glPushMatrix();
			// Transformations to display the player
			gl.glScalef(.15f, .15f, 1f);
			gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.33f, 0.0f, 0.0f);		
			break;
		}
		
		super.draw(gl, spriteSheet, Engine.TEXTURE_PLAYER, playerTexture);
		// Recover previous Matrix
		gl.glPopMatrix();
		gl.glLoadIdentity();
		
		
		
		
	}

}
