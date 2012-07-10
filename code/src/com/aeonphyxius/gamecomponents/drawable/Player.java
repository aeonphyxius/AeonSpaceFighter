package com.aeonphyxius.gamecomponents.drawable;

import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.data.PlayerData;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.engine.TextureRegion;
import com.aeonphyxius.engine.Vibration;
import com.aeonphyxius.gamecomponents.drawable.overlay.PlayerDestructionOverlay;
import com.aeonphyxius.gamecomponents.manager.LevelManager;
import com.aeonphyxius.gamecomponents.manager.SquadronManager;

/**
 * Player Object.
 * 
 * <P> All player information and methods to draw it into the scree
 *  
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class Player extends EngineGL {

	private static Player instance = null;				// Singleton implementation
	private PlayerData data;							// Player information
	private Vector<TextureRegion> playerTexturesList;	// Texture region containing the player image to show
	private int texturePosition; 						// position on texture list
	private final int NORMAL_TEXTURE = 0;				// Player animation, normal position texture
	private final int LEFT_TEXTURE = 1;					// Player animation, going left position texture
	private final int RIGHT_TEXTURE = 2;				// Player animation, going right position texture

	/**
	 * Will create a TextureRegion containing the textures to display into the HUD
	 * the controls
	 */
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}

	/**
	 * Creates a new player, with the different positions textures, player information.
	 */
	private Player() {
		data = new PlayerData();
		playerTexturesList = new Vector<TextureRegion>();

		//TextureRegion tempTextureRegion = new TextureRegion(new float[] { 0.808f, 0.813f,  0.191f, 0.813f,  0.191f, 0.182f, 0.808f,  0.182f, });
		TextureRegion tempTextureRegion = new TextureRegion(new float[] { 0.029f, 0.011f, 0.215f, 0.011f, 0.215f, 0.258f, 0.029f, 0.258f, });
		//enemyTexture = new TextureRegion(new float[] { 0.029f, 0.011f, 0.215f, 0.011f, 0.215f, 0.258f, 0.029f, 0.258f, });
		playerTexturesList.add(tempTextureRegion); // Texture for normal position spaceship texture

		//tempTextureRegion = new TextureRegion(new float[] { 0.736f, 0.111f,	0.793f, 0.111f, 0.793f, 0.195f, 0.736f, 0.195f, });
		tempTextureRegion = new TextureRegion(new float[] { 0.760f, 0.875f,	0.195f, 0.875f, 0.195f, 0.182f, 0.760f, 0.182f, });
		playerTexturesList.add(tempTextureRegion); // Texture for going right position spaceship texture

		//tempTextureRegion = new TextureRegion(new float[] { 0.898f, 0.111f,	0.957f, 0.111f, 0.957f, 0.195f, 0.898f, 0.195f, });
		tempTextureRegion = new TextureRegion(new float[] { 0.760f, 0.875f,	0.195f, 0.875f, 0.195f, 0.182f, 0.760f, 0.182f, });
		playerTexturesList.add(tempTextureRegion); // Texture for going left position spaceship texture

	}	

	/**
	 * increase the player points depending on difficulty level
	 */
	public void increasePoints(){
		this.data.increasePoints();
	}

	/**
	 * Apply damage on the ship, and all events related (sound, vibration)
	 */
	public void applyDamage() {

		MusicManager.getInstance().playSound(Engine.SOUND_LASER_HIT);
		data.increaseDamage();

		Vibration.getInstance().setVibration(Engine.PLAYER_DAMAGE_VIB);

		// Check if we have been destroyed
		if (data.getDamage() <= 0) {

			Vibration.getInstance().setVibration(Engine.PLAYER_DESTROYED_VIB);			
			data.setLives(data.getLives()-1);

			if (data.getLives() > 0){
				data.resetStatus();
				SquadronManager.getInstance().resetSquadrons(LevelManager.getInstance().getCurrentLevel());				
				PlayerDestructionOverlay.getInstance().resetOverlay();
				Engine.GameSatus = Engine.GAMESTATUS.DESTROYED;
			}else{
				Engine.GameSatus = Engine.GAMESTATUS.GAMEOVER;
			}
		}
	}


	/**
	 * Reset all player status, when has been destroyed, before starting again
	 */
	public void resetPlayerStatus(){
		data.resetAllStatus();
	}

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
	 * draw the player's image
	 * @param gl
	 * @param spriteSheet
	 */
	public void draw(GL10 gl) {

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.15f, .15f, 1f);	

		// Depending o the users input, the player will move
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
			super.draw(gl, Engine.TEXTURE_PLAYER_LEFT, playerTexturesList.get(texturePosition));
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
			super.draw(gl, Engine.TEXTURE_PLAYER_RIGHT, playerTexturesList.get(texturePosition));
			break;

		case Engine.PLAYER_RELEASE: // Stay		
			texturePosition = NORMAL_TEXTURE;
			gl.glTranslatef(Engine.playerBankPosX,Engine.PLAYER_POS_Y, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			//super.draw(gl, Engine.TEXTURE_PLAYER, playerTexturesList.get(texturePosition));
			super.draw(gl, Engine.TEXTURE_FILE_OLD, playerTexturesList.get(texturePosition));
			break;

		default:
			gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y, 0f);
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			super.draw(gl, Engine.TEXTURE_PLAYER, playerTexturesList.get(texturePosition));
			break;
		}		
		

		// Recover previous Matrix
		gl.glPopMatrix();
		gl.glLoadIdentity();
	}
}
