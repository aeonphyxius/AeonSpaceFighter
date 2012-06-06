package com.aeonphyxius.gamecomponents.drawable.overlay;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.Overlay;
import com.aeonphyxius.engine.TextureRegion;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
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

		gameOverTexture = new TextureRegion( new float[] { 0.533f, 0.296f, 0.766f, 0.296f, 0.766f, 0.424f, 0.533f, 0.424f, });

		timeStamp = System.currentTimeMillis();
	}

	@Override
	public void resetOverlay(){
		timeStamp = System.currentTimeMillis();		
	}


	@Override
	public void draw(GL10 gl, int[] spriteSheet) {

		elapsed += System.currentTimeMillis() - timeStamp;

		// update the sprite position
		update (gl,.25f, .25f, 1f,1.f, 1.f, 0f );

		// draw the sprite
		draw(gl, spriteSheet, Engine.TEXTURES, gameOverTexture);

		// restore Matrix transformations to its original configuration
		restoreMatrix(gl);

		// Continue the animation
		if ( elapsed > Engine.GAME_OVER_SLEEP){			
			GameStartOvelay.getInstance().resetOverlay();					
			WeaponManager.getInstance().resetWeapons();		
			ExplosionManager.getInstance().resetExplosions();
			Player.getInstance().resetPlayerStatus();
			Engine.GameSatus = Engine.GAMESTATUS.END;

		}
	}

}
