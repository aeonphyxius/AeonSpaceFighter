package com.aeonphyxius.gamecomponents.drawable;

import java.util.Vector;

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
	private Vector<TextureRegion> playerTexturesList;	// Texture region containing the icon to show
	private int texturePosition; 						// position on texture list
	private final int NORMAL_TEXTURE = 0;
	private final int LEFT_TEXTURE = 1;
	private final int RIGHT_TEXTURE = 2;

	
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
		playerTexturesList = new Vector<TextureRegion>();

		TextureRegion tempTextureRegion = new TextureRegion( new float[] { 0.808f, 0.027f, 0.885f, 0.027f, 0.885f, 0.106f, 0.808f, 0.106f, });
		playerTexturesList.add(tempTextureRegion); // Texture for normal position spaceship texture

		tempTextureRegion = new TextureRegion(new float[] { 0.736f, 0.111f,	0.793f, 0.111f, 0.793f, 0.195f, 0.736f, 0.195f, });
		playerTexturesList.add(tempTextureRegion); // Texture for going right position spaceship texture

		tempTextureRegion = new TextureRegion(new float[] { 0.898f, 0.111f,	0.957f, 0.111f, 0.957f, 0.195f, 0.898f, 0.195f, });
		playerTexturesList.add(tempTextureRegion); // Texture for going left position spaceship texture
		
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
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.15f, .15f, 1f);	
		
		switch (Engine.playerFlightAction) {
		
		case Engine.PLAYER_BANK_LEFT_1: // Going LEFT
			texturePosition = LEFT_TEXTURE;
			if (Engine.playerBankPosX > 0) {
				Engine.playerBankPosX -= Engine.PLAYER_BANK_SPEED;
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();				
			} else {
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
			}
			break;
			
		case Engine.PLAYER_BANK_RIGHT_1: // Going RIGHT
			texturePosition = RIGHT_TEXTURE;
			if (Engine.playerBankPosX < 5.5f) {
				Engine.playerBankPosX += Engine.PLAYER_BANK_SPEED;
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();				
			} else {
				gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();									
			}
			break;
			
		case Engine.PLAYER_RELEASE: // Stay		
			texturePosition = NORMAL_TEXTURE;
			gl.glTranslatef(Engine.playerBankPosX,Engine.PLAYER_POS_Y, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			break;
			
		default:
			gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();					
			break;
		}
		
		super.draw(gl, spriteSheet, Engine.TEXTURES, playerTexturesList.get(texturePosition));		
		// Recover previous Matrix
		gl.glPopMatrix();
		gl.glLoadIdentity();
		
		
		
		
	}

}
