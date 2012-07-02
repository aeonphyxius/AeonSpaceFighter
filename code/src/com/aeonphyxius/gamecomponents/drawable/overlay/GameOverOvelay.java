package com.aeonphyxius.gamecomponents.drawable.overlay;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.Overlay;
import com.aeonphyxius.engine.TextureRegion;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
import com.aeonphyxius.gamecomponents.manager.LevelManager;
import com.aeonphyxius.gamecomponents.manager.WeaponManager;

/**
 * GameOverOvelay Object.
 * 
 * <P>
 * Game Over animaton overlay
 * 
 * <P>
 * This class contains logic to display animation when the games ends. Showing a Game Over text 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class GameOverOvelay extends EngineGL implements Overlay {

	private static GameOverOvelay instance = null;			// Singleton pattern implementation
	private TextureRegion gameOverTexture;					// Texture region containing the icon to show
	private double timeStamp;								// times tamp at the start of each iteration
	private double elapsed;									// elapsed time since last iteration



	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of GameOverOvelay
	 */
	public static GameOverOvelay getInstance() {
		if (instance == null) {
			instance = new GameOverOvelay();
		}
		return instance;
	}

	/**
	 * Creates the textures and initializes the animation
	 */
	private GameOverOvelay() {

		//gameOverTexture = new TextureRegion( new float[] { 0.533f, 0.296f, 0.766f, 0.296f, 0.766f, 0.424f, 0.533f, 0.424f, });
		//gameOverTexture = new TextureRegion(new float[] { 1f, 1f, 0f, 1f, 0f, 0f, 1f, 0f, });
		gameOverTexture = new TextureRegion(new float[]   { 0f, 1f, 1f, 1f, 1f, 0f, 0f, 0f, });

		timeStamp = System.currentTimeMillis();
	}

	@Override
	public void resetOverlay(){
		timeStamp = System.currentTimeMillis();		
	}


	@Override
	public void draw(GL10 gl) {

		elapsed += System.currentTimeMillis() - timeStamp;

		// update the sprite position
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(1f,1f, 0f);
		gl.glTranslatef(0f, 0f, 0f);

		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();

		// draw the sprite
		draw(gl, Engine.TEXTURE_GAME_OVER, gameOverTexture);

		// restore Matrix transformations to its original configuration
		restoreMatrix(gl);

		// Continue the animation
		if ( elapsed > Engine.GAME_OVER_SLEEP){
			if (Engine.event != null){
				switch (Engine.event.getAction()){
				case MotionEvent.ACTION_DOWN:
					GameStartOvelay.getInstance().resetOverlay();					
					WeaponManager.getInstance().resetWeapons();		
					ExplosionManager.getInstance().resetExplosions();
					Player.getInstance().resetPlayerStatus();
					LevelManager.getInstance().resetLevelData();
					this.resetOverlay();
					Engine.GameSatus = Engine.GAMESTATUS.END;
					break;
				}
			}			
		}
	}
}
