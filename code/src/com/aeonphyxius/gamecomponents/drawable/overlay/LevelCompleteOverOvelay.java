package com.aeonphyxius.gamecomponents.drawable.overlay;

import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.data.LevelData;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.Overlay;
import com.aeonphyxius.engine.TextureRegion;
import com.aeonphyxius.engine.Engine.GAMESTATUS;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
import com.aeonphyxius.gamecomponents.manager.SquadronManager;
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

public class LevelCompleteOverOvelay extends EngineGL implements Overlay {

	private static LevelCompleteOverOvelay instance = null;			// Singleton pattern implementation
	private TextureRegion gameOverTexture;					// Texture region containing the icon to show
	private double timeStamp;								// times tamp at the start of each iteration
	private double elapsed;									// elapsed time since last iteration	
	private int animation;									// Current animation number inside the iteration	
	private final int ANIM_FRAMES = 11;						// Number of animations (small effect per game over text)



	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of GameOverOvelay
	 */
	public static LevelCompleteOverOvelay getInstance() {
		if (instance == null) {
			instance = new LevelCompleteOverOvelay();
		}
		return instance;
	}

	/**
	 * Creates the textures and initializes the animation
	 */
	private LevelCompleteOverOvelay() {

		gameOverTexture = new TextureRegion( new float[] { 0.0f, 0.363f, 0.449f, 0.363f, 0.449f, 0.523f, 0.0f, 0.523f, });

		timeStamp = System.currentTimeMillis();		
		animation = 0;
	}

	@Override
	public void resetOverlay(){
		timeStamp = System.currentTimeMillis();		
		animation = 0;		
	}


	@Override
	public void draw(GL10 gl, int[] spriteSheet) {

		elapsed += System.currentTimeMillis() - timeStamp;

		// update the sprite position
		update (gl,.10f + (0.01f * animation), .10f+ (0.01f * animation), 1f,4.f, 2.f, 0f );

		// draw the sprite
		draw(gl, spriteSheet, Engine.TEXTURES, gameOverTexture);

		// restore Matrix transformations to its original configuration
		restoreMatrix(gl);

		// Continue the animation
		if ( elapsed > Engine.ANIMATION_SLEEP){
			elapsed = 0;
			timeStamp = System.currentTimeMillis();
			if (animation < ANIM_FRAMES){
				animation ++ ;
			}else{				
				try {					
					Thread.sleep(Engine.GAME_OVER_THREAD_WAIT*5);					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				LevelData.getInstance().increaseLevel();
				GameStartOvelay.getInstance().resetOverlay();					
				WeaponManager.getInstance().resetWeapons();		
				ExplosionManager.getInstance().resetExplosions();
				try{
					SquadronManager.getInstance().loadSquadronsLevel(LevelData.getInstance().getCurrentLevel());
				}catch (Exception e){
					e.printStackTrace();
				}
				Engine.GameSatus = GAMESTATUS.START;	

			}			
		}
	}

}
